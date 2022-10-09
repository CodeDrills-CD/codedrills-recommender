package com.codedrills.model.analysis;

import com.codedrills.model.Handle;
import com.codedrills.model.recommendation.PracticeRecommendations;
import com.codedrills.model.stats.UserStats;

import java.util.Map;

public class AnalysisResult {
  private String handles;
  private UserStats userStats;
  private PracticeRecommendations practiceRecommendations;

  public AnalysisResult(String handles, UserStats userStats, PracticeRecommendations practiceRecommendations) {
    this.handles = handles;
    this.userStats = userStats;
    this.practiceRecommendations = practiceRecommendations;
  }

  public UserStats getUserStats() {
    return userStats;
  }

  public PracticeRecommendations getPracticeRecommendations() {
    return practiceRecommendations;
  }

  public String getHandles() {
    return handles;
  }
}
