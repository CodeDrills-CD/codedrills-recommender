package com.codedrills.model.cc;

import java.util.Map;

public class CCProblemFetchResponse {
  private Map<String, CCProblem> all_problems;
  private String message;

  public Map<String, CCProblem> getAll_problems() {
    return all_problems;
  }

  public String getMessage() {
    return message;
  }
}
