package com.petersonsantos9k.picpay_desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({TransferException.class, UserException.class})
    public ResponseEntity<StandardError> handleBusinessException(RuntimeException exception) {
        var err = new StandardError(
            LocalDateTime.now(),
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            "Business Exception",
            exception.getMessage()
        );
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(err);
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<StandardError> handleClientException(ClientException exception) {
        var err = new StandardError(
            LocalDateTime.now(),
            HttpStatus.BAD_GATEWAY.value(),
            "Client Exception",
            exception.getMessage()
        );

        return ResponseEntity
            .status(HttpStatus.BAD_GATEWAY)
            .body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidationException(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getDefaultMessage())
            .collect(Collectors.joining("; "));

        var err = new StandardError(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Validation Exception",
            message
        );

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(err);
    }
}
