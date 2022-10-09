package com.codedrills.service.sites;

import com.codedrills.model.Handle;
import com.codedrills.model.Problem;
import com.codedrills.model.Site;
import com.codedrills.model.Verdict;
import com.codedrills.model.analysis.Rating;
import com.codedrills.model.cf.*;
import com.codedrills.model.exceptions.InvalidInputException;
import com.codedrills.model.stats.UserStats;
import com.codedrills.service.DataFetcher;
import com.codedrills.util.VerdictHelper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.codedrills.model.Site.CODEFORCES;
import static com.codedrills.service.DataFetcher.CacheDuration.NONE;
import static com.codedrills.service.DataFetcher.CacheDuration.SHORT;
import static com.codedrills.util.Helper.gson;
import static java.util.Optional.ofNullable;

@Service
public class Codeforces extends AbstractSiteService {
  private static Logger logger = Logger.getLogger(Codeforces.class);

  public final static List<Integer> slrs = Arrays.asList(683, 470, 188, 153, 130, 100, 72, 64);
  public final static List<String> blacklistedHandles = Arrays.asList("vjudge1", "vjudge2", "vjudge3", "vjudge4", "vjudge5");

  private final static String PROBLEM_SET_URL = "http://codeforces.com/api/problemset.problems";
  private final static String PROBLEM_URL = "http://codeforces.com/problemset/problem/%s/%s";
  private final static String USER_STATUS_URL = "http://codeforces.com/api/user.status?handle=%s&from=%d&count=%d";
  private final static String USER_INFO_URL = "http://codeforces.com/api/user.info?handles=%s";
  private final static int MAX_SUB_FETCH_COUNT = 3000;
  private final static int MAX_SUB_COUNT = 5 * MAX_SUB_FETCH_COUNT;


  @Override
  public Site site() {
    return CODEFORCES;
  }

  @Override
  public List<Problem> fetchProblems() {
    logger.info("Fetching problems from cf");
    CFProblemSetResponse response = gson.fromJson(
      dataFetcher.fetchJson(PROBLEM_SET_URL, NONE),
      CFProblemSetResponse.class
    );
    return processProblemSet(response);
  }

  @Override
  public UserStats fetchUserStats(Handle handle) {
    String user = handle.getHandle();
    if(blacklistedHandles.contains(user.toLowerCase())) {
      throw new InvalidInputException(String.format("Can't process cf handle %s", user));
    }
    logger.info(String.format("Fetching cf stats %s ", user));
    List<CFSubmission> submissions = new ArrayList<>();
    int start = 1;
    do {
      logger.info(String.format("Fetching cf stats %s(%d-) ", user, start));
      CFUserStatusResponse response = gson.fromJson(
        dataFetcher.fetchJson(String.format(USER_STATUS_URL, user, start, MAX_SUB_FETCH_COUNT), SHORT),
        CFUserStatusResponse.class
      );
      submissions.addAll(response.getResult());
      int cur = response.getResult().size();
      start += cur;
      if(cur < MAX_SUB_FETCH_COUNT) {
        break;
      }
      if(submissions.size() >= MAX_SUB_COUNT) {
        throw new InvalidInputException(String.format("Submissions for cf handle %s is too large to process", user));
      }
    } while(true);

    CFUserInfoResponse infoResponse = gson.fromJson(
      dataFetcher.fetchJson(String.format(USER_INFO_URL, user), DataFetcher.CacheDuration.SHORT),
      CFUserInfoResponse.class
    );

    UserStats userStats = processSubmissions(submissions);
    CFUser cfUser = infoResponse.getResult().get(0);
    userStats.addRating(handle, new Rating(
      ofNullable(cfUser.getRating()).map(Double::new).orElse(null),
      ofNullable(cfUser.getMaxRating()).map(Double::new).orElse(null)
    ));

    logger.info(String.format("Fetching completed cf stats %s ", user));

    return userStats;
  }

  private UserStats processSubmissions(List<CFSubmission> result) {
    UserStats userStats = new UserStats();
    result.stream()
      .forEach(s -> {
        Verdict verdict = VerdictHelper.mapVerdict(site(), s.getVerdict());
        userStats.addVerdict(verdict, 1);
        if(verdict.equals(Verdict.AC)) {
          userStats.addSolved(computeId(s.getProblem().getContestId(), s.getProblem().getIndex()));
        }
      });

    return userStats;
  }

  private List<Problem> processProblemSet(CFProblemSetResponse response) {
    Map<String, CFProblem> cfproblems = response.getResult().getProblems()
      .stream()
      .filter(c -> c.getType().equals("PROGRAMMING"))
      .filter(c -> !slrs.contains(c.getContestId()))
      .collect(Collectors.toMap(c -> computeId(c.getContestId(), c.getIndex()), Function.identity()));

    Map<String, CFProblemStat> cfstats = response.getResult().getProblemStatistics()
      .stream()
      .collect(Collectors.toMap(c -> computeId(c.getContestId(), c.getIndex()), Function.identity()));

    return cfproblems.entrySet()
      .stream()
      .map(e -> {
        String id = e.getKey();
        CFProblem cfProblem = e.getValue();
        return new Problem(
          site(),
          id,
          String.format(PROBLEM_URL, cfProblem.getContestId(), cfProblem.getIndex()),
          cfProblem.getName(),
          mapTags(cfProblem.getTags()),
          cfstats.get(id).getSolvedCount()
        );
      })
      .collect(Collectors.toList());
  }

  private String computeId(int contestId, String index) {
    return String.format("cf:%s/%s", contestId, index);
  }
}
