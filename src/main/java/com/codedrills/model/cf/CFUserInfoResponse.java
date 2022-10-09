package com.codedrills.model.cf;

import java.util.List;

public class CFUserInfoResponse extends CFBaseResponse {
  List<CFUser> result;

  public List<CFUser> getResult() {
    return result;
  }
}
