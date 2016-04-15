package com.mbds.nfc.web.controllers;

import com.mbds.nfc.exception.InvalidInputDataException;
import com.mbds.nfc.exception.UnableToSendNotificationException;
import com.mbds.nfc.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler
    public
    @ResponseBody
    ResponseEntity<ErrorResponse> handleException(InvalidInputDataException e) {
        return getErrorResponse(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public
    @ResponseBody
    ResponseEntity<ErrorResponse> handleException(UnableToSendNotificationException e) {
        return getErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> getErrorResponse(Exception e, HttpStatus status) {
        final ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }
}
