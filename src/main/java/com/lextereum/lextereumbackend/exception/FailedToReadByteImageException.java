package com.lextereum.lextereumbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FailedToReadByteImageException extends RuntimeException{
    public FailedToReadByteImageException(String message) {
        super(message);
    }
}
