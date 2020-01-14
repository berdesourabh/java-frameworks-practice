package com.books.app.exception.controller.advice;

import com.books.app.exception.ApiError;
import com.books.app.exception.ApiException;
import com.books.app.exception.ReasonCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @Autowired
    private MessageSource messageSource;


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllException(Exception ex) {
        ApiError error = new ApiError(null, ReasonCode.INTERNAL_SERVER_ERROR.getCode(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleMethodNotAllowedException(ApiException apiException) {
        return new ResponseEntity<ApiError>(apiException.getApiError(), apiException.getApiError().getHttpStatus());
    }
}