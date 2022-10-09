package com.codedrills.model.analysis;

public class Rating {
  private Double current, highest;

  public Rating(Double current, Double highest) {
    this.current = current;
    this.highest = highest;
  }

  public Double getCurrent() {
    return current;
  }

  public Double getHighest() {
    return highest;
  }
}
