package com.uet.car4r.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController: api login & api register
 */
@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
  @PostMapping(path = "/login")
  public ResponseEntity login(@RequestBody String token) {
    return ResponseEntity.ok(token);
  }

  @PostMapping(path = "/register")
  public ResponseEntity register(@RequestBody String token) {
    return ResponseEntity.ok("register");
  }
}
