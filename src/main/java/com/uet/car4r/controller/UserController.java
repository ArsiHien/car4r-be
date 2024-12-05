package com.uet.car4r.controller;

import com.uet.car4r.constant.TypeMessage;
import com.uet.car4r.dto.NotificationDTO;
import com.uet.car4r.service.UserService;
import com.uet.car4r.utils.JwtUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uet.car4r.dto.UserDTO;
import org.springframework.web.servlet.view.RedirectView;

/**
 * UserController: api login & api register
 */
@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {
  private static Logger logger = LoggerFactory.getLogger(UserController.class);
  private final JwtUtil jwtUtil;
  private final UserService userService;

  @PostMapping(path = "/login")
  public ResponseEntity login(@RequestBody UserDTO userDTO) {
    logger.info("UserController.login(): " + userDTO.getEmail());
    return ResponseEntity.ok("");
  }

  @PostMapping(path = "/register")
  public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
    logger.info("UserController.register(): " + userDTO.getEmail());
    return ResponseEntity.ok(userService.register(userDTO));
  }

  @PostMapping(path = "/resetPassword")
  public ResponseEntity<?> resetPassword(@RequestBody UserDTO userDTO) {
    logger.info("UserController.resetPassword(): " + userDTO.getEmail());
    return ResponseEntity.ok(userService.register(userDTO));
  }

  @GetMapping(path = "/verify/{token}")
  public ResponseEntity verify(@PathVariable String token) throws IOException {
    System.out.println(token);
    NotificationDTO notificationDTO = userService.verify(token);

    if (notificationDTO.getMessage().equals(TypeMessage.SUCCESS)) {
      new RedirectView("/home");
    }

    return ResponseEntity.ok(notificationDTO);
  }

}
