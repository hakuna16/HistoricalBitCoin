package com.rituj.test.sapienttest.exceptions;

import java.io.Serial;

/** Exception class can be used whenever and wherever data is empty or null for a request. */
public class DataNotFoundException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  public DataNotFoundException() {
    super();
  }

  public DataNotFoundException(final String message) {
    super(message);
  }

  public DataNotFoundException(final String message, final Throwable throwable) {
    super(message, throwable);
  }
}
