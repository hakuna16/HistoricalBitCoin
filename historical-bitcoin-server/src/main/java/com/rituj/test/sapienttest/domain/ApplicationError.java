package com.rituj.test.sapienttest.domain;


/** Common error class for handling error message for range applications. */
public record ApplicationError (
  int errorCode,
  String errorMessage
){}
