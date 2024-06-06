package com.jobSearchEngine.controllers;

import com.jobSearchEngine.exceptions.ClientAlreadyExistsException;
import com.jobSearchEngine.exceptions.InvalidFormDataException;
import com.jobSearchEngine.exceptions.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<String> handleException(ClientAlreadyExistsException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidFormDataException.class)
    public ResponseEntity<String> handleException(InvalidFormDataException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity<String> handleException(UnauthenticatedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
