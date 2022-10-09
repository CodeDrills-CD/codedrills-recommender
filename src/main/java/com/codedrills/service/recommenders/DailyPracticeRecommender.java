package com.codedrills.service.recommenders;

import com.codedrills.model.recommendation.Recommendation;
import com.codedrills.model.recommendation.RecommendationContext;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DailyPracticeRecommender extends AbstractRecommender {
  @Override
  public void recommend(RecommendationContext context) {
    Recommendation recommendation = new Recommendation(
      "Daily Practice",
      "One easy and medium problem for quick daily practice",
      Stream.concat(
        getEasy(context.getSolvedSorted(), context.getUnsolvedSorted()).limit(1),
        getMedium(context.getSolvedSorted(), context.getUnsolvedSorted()).limit(1)
      ).limit(2).collect(Collectors.toList()),
      200
    );

    context.addRecommendation(recommendation);
  }
}
