package com.codedrills.model.analysis;

import com.codedrills.model.Handle;
import com.codedrills.model.Problem;
import com.codedrills.model.Tag;

import java.util.List;
import java.util.Map;

public class ComparatorResult {
  private String handlesA;
  private String handlesB;
  private List<Tag> strongerA, strongerB;
  private List<Problem> onlySolvedByA, onlySolvedByB;
  private Map<Handle, Rating> ratingsA, ratingsB;
  private boolean same;

  public ComparatorResult() {
  }

  public ComparatorResult(String handlesA, String handlesB) {
    this.handlesA = handlesA;
    this.handlesB = handlesB;
  }

  public String getHandlesA() {
    return handlesA;
  }

  public void setHandlesA(String handlesA) {
    this.handlesA = handlesA;
  }

  public String getHandlesB() {
    return handlesB;
  }

  public void setHandlesB(String handlesB) {
    this.handlesB = handlesB;
  }

  public List<Tag> getStrongerA() {
    return strongerA;
  }

  public void setStrongerA(List<Tag> strongerA) {
    this.strongerA = strongerA;
  }

  public List<Tag> getStrongerB() {
    return strongerB;
  }

  public void setStrongerB(List<Tag> strongerB) {
    this.strongerB = strongerB;
  }

  public List<Problem> getOnlySolvedByA() {
    return onlySolvedByA;
  }

  public void setOnlySolvedByA(List<Problem> onlySolvedByA) {
    this.onlySolvedByA = onlySolvedByA;
  }

  public List<Problem> getOnlySolvedByB() {
    return onlySolvedByB;
  }

  public void setOnlySolvedByB(List<Problem> onlySolvedByB) {
    this.onlySolvedByB = onlySolvedByB;
  }

  public Map<Handle, Rating> getRatingsA() {
    return ratingsA;
  }

  public void setRatingsA(Map<Handle, Rating> ratingsA) {
    this.ratingsA = ratingsA;
  }

  public Map<Handle, Rating> getRatingsB() {
    return ratingsB;
  }

  public void setRatingsB(Map<Handle, Rating> ratingsB) {
    this.ratingsB = ratingsB;
  }

  public boolean areSame() {
    return same;
  }

  public void setSame(boolean same) {
    this.same = same;
  }
}
