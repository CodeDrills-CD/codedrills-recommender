package com.codedrills.model.exceptions;

public class InvalidInputException extends CodedrillsException {
  public InvalidInputException() {
  }

  public InvalidInputException(String message) {
    super(message);
  }

  public InvalidInputException(String message, Throwable cause) {
    super(message, cause);
  }
}
