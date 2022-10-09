package com.codedrills.model;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

// WARNING ORDER MATTERS ONLY APPEND
public enum Site {
  CODECHEF(singletonList("cc")),
  CODEFORCES(asList("cf", "")),
  SPOJ(singletonList("sp"));

  private List<String> aliases;


  Site(List<String> aliases) {
    this.aliases = aliases;
  }

  public List<String> getAliases() {
    return aliases;
  }

  public String getShortName() {
    return getAliases().get(0);
  }
}
