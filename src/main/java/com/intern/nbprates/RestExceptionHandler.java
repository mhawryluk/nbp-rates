package com.intern.nbprates;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.web.client.HttpClientErrorException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    protected ResponseEntity<Object> handleHttpNotFound(Throwable ex) {
        String errorMessage = "404 Not found.";
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, errorMessage, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}