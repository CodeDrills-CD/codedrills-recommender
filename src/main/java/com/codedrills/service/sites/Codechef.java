package com.codedrills.service.sites;

import com.codedrills.model.Handle;
import com.codedrills.model.Problem;
import com.codedrills.model.Site;
import com.codedrills.model.analysis.Rating;
import com.codedrills.model.cc.CCProblemFetchResponse;
import com.codedrills.model.stats.UserStats;
import com.codedrills.util.Helper;
import com.codedrills.util.VerdictHelper;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.codedrills.model.Site.CODECHEF;
import static com.codedrills.service.DataFetcher.CacheDuration.NONE;
import static com.codedrills.service.DataFetcher.CacheDuration.SHORT;

@Service
public class Codechef extends AbstractTagProblemFetcher {
  private static Logger logger = Logger.getLogger(Codechef.class);
  private static String FETCH_PROBLEM_URL = "https://www.codechef.com/get/tags/problems/%s";
  private static String PROBLEM_URL = "https://www.codechef.com/problems/%s";
  private static String USER_URL = "https://www.codechef.com/users/%s";
  private static Set<String> verdicts = new HashSet<>(VerdictHelper.verdictsFor(CODECHEF));

  @Override
  public Site site() {
    return CODECHEF;
  }

  @Override
  public UserStats fetchUserStats(Handle handle) {
    String user = handle.getHandle();
    logger.info(String.format("Fetching cc stats %s ", user));
    try {
      Document doc = dataFetcher.fetchDoc(String.format(USER_URL, user), SHORT);
      UserStats userStats = new UserStats();

      parseSubmissions(userStats, doc);
      parseRating(handle, userStats, doc);

      logger.info(String.format("Fetching completed cc stats %s ", user));
      return userStats;
    } catch(Exception ex) {
      logger.warn(String.format("Error while fetching cc submissions for %s", user), ex);
      throw new RuntimeException(ex.getCause());
    }
  }

  private void parseSubmissions(UserStats userStats, Document doc) {

    Elements solvedElements = doc.select("section.problems-solved div.content a");
    solvedElements.stream()
      .map(e -> e.ownText())
      .map(this::computeUid)
      .forEach(userStats::addSolved);

    String body = doc.html();
    int idx1 = body.indexOf("{name:'");
    int idx2 = body.indexOf("}]", idx1);
    String array = body.substring(idx1, idx2);
    String[] parts = array.split(",");

    for(int i = 0; i < parts.length - 1; ++i) {
      String v = getKey(parts[i]);
      if(verdicts.contains(v)) {
        userStats.addVerdict(VerdictHelper.mapVerdict(site(), v), Integer.parseInt(getVal(parts[i + 1])));
        ++i;
      }
    }
  }

  private void parseRating(Handle handle, UserStats userStats, Document doc) {
    Elements ratingElements = doc.select("aside.sidebar div.rating-header div.rating-number");
    Double current = Helper.parseRatingNumber(ratingElements.get(0).text());

    ratingElements = doc.select("aside.sidebar div.rating-header small");
    String text = ratingElements.get(0).text().split(" ")[2];
    text = text.substring(0, text.length() - 1);

    Double highest = Helper.parseRatingNumber(text);

    userStats.addRating(handle, new Rating(current, highest));

  }

  private String getKey(String part) {
    if(!part.contains(":")) return "";
    String key = part.split(":")[1];
    if(key.startsWith("'")) key = key.substring(1);
    if(key.endsWith("'")) key = key.substring(0, key.length() - 1);
    return key;
  }

  private String getVal(String part) {
    String count = part.trim().split(":")[1];
    if(count.endsWith("}")) count = count.substring(0, count.length() - 1);
    return count;
  }

  @Override
  protected List<Problem> fetchForTag(String tag) {
    logger.info(String.format("Fetching cc problems for tag %s", tag));

    CCProblemFetchResponse response;
    response = Helper.gson.fromJson(
      dataFetcher.fetchJson(String.format(FETCH_PROBLEM_URL, tag), NONE),
      CCProblemFetchResponse.class
    );

    return response.getAll_problems()
      .values()
      .stream()
      .map(cc -> new Problem(
          site(),
          computeUid(cc.getCode()),
          String.format(PROBLEM_URL, cc.getCode()),
          cc.getName(),
          mapTags(cc.getTags()),
          cc.getSolved_by()
        )
      )
      .collect(Collectors.toList());
  }

  private String computeUid(String code) {
    return "cc:" + code;
  }
}
