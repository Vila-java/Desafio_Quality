package com.example.desafio_teste.exception.handler;

import com.example.desafio_teste.exception.ExceptionDetails;
import com.example.desafio_teste.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionGenericsHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundException(Exception ex) {
        return new ResponseEntity<>(
            ExceptionDetails.builder()
                    .title("Not found")
                    .status(HttpStatus.NOT_FOUND.value())
                    .message(ex.getMessage())
                    .timestamp(LocalDateTime.now())
                    .build(),
            HttpStatus.NOT_FOUND
        );
    }
}
