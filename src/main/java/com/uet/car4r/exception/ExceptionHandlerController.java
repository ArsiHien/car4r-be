package com.uet.car4r.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler(value = CustomException.class)
  public ResponseEntity<?> exceptionHandle(CustomException customException, HttpServletRequest request) {
    return null;
  }

}
