package com.uet.car4r.controller;

import com.uet.car4r.dto.NotificationDTO;
import com.uet.car4r.dto.RequestTokenDTO;
import com.uet.car4r.repository.TokenRepository;
import com.uet.car4r.service.TokenService;
import com.uet.car4r.service.UserService;
import com.uet.car4r.utils.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uet.car4r.dto.UserDTO;
import org.springframework.web.servlet.view.RedirectView;

/**
 * UserController: api login & api register
 */
@RestController
@RequestMapping(path = "/v1/users")
@RequiredArgsConstructor
public class UserController {
  private static Logger logger = LoggerFactory.getLogger(UserController.class);
  private final JwtUtil jwtUtil;
  private final UserService userService;
  private final TokenRepository tokenRepository;
  private final TokenService tokenService;

  @PostMapping(path = "/login")
  public ResponseEntity<Optional> login(@RequestBody UserDTO userDTO) {
    logger.info("UserController -> login(): " + userDTO.getEmail());
    return ResponseEntity.ok(userService.login(userDTO));
  }

  @PostMapping(path = "/register")
  public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
    logger.info("register(): " + userDTO.getEmail());
    return ResponseEntity.ok(userService.register(userDTO));
  }

  @GetMapping(path = "/verifyRegister/{token}")
  public ResponseEntity verifyRegister(@PathVariable String token) throws IOException {
    logger.info("verifyRegister()");
    Optional valueVerify = userService.verifyRegister(token);

    if (!valueVerify.get().getClass().equals(NotificationDTO.class)) {
      new RedirectView("/home");
    }

    return ResponseEntity.ok(valueVerify);
  }

  @GetMapping(path = "/resetPassword/{email}")
  public ResponseEntity<?> resetPassword(@PathVariable String email) {
    logger.info("resetPassword(): " + email);
    return ResponseEntity.ok(userService.resetPassword(email));
  }

  @PostMapping(path = "/refreshToken")
  public ResponseEntity refreshAccessToken(HttpServletRequest request) {
    logger.info("refreshAccessToken()");
    Cookie cookies[] = request.getCookies();
    String refreshToken = Arrays.stream(request.getCookies())
        .filter(cookie -> "refreshToken".equals(cookie.getName()))
        .findFirst()
        .map(cookie -> cookie.getValue())
        .orElse(null);


    if (refreshToken == null || refreshToken.isEmpty()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please Log In Again Or Sign Up");
    }

    Optional res = tokenService.refreshAccessToken(refreshToken);

    return ResponseEntity.ok(res);
   }

  @PostMapping(path = "/oauth/google")
  public ResponseEntity authWithGoogle(@RequestBody UserDTO userDTO) {
    Optional res = userService.authWithGoogle(userDTO);
    return ResponseEntity.ok(res);
  }

  @GetMapping("/api/v1/users/{id}")
  public ResponseEntity getUserById(@PathVariable String id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }

  @PostMapping(path = "/verifyAccessToken")
  public ResponseEntity verifyAccessToken(@RequestBody RequestTokenDTO accessToken) {
    return ResponseEntity.ok(tokenService.validateAccessToken(accessToken.getToken()));
  }

}
