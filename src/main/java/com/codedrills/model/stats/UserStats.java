package com.codedrills.model.stats;

import com.codedrills.model.Handle;
import com.codedrills.model.Tag;
import com.codedrills.model.Verdict;
import com.codedrills.model.analysis.Rating;
import com.codedrills.model.analysis.TagScore;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Collections.emptyList;

public class UserStats {
  private Set<String> solvedProblemUids = new HashSet<>();
  private ConcurrentHashMap<Verdict, Integer> verdictCount = new ConcurrentHashMap<>();
  private ConcurrentHashMap<Tag, Integer> tagCount = new ConcurrentHashMap<>();
  private List<TagScore> strong = emptyList(), weak = emptyList();
  private Map<Handle, Rating> ratings = new TreeMap<>();

  public Set<String> getSolvedProblemUids() {
    return solvedProblemUids;
  }

  public Map<Verdict, Integer> getVerdictCount() {
    return verdictCount;
  }

  public Map<Tag, Integer> getTagCount() {
    return tagCount;
  }

  public void addVerdict(Verdict verdict, int cnt) {
    verdictCount.put(verdict, verdictCount.getOrDefault(verdict, 0) + cnt);
  }

  public void addSolved(String uid) {
    solvedProblemUids.add(uid);
  }

  public void merge(UserStats userStats) {
    solvedProblemUids.addAll(userStats.getSolvedProblemUids());
    userStats.getVerdictCount()
      .entrySet()
      .forEach(e -> verdictCount.put(e.getKey(), verdictCount.getOrDefault(e.getKey(), 0) + e.getValue()));
    userStats.getRatings()
      .entrySet()
      .forEach(e -> ratings.put(e.getKey(), e.getValue()));
  }

  public void addTag(Tag t) {
    tagCount.put(t, tagCount.getOrDefault(t, 0) + 1);
  }

  // Used from analysis.ftl
  public Integer totalSubmissions() {
    return verdictCount.values()
      .stream()
      .mapToInt(i -> i)
      .sum();
  }

  public Integer totalSolved() {
    return solvedProblemUids.size();
  }

  public List<TagScore> getStrong() {
    return strong;
  }

  public void setStrong(List<TagScore> strong) {
    this.strong = strong;
  }

  public List<TagScore> getWeak() {
    return weak;
  }

  public void setWeak(List<TagScore> weak) {
    this.weak = weak;
  }

  public Map<Handle, Rating> getRatings() {
    return ratings;
  }

  public void addRating(Handle handle, Rating rating) {
    ratings.put(handle, rating);
  }
}
