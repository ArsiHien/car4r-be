package com.uet.car4r.service;

import com.uet.car4r.constant.TypeMessage;
import com.uet.car4r.dto.NotificationDTO;
import com.uet.car4r.dto.UserDTO;
import com.uet.car4r.repository.UserRepository;
import com.uet.car4r.utils.JwtUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import com.uet.car4r.constant.TypeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
  private UserRepository userRepository;
  private EmailService emailService;
  private JwtUtil jwtUtil;

  public void login(UserDTO userDTO) {

  }

  public NotificationDTO register(UserDTO userDTO) {
    String email = userDTO.getEmail();

    if (userRepository.existsUsersByEmail(email)) {
      return new NotificationDTO().builder()
          .endpoint("/api/v1/users/register")
          .message(TypeMessage.FAIL)
          .messageDetail("User Already Exists")
          .build();
    } else {
      String token = jwtUtil.generateTokenValidateRegister();
      String url = "http://localhost:8080/api/v1/users/verify/" + token;
      emailService.sendEmailRegister(email, url);
      return new NotificationDTO().builder()
          .endpoint("/api/v1/users/register")
          .message(TypeMessage.SUCCESS)
          .messageDetail("Check Your Email")
          .build();
    }
  }

  public void resetPassword(UserDTO userDTO) {
    String email = userDTO.getEmail();
  }

  public NotificationDTO verify(String token, HttpServletResponse response) {
    Boolean validate = jwtUtil.validateToken(token);
    if (validate) {
      try {
        response.sendRedirect("/home");
        return new NotificationDTO().builder()
            .endpoint("/api/v1/users/register")
            .message(TypeMessage.SUCCESS)
            .messageDetail("Welcome to CAR4R")
            .build();
      } catch (Exception e) {
        System.out.println(e);
        return null;
      }
    } else {
      return new NotificationDTO().builder()
          .endpoint("/api/v1/users/register")
          .message(TypeMessage.FAIL)
          .messageDetail("Not validate your email success")
          .build();
    }
  }

}