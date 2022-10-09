package com.codedrills.service.recommenders;

import com.codedrills.model.Problem;
import com.codedrills.model.Tag;
import com.codedrills.model.recommendation.Recommendation;
import com.codedrills.model.recommendation.RecommendationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

@Service
public class TagRecommender extends AbstractRecommender {
  private final static Map<Tag.TagType, Integer> tagTypeRank;

  static {
    tagTypeRank = new HashMap<>();
    tagTypeRank.put(Tag.TagType.TECHNIQUE, 825);
    tagTypeRank.put(Tag.TagType.TOPIC, 850);
    tagTypeRank.put(Tag.TagType.UNTAGGED, 900);
  }

  @Override
  public void recommend(RecommendationContext context) {
    context.getProblemsByTags().entrySet()
      .stream()
      .forEach(e -> recommendForTag(context, e.getKey()));
  }

  private void recommendForTag(RecommendationContext context, Tag tag) {
    List<Problem> solved = context.getSortedSolvedByTags().getOrDefault(tag, emptyList());
    List<Problem> unsolved = context.getSortedUnsolvedByTags().getOrDefault(tag, emptyList());

    Recommendation recommendation = new Recommendation(
      tag.getFullName(),
      "Practice problems for " + tag.getFullName(),
      uniformSelect(solved, unsolved, 10),
      tagTypeRank.get(tag.getType())
    );

    context.addRecommendation(recommendation);
  }

}
