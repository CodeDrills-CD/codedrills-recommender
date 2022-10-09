package com.codedrills.model.bfscp;

import java.util.List;

public class Module {
  private String id;
  private String title;
  private String description;
  private List<Lecture> lectures;
  private List<Link> prereqs;
  private List<Link> references;
  private List<String> problems;
  private Boolean completed;
  private Level level;

  public Module() {
  }

  public String getTitle() {
    return title;
  }

  public String getId() {
    return id;
  }

  public List<Lecture> getLectures() {
    return lectures;
  }

  public List<Link> getPrereqs() {
    return prereqs;
  }

  public List<Link> getReferences() {
    return references;
  }

  public List<String> getProblems() {
    return problems;
  }

  public Level getLevel() {
    return level;
  }

  public Boolean getCompleted() {
    return completed;
  }

  public String getDescription() {
    return description;
  }

  public void connect(Level level) {
    this.level = level;
    lectures.stream()
      .forEach(l -> l.connect(this));
  }
}
