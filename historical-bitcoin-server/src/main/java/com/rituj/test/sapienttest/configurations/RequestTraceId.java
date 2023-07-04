package com.rituj.test.sapienttest.configurations;

/** Class to create a request context for application. */
public class RequestTraceId {

  private RequestTraceId() {}

  public static final String TRACE_ID = "TraceId";
  private static final ThreadLocal<String> traceIdContext = new ThreadLocal<>();

  public static String getTraceId() {
    return traceIdContext.get();
  }

  public static void setTraceId(final String traceId) {
    traceIdContext.set(traceId);
  }
}
