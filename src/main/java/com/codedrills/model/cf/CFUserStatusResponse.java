package com.codedrills.model.cf;

import java.util.List;

public class CFUserStatusResponse extends CFBaseResponse {
  private List<CFSubmission> result;

  public List<CFSubmission> getResult() {
    return result;
  }
}
