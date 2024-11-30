package com.uet.car4r.controller;

import com.uet.car4r.constant.TypeMessage;
import com.uet.car4r.utils.JwtUtil;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uet.car4r.dto.ResponseDTO;
import com.uet.car4r.dto.UserDTO;

/**
 * UserController: api login & api register
 */
@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
  private static Logger logger = LoggerFactory.getLogger(UserController.class);
  private final JwtUtil jwtUtil;

  @Autowired
  public UserController(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @PostMapping(path = "/login")
  public ResponseEntity login(@RequestBody String token) {
    System.out.println(jwtUtil);
    logger.info("Login(): token: " + token);
    return ResponseEntity.ok(token);
  }

  @PostMapping(path = "/register")
  public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
    System.out.println(jwtUtil);
    UserDetails userDetails = (UserDetails) userDTO;
    return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
  }
}
