package com.example.Employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse>resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
       String message=ex.getMessage();
       ApiResponse api = ApiResponse.builder().message(message).success(false).status(HttpStatus.NOT_FOUND).build();
       return new ResponseEntity<ApiResponse>(api, HttpStatus.NOT_FOUND);
    }


}
