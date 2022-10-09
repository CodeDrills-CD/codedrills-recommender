package com.codedrills.service.recommenders;

import com.codedrills.model.Problem;
import com.codedrills.model.recommendation.RecommendationContext;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractRecommender implements RecommenderInterface {
  @Override
  public abstract void recommend(RecommendationContext context);

  protected List<Problem> uniformSelect(List<Problem> solved, List<Problem> unsolved, int len) {

    if(solved.size() <= 3) {
      return unsolved.stream()
        .limit(len)
        .collect(Collectors.toList());
    }

    List<Problem> easy =  getEasy(solved, unsolved).collect(Collectors.toList());
    List<Problem> medium = getMedium(solved, unsolved).collect(Collectors.toList());
    List<Problem> hard = getHard(solved, unsolved).collect(Collectors.toList());

    int easyLen = Math.min(len / 3, easy.size());
    int hardLen = Math.min(len / 3, hard.size());

    int maxLen = Math.min(len, easy.size() + medium.size() + hard.size());
    int rem = Math.max(0, maxLen - easyLen - hardLen - medium.size());
    int fromHard = Math.min(rem, hard.size() - hardLen);
    rem -= fromHard;
    hardLen += fromHard;

    int fromEasy = Math.min(rem, easy.size() - easyLen);
    rem -= fromEasy;
    easyLen += fromEasy;


    return Stream.concat(
      Stream.concat(
        easy.stream().limit(easyLen),
        hard.stream().limit(hardLen)
      ),
      medium.stream()
    )
      .distinct()
      .limit(len)
      .collect(Collectors.toList());
  }

  protected Stream<Problem> getEasy(List<Problem> solved, List<Problem> unsolved) {
    return selectProblems(0, 70, solved, unsolved);
  }

  protected Stream<Problem> getHard(List<Problem> solved, List<Problem> unsolved) {
    return selectProblems(94, 100, solved, unsolved);
  }

  protected Stream<Problem> getMedium(List<Problem> solved, List<Problem> unsolved) {
    return selectProblems(71, 93, solved, unsolved);
  }

  private Stream<Problem> selectProblems(double start, double end, List<Problem> solved, List<Problem> unsolved) {
    double lowScore = getIth(solved, start);
    double highScore = getIth(solved, end);

    List<Problem> slice = unsolved.stream()
      .filter(p -> p.getScore() >= lowScore && p.getScore() <= highScore)
      .collect(Collectors.toList());
    Collections.shuffle(slice);

    return slice.stream();
  }

  private double getIth(List<Problem> solved, double start) {
    int idx = (int) Math.floor(solved.size() * start / 100.0);
    if(idx < 0) idx = 0;
    if(idx >= solved.size()) return 100;

    return solved.get(idx).getScore();
  }

}
