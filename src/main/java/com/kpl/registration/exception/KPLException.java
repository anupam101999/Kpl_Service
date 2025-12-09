package com.kpl.registration.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class KPLException extends RuntimeException {
    private final String message;
    @Getter
    private final HttpStatus httpStatus;

    @Override
    public String getMessage() {
        return message;
    }

    public KPLException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
