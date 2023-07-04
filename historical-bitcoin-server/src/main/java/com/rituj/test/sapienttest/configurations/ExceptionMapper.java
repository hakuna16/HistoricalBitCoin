package com.rituj.test.sapienttest.configurations;

import com.rituj.test.sapienttest.commons.Constants;
import com.rituj.test.sapienttest.domain.ApplicationError;
import com.rituj.test.sapienttest.exceptions.DataNotFoundException;
import com.rituj.test.sapienttest.exceptions.InvalidDataException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.ws.WebServiceException;

/**
 * This is a common exception mapper class,where we will be handling exception and returning common
 * error response with appropriate {@link HttpStatus}. This is based on the {@linkplain "Rest"}
 * principle.
 */
@ControllerAdvice
public class ExceptionMapper extends ResponseEntityExceptionHandler {

  /**
   * This method will handle the {@link DataNotFoundException}.
   *
   * @param dataNotFoundException user defined exception class.
   * @return {@link HttpStatus#NO_CONTENT} status code wrapped with {@link ApplicationError}.
   */
  @ExceptionHandler(DataNotFoundException.class)
  public ResponseEntity<Object> handleDataNotFound(
      @NotNull final DataNotFoundException dataNotFoundException) {
    final var error = StringUtils.defaultString(dataNotFoundException.getMessage());
    final var applicationError = buildError(HttpStatus.NO_CONTENT.value(), error);
    return ResponseEntity.status(HttpStatus.NO_CONTENT)
        .header(Constants.ERROR_MESSAGE_HEADER, applicationError.errorMessage())
        .build();
  }

  /**
   * This method will handle the {@link InvalidDataException}.
   *
   * @param invalidDataException user defined exception class.
   * @return {@link HttpStatus#BAD_REQUEST} status code wrapped with {@link ApplicationError}.
   */
  @ExceptionHandler(InvalidDataException.class)
  public ResponseEntity<Object> handleInvalidData(
      @NotNull final InvalidDataException invalidDataException) {
    final var applicationError =
        buildError(HttpStatus.BAD_REQUEST.value(), invalidDataException.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
  }

  /**
   * This method will handle the {@link WebServiceException}.
   *
   * @param webServiceException {@link WebServiceException} represents user defined exception class.
   * @return {@link HttpStatus#INTERNAL_SERVER_ERROR} status code wrapped with {@link
   *     ApplicationError}.
   */
  @ExceptionHandler(WebServiceException.class)
  public ResponseEntity<Object> handleWebServiceException(
      @NotNull final WebServiceException webServiceException) {
    final var applicationError =
        buildError(HttpStatus.INTERNAL_SERVER_ERROR.value(), webServiceException.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
  }

  /**
   * This is the method which is used to build the exception based {@link Error} pojo class.
   *
   * @param status {@link HttpStatus} code which we need to set.
   * @param errorMessage {@linkplain String} representation of the error message.
   * @return {@link ApplicationError} with errorCode and errorMessage.
   */
  private ApplicationError buildError(
      @NotNull final int status, @NotBlank final String errorMessage) {
    return new ApplicationError(status, errorMessage);
  }
}
