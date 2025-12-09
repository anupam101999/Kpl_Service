package com.kpl.registration.exception;

import com.kpl.registration.dto.NewLogic.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(KPLException.class)
    public ResponseEntity<ErrorResponse> handleKplException(KPLException ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                ex.getHttpStatus().value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, ex.getHttpStatus());
    }
}
