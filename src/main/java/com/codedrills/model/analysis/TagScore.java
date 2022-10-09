package com.codedrills.model.analysis;

import com.codedrills.model.Tag;

public class TagScore {
  private final Tag tag;
  private final double score;
  private final int count;

  public TagScore(Tag tag, double score, int count) {
    this.tag = tag;
    this.score = score;
    this.count = count;
  }

  public Tag getTag() {
    return tag;
  }

  public double getScore() {
    return score;
  }

  public int getCount() {
    return count;
  }
}
