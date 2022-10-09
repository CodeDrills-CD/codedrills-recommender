package com.codedrills.model.cf;

public class CFSubmission {
  private String id;
  private int contestId;
  private long creationTimeSeconds;
  private long relativeTimeSeconds;
  CFProblem problem;
  CFAuthor author;
  String programmingLanguage;
  String verdict;
  String testset;
  int passedTestCount;
  int timeConsummedMillis;
  int memoryConsumedBytes;

  public String getProgrammingLanguage() {
    return programmingLanguage;
  }

  public String getVerdict() {
    return verdict;
  }

  public String getTestset() {
    return testset;
  }

  public int getPassedTestCount() {
    return passedTestCount;
  }

  public int getTimeConsummedMillis() {
    return timeConsummedMillis;
  }

  public int getMemoryConsumedBytes() {
    return memoryConsumedBytes;
  }

  public String getId() {
    return id;
  }

  public int getContestId() {
    return contestId;
  }

  public long getCreationTimeSeconds() {
    return creationTimeSeconds;
  }

  public long getRelativeTimeSeconds() {
    return relativeTimeSeconds;
  }

  public CFProblem getProblem() {
    return problem;
  }

  public CFAuthor getAuthor() {
    return author;
  }
}
