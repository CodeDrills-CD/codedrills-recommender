package com.codedrills.service.recommenders;

import com.codedrills.model.Problem;
import com.codedrills.model.recommendation.Recommendation;
import com.codedrills.model.recommendation.RecommendationContext;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarmupRecommender extends AbstractRecommender {
  @Override
  public void recommend(RecommendationContext context) {
    List<Problem> from = context.getUnsolved()
      .stream()
      .limit(20)
      .collect(Collectors.toList());
    Collections.shuffle(from);

    List<Problem> problems = from.stream()
      .limit(2)
      .collect(Collectors.toList());

    Recommendation recommendation = new Recommendation(
       "Warmup",
      "Warmup problems for before a contest or a long practice session",
      problems,
      100
    );

    context.addRecommendation(recommendation);
  }
}
