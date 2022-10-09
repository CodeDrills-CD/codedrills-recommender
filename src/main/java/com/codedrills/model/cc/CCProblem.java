package com.codedrills.model.cc;

import java.util.List;

public class CCProblem {
  private String code;
  List<String> tags;
  String author;
  int attempted_by;
  int solved_by;
  String name;

  public String getCode() {
    return code;
  }

  public List<String> getTags() {
    return tags;
  }

  public String getAuthor() {
    return author;
  }

  public int getAttempted_by() {
    return attempted_by;
  }

  public int getSolved_by() {
    return solved_by;
  }

  public String getName() {
    return name;
  }
}
