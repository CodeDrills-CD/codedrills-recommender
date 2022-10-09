package com.codedrills.model.recommendation;

import com.codedrills.model.Problem;
import com.codedrills.model.Tag;
import com.codedrills.model.analysis.AnalysisContext;
import com.codedrills.model.stats.UserStats;
import com.codedrills.util.HandleHelper;

import java.util.*;
import java.util.stream.Collectors;

public class RecommendationContext {
  private final Map<String, Problem> problemsByUid;
  private final Map<Tag, Set<Problem>> problemsByTags;
  private final Map<Tag, List<Problem>> sortedSolvedByTags;
  private final Map<Tag, List<Problem>> sortedUnsolvedByTags;

  private final UserStats userStats;
  private final List<Problem> unsolved;
  private final List<Problem> solved;
  private final List<Problem> solvedSorted;
  private final List<Problem> unsolvedSorted;
  private final String handles;

  private List<Recommendation> recommendations = new ArrayList<>();

  public RecommendationContext(Map<String, Problem> problemsByUid, Map<Tag, Set<Problem>> problemsByTags, AnalysisContext analysisContext) {
    this.problemsByUid = problemsByUid;
    this.problemsByTags = problemsByTags;
    this.userStats = analysisContext.getUserStats();
    this.handles = HandleHelper.canonicalHandles(analysisContext.getHandles());

    Set<String> solvedUids = new HashSet<>(userStats.getSolvedProblemUids());
    Set<String> unsolvedUids = new HashSet<>(problemsByUid.keySet());
    unsolvedUids.removeAll(solvedUids);

    solved = solvedUids.stream()
      .map(problemsByUid::get)
      .filter(p -> p != null)
      .collect(Collectors.toList());

    unsolved = unsolvedUids.stream()
      .map(problemsByUid::get)
      .filter(p -> p != null)
      .collect(Collectors.toList());

    solvedSorted = solved
      .stream()
      .sorted(Problem::compareByScore)
      .collect(Collectors.toList());

    unsolvedSorted = unsolved
      .stream()
      .sorted(Problem::compareByScore)
      .collect(Collectors.toList());

    sortedSolvedByTags = analysisContext.getSortedSolvedByTag();
    sortedUnsolvedByTags = new HashMap<>();
    unsolvedSorted.stream()
      .forEach(p -> p.getTags().stream()
        .forEach(t -> sortedUnsolvedByTags.computeIfAbsent(t, __ -> new ArrayList<>()).add(p)));
  }

  public void addRecommendation(Recommendation recommendation) {
    if(recommendation == null || recommendation.getPracticeProblems().isEmpty()) return;
    recommendation.getPracticeProblems().sort(
      (p1, p2) -> new Double(p1.getProblem().getScore()).compareTo(p2.getProblem().getScore())
    );
    recommendation.setHandles(handles);
    recommendations.add(recommendation);
  }

  public Map<String, Problem> getProblemsByUid() {
    return problemsByUid;
  }

  public UserStats getUserStats() {
    return userStats;
  }

  public List<Recommendation> getRecommendations() {
    return recommendations;
  }

  public List<Problem> getUnsolved() {
    return unsolved;
  }

  public List<Problem> getSolved() {
    return solved;
  }

  public Map<Tag, Set<Problem>> getProblemsByTags() {
    return problemsByTags;
  }

  public List<Problem> getSolvedSorted() {
    return solvedSorted;
  }

  public List<Problem> getUnsolvedSorted() {
    return unsolvedSorted;
  }

  public Map<Tag, List<Problem>> getSortedSolvedByTags() {
    return sortedSolvedByTags;
  }

  public Map<Tag, List<Problem>> getSortedUnsolvedByTags() {
    return sortedUnsolvedByTags;
  }
}