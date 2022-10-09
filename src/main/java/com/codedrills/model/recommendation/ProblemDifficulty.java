package com.codedrills.model.recommendation;

public enum ProblemDifficulty {
  EASY,
  MEDIUM,
  HARD;

  public static ProblemDifficulty getDifficulty(double score) {
    if(score <= 40) return EASY;
    if(score <= 80) return MEDIUM;
    return HARD;
  }
}
