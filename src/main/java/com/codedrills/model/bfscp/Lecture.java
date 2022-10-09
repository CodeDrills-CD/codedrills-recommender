package com.codedrills.model.bfscp;

public class Lecture {
  private String id;
  private String name;
  private String description;
  private String url;
  private String releaseExpected;
  private Boolean slides;
  private Module module;

  public Lecture() {
  }

  public String getName() {
    return name;
  }

  public String getUrl() {
    return url;
  }

  public String getReleaseExpected() {
    return releaseExpected;
  }

  public String getDescription() {
    return description;
  }

  public String getId() {
    return id;
  }

  public Boolean getSlides() {
    return slides;
  }

  public Module getModule() {
    return module;
  }

  public void connect(Module module) {
    this.module = module;
  }
}
