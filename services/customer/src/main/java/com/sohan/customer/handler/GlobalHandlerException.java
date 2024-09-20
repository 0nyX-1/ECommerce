package com.sohan.customer.handler;

import com.sohan.customer.Exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handle(CustomerNotFoundException cnfe){
       return ResponseEntity
               .status(HttpStatus.NOT_FOUND)
               .body(cnfe.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException methodArgumentNotValidException){
        Map<String, String> stringStringHashMap = new HashMap<>();
        methodArgumentNotValidException
                .getAllErrors()
                .forEach(error->{
                    String field = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    stringStringHashMap.put(field,errorMessage);
                });
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(stringStringHashMap));
    }

}
