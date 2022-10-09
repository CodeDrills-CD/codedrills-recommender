package com.codedrills.model.cf;

import java.util.List;

public class CFProblemSet {
  List<CFProblem> problems;
  List<CFProblemStat> problemStatistics;

  public List<CFProblem> getProblems() {
    return problems;
  }

  public List<CFProblemStat> getProblemStatistics() {
    return problemStatistics;
  }
}
