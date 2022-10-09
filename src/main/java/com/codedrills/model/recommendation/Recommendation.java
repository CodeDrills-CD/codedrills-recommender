package com.codedrills.model.recommendation;

import com.codedrills.model.Problem;
import com.codedrills.util.Helper;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "recommendations")
public class Recommendation {
  @Id
  private String id;
  private String handles;
  private String name;
  private String description;
  @OneToMany(cascade = CascadeType.ALL)
  private List<PracticeProblem> practiceProblems;
  private int rank;

  public Recommendation() {
  }

  public Recommendation(String name, String description, List<Problem> practiceProblems, int rank) {
    this.id = Helper.randomId();
    this.name = name;
    this.description = description;
    this.rank = rank;

    this.practiceProblems = practiceProblems.stream()
      .sorted(Problem::compareByScore)
      .map(p -> new PracticeProblem(this, p, ProblemStatus.UNSOLVED))
      .collect(Collectors.toList());
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public List<PracticeProblem> getPracticeProblems() {
    return practiceProblems;
  }

  public int getRank() {
    return rank;
  }

  public static int compareByRank(Recommendation pr1, Recommendation pr2) {
    if(pr1.getRank() != pr2.getRank()) return new Integer(pr1.getRank()).compareTo(pr2.getRank());
    return pr1.getName().compareTo(pr2.getName());
  }

  public String getId() {
    return id;
  }

  public String getHandles() {
    return handles;
  }

  public void setHandles(String handles) {
    this.handles = handles;
  }
}
