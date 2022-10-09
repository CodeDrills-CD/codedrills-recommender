package com.codedrills.model.recommendation;

import com.codedrills.model.Problem;

import javax.persistence.*;

@Entity(name = "practice_problems")
public class PracticeProblem {
  @Id @GeneratedValue
  private long id;
  @ManyToOne
  private Recommendation recommendation;
  @ManyToOne
  private Problem problem;
  @Enumerated(value = EnumType.ORDINAL)
  private ProblemStatus status;

  public PracticeProblem() {
  }

  public PracticeProblem(Recommendation recommendation, Problem problem, ProblemStatus status) {
    this.recommendation = recommendation;
    this.problem = problem;
    this.status = status;
  }

  public Recommendation getRecommendation() {
    return recommendation;
  }

  public Problem getProblem() {
    return problem;
  }

  public ProblemStatus getStatus() {
    return status;
  }

  public void setStatus(ProblemStatus status) {
    this.status = status;
  }

  public long getId() {
    return id;
  }
}
