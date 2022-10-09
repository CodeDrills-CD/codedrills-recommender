package com.codedrills.service.sites;

import com.codedrills.model.Handle;
import com.codedrills.model.Problem;
import com.codedrills.model.Site;
import com.codedrills.model.analysis.Rating;
import com.codedrills.model.stats.UserStats;
import com.codedrills.util.Helper;
import com.codedrills.util.TagHelper;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.codedrills.model.Verdict.*;
import static com.codedrills.service.DataFetcher.CacheDuration.NONE;
import static com.codedrills.service.DataFetcher.CacheDuration.SHORT;
import static java.util.Collections.singletonList;

@Service
public class Spoj extends AbstractTagProblemFetcher {
  private static Logger logger = Logger.getLogger(Codechef.class);
  private static String FETCH_PROBLEM_URL = "https://www.spoj.com/problems/tag/%s";
  private static String PROBLEM_URL = "https://www.spoj.com/problems/%s";
  private static String USER_URL = "https://www.spoj.com/users/%s";

  @Override
  public UserStats fetchUserStats(Handle handle) {
    String user = handle.getHandle();
    logger.info(String.format("Fetching sp stats %s ", user));
    try {
      Document doc = dataFetcher.fetchDoc(String.format(USER_URL, user), SHORT);
      UserStats userStats = new UserStats();

      parseSubmissions(userStats, doc);
      parsePoints(handle, userStats, doc);

      logger.info(String.format("Fetching completed sp stats %s ", user));
      return userStats;
    } catch(Exception ex) {
      logger.warn(String.format("Error while fetching sp submissions for %s", user), ex);
      throw new RuntimeException(ex.getCause());
    }
  }

  private void parsePoints(Handle handle, UserStats userStats, Document doc) {
    Elements ratingElements = doc.select("div#user-profile-left p");
    String text = ratingElements.stream()
      .filter(e -> e.text().endsWith("points)"))
      .findAny()
      .map(e -> e.text())
      .get();

    String[] parts = text.split(" ");
    text = parts[parts.length - 2];
    text = text.substring(1);

    Double points = Helper.parseRatingNumber(text);
    userStats.addRating(handle, new Rating(points, null));
  }

  private void parseSubmissions(UserStats userStats, Document doc) {
    Elements solvedElements = doc.select("div#user-profile-tables table tbody tr td a");
    solvedElements.stream()
      .map(e -> e.ownText())
      .map(this::computeUid)
      .forEach(userStats::addSolved);

    String body = doc.html();
    int idx1 = body.indexOf("['Submissions',");
    int idx2 = body.indexOf("]", idx1);
    String array = body.substring(idx1 + 1, idx2);
    String[] parts = array.split(",");


    userStats.addVerdict(AC, Integer.parseInt(parts[1].trim()));
    userStats.addVerdict(WA, Integer.parseInt(parts[2].trim()));
    userStats.addVerdict(TLE, Integer.parseInt(parts[3].trim()));
    userStats.addVerdict(RTE, Integer.parseInt(parts[4].trim()));
    userStats.addVerdict(CE, Integer.parseInt(parts[5].trim()));
  }


  @Override
  public Site site() {
    return Site.SPOJ;
  }

  protected List<Problem> fetchForTag(String tag) {
    logger.info(String.format("Fetching sp problems for tag %s", tag));

    try {
      Document doc = dataFetcher.fetchDoc(String.format(FETCH_PROBLEM_URL, tag), NONE);
      Elements rows = doc.select("table.problems tbody tr");
      List<Problem> problems = new ArrayList<>();

      for(Element row : rows) {
        Elements r = row.select("td");
        Element nameElement = r.get(1).select("a").get(0);
        Element solvedElement = r.get(3).select("a").get(0);
        String name = nameElement.ownText();
        String link = nameElement.attr("href");
        String[] parts = link.split("/");
        String id = parts[parts.length - 1];
        int solved = Integer.parseInt(solvedElement.ownText());

        Problem problem = new Problem(site(),
          computeUid(id),
          String.format(PROBLEM_URL, id),
          name,
          singletonList(TagHelper.mapTag(site(), tag)),
          solved
        );
        problems.add(problem);
      }

      return problems;
    } catch(Exception ex) {
      logger.warn(String.format("Error while fetching sp problems for %s", tag), ex);
      throw new RuntimeException(ex.getCause());
    }
  }

  private String computeUid(String id) {
    return String.format("%s/%s", site().getShortName(), id);
  }
}
