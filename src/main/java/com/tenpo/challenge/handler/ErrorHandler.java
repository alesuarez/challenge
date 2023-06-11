package com.tenpo.challenge.handler;

import com.tenpo.challenge.controllers.responses.ErrorResponse;
import com.tenpo.challenge.exceptions.LimitRequestException;
import com.tenpo.challenge.exceptions.NotFoundValueException;
import com.tenpo.challenge.exceptions.ProviderClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProviderClientException.class)
    public ResponseEntity<ErrorResponse> providerClientExceptionHandling(ProviderClientException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(LimitRequestException.class)
    public ResponseEntity<ErrorResponse> limitRequestExceptionHandling(LimitRequestException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(NotFoundValueException.class)
    public ResponseEntity<ErrorResponse> notFoundValueExceptionHandling(NotFoundValueException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
