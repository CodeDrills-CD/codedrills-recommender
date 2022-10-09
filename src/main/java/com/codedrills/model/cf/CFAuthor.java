package com.codedrills.model.cf;

import java.util.List;

public class CFAuthor {
  private int contestId;
  private List<CFMember> members;

  public int getContestId() {
    return contestId;
  }

  public List<CFMember> getMembers() {
    return members;
  }
}
