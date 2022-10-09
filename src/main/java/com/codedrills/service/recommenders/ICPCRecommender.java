package com.codedrills.service.recommenders;

import com.codedrills.model.recommendation.Recommendation;
import com.codedrills.model.recommendation.RecommendationContext;
import org.springframework.stereotype.Service;

@Service
public class ICPCRecommender extends AbstractRecommender {
  @Override
  public void recommend(RecommendationContext context) {
    context.addRecommendation(new Recommendation(
       "ICPC",
      "Recommendations for ICPC team practice. Best used when all team handles are entered",
      uniformSelect(context.getSolvedSorted(), context.getUnsolvedSorted(), 10),
      500
    ));
  }
}
