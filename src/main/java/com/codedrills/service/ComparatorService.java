package com.codedrills.service;

import com.codedrills.model.Problem;
import com.codedrills.model.Tag;
import com.codedrills.model.analysis.AnalysisContext;
import com.codedrills.model.analysis.ComparatorResult;
import com.codedrills.model.analysis.TagScore;
import com.codedrills.util.HandleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;

@Service
public class ComparatorService {
  @Autowired
  AnalysisService analysisService;
  @Autowired
  ProblemsService problemsService;

  public ComparatorResult compare(String handlesA, String handlesB) {
    handlesA = canonize(handlesA);
    handlesB = canonize(handlesB);
    ComparatorResult comparatorResult = new ComparatorResult(handlesA, handlesB);

    comparatorResult.setSame(handlesA.equals(handlesB));
    if(comparatorResult.areSame()) return comparatorResult;

    AnalysisContext analysisA = analysisService.analyzeUser(HandleHelper.splitHandles(handlesA));
    AnalysisContext analysisB = analysisService.analyzeUser(HandleHelper.splitHandles(handlesB));

    setStrong(comparatorResult, analysisA.getUserStats().getStrong(), analysisB.getUserStats().getStrong());
    setSolved(comparatorResult, analysisA.getUserStats().getSolvedProblemUids(), analysisB.getUserStats().getSolvedProblemUids());

    comparatorResult.setRatingsA(analysisA.getUserStats().getRatings());
    comparatorResult.setRatingsB(analysisB.getUserStats().getRatings());

    return comparatorResult;
  }

  private void setSolved(ComparatorResult comparatorResult, Set<String> solvedProblemUidsA, Set<String> solvedProblemUidsB) {
    List<Problem> solvedA = solvedProblemUidsA.stream()
      .filter(u -> !solvedProblemUidsB.contains(u))
      .map(problemsService::getProblemById)
      .filter(p -> p != null)
      .collect(Collectors.toList());

    List<Problem> solvedB = solvedProblemUidsB.stream()
      .filter(u -> !solvedProblemUidsA.contains(u))
      .map(problemsService::getProblemById)
      .filter(p -> p != null)
      .collect(Collectors.toList());

    comparatorResult.setOnlySolvedByA(solvedA);
    comparatorResult.setOnlySolvedByB(solvedB);
  }

  private void setStrong(ComparatorResult comparatorResult, List<TagScore> strongA, List<TagScore> strongB) {
    Map<Tag, Double> strongAMap = strongA.stream()
      .collect(Collectors.toMap(t -> t.getTag(), t -> t.getScore()));

    Map<Tag, Double> strongBMap = strongB.stream()
      .collect(Collectors.toMap(t -> t.getTag(), t -> t.getScore()));

    Map<Boolean, List<Tag>> strongerA = Arrays.stream(Tag.values())
      .filter(t -> strongAMap.containsKey(t) || strongBMap.containsKey(t))
      .filter(t -> isStronger(strongAMap.get(t), strongBMap.get(t)) != null)
      .collect(groupingBy(t -> isStronger(strongAMap.get(t), strongBMap.get(t))));

    comparatorResult.setStrongerA(strongerA.getOrDefault(true, emptyList()));
    comparatorResult.setStrongerB(strongerA.getOrDefault(false, emptyList()));
  }

  private Boolean isStronger(Double scoreA, Double scoreB) {
    if(scoreA == null) return false;
    if(scoreB == null) return true;
    if(scoreA.equals(scoreB)) return null;
    return scoreA > scoreB;
  }

  private String canonize(String handles) {
    return HandleHelper.canonicalHandles(HandleHelper.validateAndSplit(handles));
  }
}
