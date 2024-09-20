package com.sohan.products.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handle(EntityNotFoundException entityNotFoundException){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(entityNotFoundException.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle
            (MethodArgumentNotValidException ex){
        HashMap<String, String> errorHashMap = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error->{
                    String field = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errorHashMap.put(field,errorMessage);
                });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(errorHashMap));
    }
}
