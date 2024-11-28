package com.uet.car4r.Controller;

import com.uet.car4r.Constant.TypeMessage;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uet.car4r.DTO.ResponseDTO;
import com.uet.car4r.DTO.UserDTO;

/**
 * UserController: api login & api register
 */
@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
  private static Logger logger = LoggerFactory.getLogger(UserController.class);

  @PostMapping(path = "/login")
  public ResponseEntity login(@RequestBody String token) {
    logger.info("Login(): token: " + token);
    return ResponseEntity.ok(token);
  }

  @PostMapping(path = "/register")
  public ResponseEntity<ResponseDTO> register(@RequestBody UserDTO userDTO) {
    logger.info("email: " + userDTO.getEmail() + "\n" + "password: " + userDTO.getPassword());
    ResponseDTO responseDTO = ResponseDTO.builder()
        .endpoint("/api/v1/users/register")
        .message(TypeMessage.SUCCESS)
        .messageDetail("").build();
    return ResponseEntity.ok(responseDTO);
  }
}
