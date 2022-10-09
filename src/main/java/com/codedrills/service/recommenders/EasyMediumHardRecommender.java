package com.codedrills.service.recommenders;

import com.codedrills.model.recommendation.Recommendation;
import com.codedrills.model.recommendation.RecommendationContext;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class EasyMediumHardRecommender extends AbstractRecommender {
  @Override
  public void recommend(RecommendationContext context) {
    context.addRecommendation(new Recommendation(
       "Easy",
        "Easy problems",
        getEasy(context.getSolvedSorted(), context.getUnsolvedSorted()).limit(10).collect(Collectors.toList()),
        425
      )
    );

    context.addRecommendation(new Recommendation(
       "Medium",
        "Medium problems",
        getMedium(context.getSolvedSorted(), context.getUnsolvedSorted()).limit(10).collect(Collectors.toList()),
        450
      )
    );

    context.addRecommendation(new Recommendation(
       "Hard",
        "Hard problems",
        getHard(context.getSolvedSorted(), context.getUnsolvedSorted()).limit(10).collect(Collectors.toList()),
        475
      )
    );
  }
}
