package com.codedrills.model.analysis;

import com.codedrills.model.Handle;
import com.codedrills.model.Problem;
import com.codedrills.model.Tag;
import com.codedrills.model.stats.UserStats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;

public class AnalysisContext {
  private final UserStats userStats = new UserStats();
  private final List<Handle> handles;
  private List<Problem> sortedSolved;
  private Map<Tag, List<Problem>> sortedSolvedByTag = new HashMap<>();

  public AnalysisContext(List<Handle> handles) {
    this.handles = handles;
  }

  public UserStats getUserStats() {
    return userStats;
  }

  public void addStats(UserStats userStats) {
    getUserStats().merge(userStats);
  }

  public void setSortedSolved(List<Problem> sortedSolved) {
    this.sortedSolved = sortedSolved;
  }

  public List<Problem> getSortedSolved() {
    return sortedSolved;
  }

  public void addTag(Problem po, Tag t) {
    userStats.addTag(t);
    ofNullable(po)
      .ifPresent(p -> sortedSolvedByTag.computeIfAbsent(t, __ -> new ArrayList<>()).add(p));
  }

  public Map<Tag, List<Problem>> getSortedSolvedByTag() {
    return sortedSolvedByTag;
  }

  public void addStrong(List<TagScore> strong) {
    userStats.setStrong(strong);
  }

  public void addWeak(List<TagScore> weak) {
    userStats.setWeak(weak);
  }

  public List<Handle> getHandles() {
    return handles;
  }
}
