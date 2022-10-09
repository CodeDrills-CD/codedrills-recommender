package com.codedrills.service.recommenders;

import com.codedrills.model.Problem;
import com.codedrills.model.Tag;
import com.codedrills.model.analysis.TagScore;
import com.codedrills.model.recommendation.Recommendation;
import com.codedrills.model.recommendation.RecommendationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Service
public class StrongWeakRecommender extends AbstractRecommender {

  @Override
  public void recommend(RecommendationContext context) {
    context.addRecommendation(
      new Recommendation(
         "Strong Topics",
        "Double down on your strong topics by solving problems in your strong area",
        recommendForTags(context, getTags(context.getUserStats().getStrong())),
        700
      )
    );

    context.addRecommendation(
      new Recommendation(
         "Weak Topics",
        "Improve your range of topics by solving problems in your weak area",
        recommendForTags(context, getTags(context.getUserStats().getWeak())),
        300
      )
    );
  }

  private List<Tag> getTags(List<TagScore> tagScores) {
    return tagScores.stream()
      .map(TagScore::getTag)
      .collect(Collectors.toList());
  }

  private List<Problem> recommendForTags(RecommendationContext context, List<Tag> tags) {
    List<Problem> solved = tags.stream()
      .map(t -> context.getSortedSolvedByTags().getOrDefault(t, emptyList()))
      .flatMap(List::stream)
      .distinct()
      .sorted(Problem::compareByScore)
      .collect(Collectors.toList());

    List<Problem> unsolved = tags.stream()
      .map(t -> context.getSortedUnsolvedByTags().getOrDefault(t, emptyList()))
      .flatMap(List::stream)
      .distinct()
      .sorted(Problem::compareByScore)
      .collect(Collectors.toList());

    return uniformSelect(solved, unsolved, 10);
  }
}
