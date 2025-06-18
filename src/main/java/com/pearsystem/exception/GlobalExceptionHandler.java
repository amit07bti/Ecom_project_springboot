package com.pearsystem.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
     @ExceptionHandler(ResourceNotFoundException.class)
    public String HandelResourceNotFoundException(ResourceNotFoundException ex){
  return ex.getMessage();
    }
}
