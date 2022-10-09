package com.codedrills.model.recommendation;

import java.util.List;
import java.util.stream.Collectors;

public class PracticeRecommendations {
  private final List<Recommendation> recommendations;

  public PracticeRecommendations(List<Recommendation> recommendations) {
    this.recommendations = recommendations;
  }

  public List<Recommendation> getRecommendations() {
    return recommendations.stream()
      .sorted(Recommendation::compareByRank)
      .collect(Collectors.toList());
  }
}
