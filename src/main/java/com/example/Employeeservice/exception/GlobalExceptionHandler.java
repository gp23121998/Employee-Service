package com.example.Employeeservice.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ApiResponse api = ApiResponse.builder().message(message).success(false).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<ApiResponse>(api, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ApiResponse> httpClientErrorException(HttpClientErrorException httpResponse) {
        if (httpResponse.getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR) {
            ApiResponse api = ApiResponse.builder().message(httpResponse.getMessage()).success(false).status(HttpStatus.NOT_FOUND).build();
            return new ResponseEntity<ApiResponse>(api, HttpStatus.INTERNAL_SERVER_ERROR);
            // handle SERVER_ERROR
        } else if (httpResponse.getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR) {
            // handle CLIENT_ERROR
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
               ApiResponse api = ApiResponse.builder().message(httpResponse.getMessage()).success(false).status(HttpStatus.NOT_FOUND).build();
                return new ResponseEntity<ApiResponse>(api, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<ApiResponse>(new ApiResponse(), HttpStatus.EXPECTATION_FAILED);
    }
}