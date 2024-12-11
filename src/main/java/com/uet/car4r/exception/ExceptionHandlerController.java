package com.uet.car4r.exception;

import com.uet.car4r.constant.TypeMessage;
import com.uet.car4r.dto.NotificationDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler(value = CustomException.class)
  public ResponseEntity exceptionHandle(CustomException customException, HttpServletRequest request) {
    return ResponseEntity.internalServerError().body(NotificationDTO
                                                         .builder()
                                                         .endpoint(request.getRequestURI())
                                                         .message(TypeMessage.FAIL)
                                                         .messageDetail(customException.getMessage())
                                                         .build());
  }

}
