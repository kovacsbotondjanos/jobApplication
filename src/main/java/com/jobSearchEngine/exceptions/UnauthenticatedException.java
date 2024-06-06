package com.jobSearchEngine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthenticatedException extends RuntimeException{
    public UnauthenticatedException() {
        super("Please provide proper authentication");
    }
}
