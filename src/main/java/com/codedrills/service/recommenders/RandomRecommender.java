package com.codedrills.service.recommenders;

import com.codedrills.model.Problem;
import com.codedrills.model.recommendation.Recommendation;
import com.codedrills.model.recommendation.RecommendationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RandomRecommender extends AbstractRecommender {
  @Override
  public void recommend(RecommendationContext context) {
    List<Problem> unsolved = new ArrayList<>(context.getUnsolved());
    Collections.shuffle(unsolved);
    List<Problem> rec = unsolved.stream()
      .limit(10)
      .collect(Collectors.toList());

    Recommendation recommendation = new Recommendation( "Random",
      "Random practice problems",
      rec,
      1000);
    context.addRecommendation(recommendation);
  }
}
