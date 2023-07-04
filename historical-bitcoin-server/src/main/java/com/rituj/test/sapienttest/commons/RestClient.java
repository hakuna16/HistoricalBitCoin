package com.rituj.test.sapienttest.commons;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.rituj.test.sapienttest.configurations.RequestTraceId;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/** Following class contains RestClient to make http calls like GET, POST. */
@AllArgsConstructor
@Component
public class RestClient {

  private RestTemplate restTemplate;

  /**
   * Following method is useful to make http get call.
   *
   * <p>For making a get call it takes url, request body object, response type required,
   * UriVariables(query params) and customHeader(request specific custom header key, values). It
   * uses {@link RestTemplate#exchange(String, HttpMethod, HttpEntity, ParameterizedTypeReference,
   * Map)} to make get call.
   *
   * @param url {@linkplain String} representation of url to make call.
   * @param responseType {@link ParameterizedTypeReference<T>} response parameterized type ref.
   * @param uriVariables {@link Map<String, ?>} of query params.
   * @param customHeaderFields {@link MultiValueMap} of request specific custom header.
   * @param <T> required return object type.
   * @return required return type object.
   */
  public <T> ResponseEntity<T> get(
      final String url,
      @NotNull final ParameterizedTypeReference<T> responseType,
      @NotNull final Map<String, ?> uriVariables,
      @NotNull final MultiValueMap<String, String> customHeaderFields) {
    Assert.hasText(url, "url can not be null or empty !!");
    Assert.notNull(responseType, "responseType can not be null !!");
    Assert.notNull(uriVariables, "uriVariables can not be null !!");
    Assert.notNull(customHeaderFields, "customHeaderFields can not be null !!");

    final var headers = createCommonHeaderFields();
    headers.addAll(customHeaderFields);
    return restTemplate.exchange(url, GET, new HttpEntity<>(headers), responseType, uriVariables);
  }

  /**
   * Following method creates {@link HttpHeaders} of header fields that will be part of every
   * request i.e. common header fields.
   *
   * @return {@link HttpHeaders} of common header fields.
   */
  private HttpHeaders createCommonHeaderFields() {
    var commonHeader = new HttpHeaders();
    commonHeader.setAccept(List.of(APPLICATION_JSON));
    commonHeader.add("TraceId", RequestTraceId.getTraceId());
    return commonHeader;
  }
}
