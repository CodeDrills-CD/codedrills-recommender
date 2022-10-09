package com.codedrills.service;

import com.codedrills.model.Handle;
import com.codedrills.model.Problem;
import com.codedrills.model.Tag;
import com.codedrills.model.analysis.AnalysisContext;
import com.codedrills.model.analysis.TagScore;
import com.codedrills.model.stats.UserStats;
import com.codedrills.service.sites.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.codedrills.model.Tag.UNTAGGED;
import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;

@Service
public class AnalysisService {
  private static final int TAG_SOLVED_COUNT_THRESHOLD = 7;
  private static final int STRONG_WEAK_THRESHOLD = 30;
  @Autowired
  private ProblemsService problemsService;
  @Autowired
  private SiteService siteService;

  public AnalysisContext analyzeUser(List<Handle> handles) {
    AnalysisContext analysisContext = new AnalysisContext(handles);

    List<UserStats> userStatsList = siteService.fetchSubmissionStats(handles);
    userStatsList.stream()
      .forEach(analysisContext::addStats);

    analyzeTags(analysisContext);
    addStrongWeakAreas(analysisContext);

    return analysisContext;
  }

  private void analyzeTags(AnalysisContext analysisContext) {
    List<Problem> sortedSolved = analysisContext.getUserStats().getSolvedProblemUids()
      .stream()
      .map(problemsService::getProblemById)
      .filter(p -> p != null)
      .sorted(Problem::compareByScore)
      .collect(Collectors.toList());

    analysisContext.setSortedSolved(sortedSolved);

    analysisContext.getUserStats().getSolvedProblemUids()
      .stream()
      .map(problemsService::getProblemById)
      .sorted(Problem::compareByScore)
      .map(Optional::ofNullable)
      .forEach(po ->
        po.map(p -> p.getTags()).orElseGet(() -> singleton(UNTAGGED))
          .stream()
          .forEach(t -> analysisContext.addTag(po.orElse(null), t))
      );
  }

  private void addStrongWeakAreas(AnalysisContext analysisContext) {
    if(analysisContext.getUserStats().getSolvedProblemUids().size() <= STRONG_WEAK_THRESHOLD) {
      analysisContext.addStrong(emptyList());
      analysisContext.addWeak(emptyList());
      return;
    }

    List<TagScore> tagScores = Arrays.stream(Tag.values())
      .filter(t -> !t.equals(UNTAGGED))
      .map(t -> {
        List<Problem> sortedSolved = analysisContext.getSortedSolvedByTag().getOrDefault(t, emptyList());
        int getLast = Math.min(sortedSolved.size(), Math.max(5, 20 * sortedSolved.size() / 100));
        double score = sortedSolved.stream()
          .skip(sortedSolved.size() - getLast)
          .mapToDouble(Problem::getScore)
          .average()
          .orElse(0);

        return new TagScore(t, score, sortedSolved.size());
      })
      .filter(t -> t.getCount() > TAG_SOLVED_COUNT_THRESHOLD)
      .sorted((ts1, ts2) -> {
        if(new Double(ts1.getScore()).equals(ts2.getScore())) {
          if(ts1.getTag().getType() == Tag.TagType.TECHNIQUE) return -1;
          return 1;
        }
        return new Double(ts1.getScore()).compareTo(ts2.getScore());
      })
      .collect(Collectors.toList());

    int weakLen = Math.max(0, tagScores.size() - 5);
    List<TagScore> weak = tagScores.stream()
      .limit(weakLen)
      .collect(Collectors.toList());

    List<TagScore> strong = tagScores.stream()
      .collect(Collectors.toList());

    Collections.reverse(strong);

    analysisContext.addStrong(strong);
    analysisContext.addWeak(weak);
  }

}
