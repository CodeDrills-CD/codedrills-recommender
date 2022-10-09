package com.codedrills.model;

import static com.codedrills.model.Tag.TagType.TECHNIQUE;
import static com.codedrills.model.Tag.TagType.TOPIC;

// WARNING ORDER MATTERS ONLY APPEND
public enum Tag {
  BinarySearch("Binary Search", TECHNIQUE),
  BruteForce("Brute Force", TECHNIQUE),
  DataStructures("Data Structures", TECHNIQUE),
  DivideConquer("Divide & Conquer", TECHNIQUE),
  DynamicProgramming("Dynamic Programming", TECHNIQUE),
  Implementation("Implementation", TECHNIQUE),
  Greedy("Greedy", TECHNIQUE),

  Adhoc("Adhoc", TOPIC),
  Combinatorics("Combinatorics", TOPIC),
  FFT("FFT", TOPIC),
  Flows("Flows & Matching", TOPIC),
  Games("Games", TOPIC),
  Geometry("Geometry", TOPIC),
  GraphsTrees("Graphs & Trees", TOPIC),
  Math("Math", TOPIC),
  Matrices("Matrices", TOPIC),
  NumberTheory("Number Theory", TOPIC),
  Probabilities("Probabilities", TOPIC),
  Strings("Strings", TOPIC),
  Hashing("Hashing", TOPIC),
  Bitmasks("Bitmasks", TOPIC),

  UNTAGGED("UNTAGGED", TagType.UNTAGGED);

  private String fullName;
  private TagType type;

  Tag(String fullName, TagType type) {
    this.fullName = fullName;
    this.type = type;
  }

  public String getFullName() {
    return fullName;
  }

  public TagType getType() {
    return type;
  }

  @Override
  public String toString() {
    return getFullName();
  }

  public enum TagType {
    TECHNIQUE,
    TOPIC,
    UNTAGGED
  }


}
