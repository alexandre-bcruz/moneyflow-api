package com.alexandrebcruz.moneyflow.adapters.in.web.advice;

import com.alexandrebcruz.moneyflow.domain.exception.CategoryNotFoundException;
import com.alexandrebcruz.moneyflow.domain.exception.TransactionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex){
        return ResponseEntity.badRequest().body(
                Map.of("error", "validation_error",
                        "timestamp", Instant.now())
        );
    }
    @ExceptionHandler(IllegalStateException.class)
    ResponseEntity<?> handleConflict(IllegalStateException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of("error", ex.getMessage(),
                        "timestamp", Instant.now())
        );
    }
    @ExceptionHandler({CategoryNotFoundException.class, TransactionNotFoundException.class})
    ResponseEntity<?> handleConflict(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("not_found", ex.getMessage(),
                        "timestamp", Instant.now())
        );
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<?> handleGeneric(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Map.of(
                        "error", "internal_error",
                        "timestamp", Instant.now()
                )
        );
    }

}
