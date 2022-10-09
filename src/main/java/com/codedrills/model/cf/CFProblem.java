package com.codedrills.model.cf;

import java.util.List;

public class CFProblem {
  private int contestId;
  private String index;
  private String name;
  private String type;
  private Double points;
  List<String> tags;

  public int getContestId() {
    return contestId;
  }

  public String getIndex() {
    return index;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public Double getPoints() {
    return points;
  }

  public List<String> getTags() {
    return tags;
  }
}
