package com.jobSearchEngine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException() {
        super("Client with the same name or email address already exists");
    }
}
