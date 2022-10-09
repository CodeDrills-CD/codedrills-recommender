package com.codedrills.model.exceptions;

public class CodedrillsException extends RuntimeException {
  public CodedrillsException() {
  }

  public CodedrillsException(String message) {
    super(message);
  }

  public CodedrillsException(String message, Throwable cause) {
    super(message, cause);
  }

  public CodedrillsException(Throwable cause) {
    super(cause);
  }

  public CodedrillsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
