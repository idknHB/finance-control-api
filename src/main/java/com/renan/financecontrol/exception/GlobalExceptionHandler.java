package com.renan.financecontrol.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResouceNotFoundException.class
    )
    public ResponseEntity<String> handleNotFound(
            ResouceNotFoundException ex
    ){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(
            EmailJaCadastradoException.class
    )
    public ResponseEntity<String> handleEmailJaCadastrado(
            EmailJaCadastradoException ex
    ){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(
            CredenciaisInvalidasException.class
    )
    public ResponseEntity<String> handleCredenciaisInvalidas(
            CredenciaisInvalidasException ex
    ){
        return  ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ex.getMessage());
    }
}
