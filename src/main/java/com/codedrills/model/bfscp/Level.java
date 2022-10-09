package com.codedrills.model.bfscp;

import java.util.List;

public class Level {
  private int val;
  private String colour;
  private List<Module> modules;

  public Level() {
  }

  public int getVal() {
    return val;
  }

  public String getColour() {
    return colour;
  }

  public List<Module> getModules() {
    return modules;
  }

  public void connect() {
    modules.stream()
      .forEach(m -> m.connect(this));
  }
}
