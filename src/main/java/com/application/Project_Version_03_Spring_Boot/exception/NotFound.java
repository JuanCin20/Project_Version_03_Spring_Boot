package com.application.Project_Version_03_Spring_Boot.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFound extends RuntimeException {

    public NotFound(String message) {
        super(message);
    }
}