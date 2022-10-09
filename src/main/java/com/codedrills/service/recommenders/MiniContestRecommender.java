package com.codedrills.service.recommenders;

import com.codedrills.model.recommendation.Recommendation;
import com.codedrills.model.recommendation.RecommendationContext;
import org.springframework.stereotype.Service;

@Service
public class MiniContestRecommender extends AbstractRecommender {
  @Override
  public void recommend(RecommendationContext context) {
    context.addRecommendation(new Recommendation(
       "Mini Contest",
      "5 problems - ideal for a 2 hour solo/team practice",
      uniformSelect(context.getSolvedSorted(), context.getUnsolvedSorted(), 5),
      600
      )
    );
  }
}
