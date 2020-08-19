package com.lextereum.lextereumbackend.service.parser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LanguageNotFoundException extends RuntimeException {
    public LanguageNotFoundException() {
        super("Failed to recognize document language");
    }
}
