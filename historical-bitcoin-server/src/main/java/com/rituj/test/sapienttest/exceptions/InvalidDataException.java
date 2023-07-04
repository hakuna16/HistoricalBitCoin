package com.rituj.test.sapienttest.exceptions;

import java.io.Serial;

/**
 * Runtime exception denoting invalid data. Valid cases for this exception are incorrect input data,
 * Json parsing failure due to incorrect data, date parsing failure due to incorrect data etc.
 */
public class InvalidDataException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  public InvalidDataException() {
    super();
  }

  public InvalidDataException(final String message) {
    super(message);
  }

  public InvalidDataException(final String message, final Throwable throwable) {
    super(message, throwable);
  }
}
