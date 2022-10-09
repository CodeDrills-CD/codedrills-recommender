package com.codedrills.model;

import com.codedrills.model.recommendation.ProblemDifficulty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

import static com.google.common.base.Strings.isNullOrEmpty;

@Entity
@Table(name="problems")
public class Problem {
  @Id @NotNull
  private final String uid;

  @NotNull
  @Column(length = 64)
  private final String url;

  @NotNull
  @Column(length = 128)
  private final String name;

  @NotNull
  @ElementCollection(targetClass = Tag.class)
  @Enumerated(EnumType.ORDINAL)
  private final Set<Tag> tags;

  @NotNull
  private final int solved;

  @NotNull
  @Enumerated(EnumType.ORDINAL)
  private final Site site;

  @Transient
  private double score;

  @Transient
  private ProblemDifficulty difficulty;

  public Problem() {
    uid = null;
    url = null;
    name = null;
    tags = null;
    solved = -1;
    site = null;
    score = -1;
  }

  public Problem(Site site, String uid, String url, String name, Collection<Tag> tags, int solved) {
    this.site = site;
    this.uid = uid;
    this.url = url;
    this.name = name;
    this.tags = new HashSet<>(tags);
    this.solved = solved;
  }

  public Site getSite() {
    return site;
  }

  public String getUrl() {
    return url;
  }

  public Set<Tag> getTags() {
    return tags;
  }

  public String getUid() {
    return uid;
  }

  public String getName() {
    return name;
  }

  public int getSolved() {
    return solved;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }

  public ProblemDifficulty getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(ProblemDifficulty difficulty) {
    this.difficulty = difficulty;
  }

  public boolean isValid() {
    return !isNullOrEmpty(url) && !isNullOrEmpty(name) && !isNullOrEmpty(uid);
  }

  public class CompareByUrl implements Comparator<Problem> {
    @Override
    public int compare(Problem p1, Problem p2) {
      return p1.getUrl().compareTo(p2.getUrl());
    }
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) return true;
    if(o == null || getClass() != o.getClass()) return false;

    Problem problem = (Problem) o;

    if(!getUid().equals(problem.getUid())) return false;
    if(!getUrl().equals(problem.getUrl())) return false;
    if(!getName().equals(problem.getName())) return false;
    return getSite() == problem.getSite();
  }

  @Override
  public int hashCode() {
    int result = getUid().hashCode();
    result = 31 * result + getUrl().hashCode();
    result = 31 * result + getName().hashCode();
    result = 31 * result + getSite().hashCode();
    return result;
  }

  public static int compareByScore(Problem p1, Problem p2) {
    if(p1 == null && p2 == null) return 0;
    if(p1 == null) return -1;
    if(p2 == null) return 1;
    return new Double(p1.getScore()).compareTo(p2.getScore());
  }
}
