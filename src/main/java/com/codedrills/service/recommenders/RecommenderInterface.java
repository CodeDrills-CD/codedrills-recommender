package com.codedrills.service.recommenders;

import com.codedrills.model.recommendation.RecommendationContext;

public interface RecommenderInterface {
  void recommend(RecommendationContext context);
}
