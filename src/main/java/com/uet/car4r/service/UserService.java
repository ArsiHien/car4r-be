package com.uet.car4r.service;

import com.uet.car4r.constant.TypeMessage;
import com.uet.car4r.dto.NotificationDTO;
import com.uet.car4r.dto.UserDTO;
import com.uet.car4r.entity.User;
import com.uet.car4r.mapper.UserMapper;
import com.uet.car4r.repository.UserRepository;
import com.uet.car4r.utils.JwtUtil;
import java.io.IOException;
import com.uet.car4r.constant.TypeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final EmailService emailService;
  private final JwtUtil jwtUtil;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final UserMapper userMapper;
  private UserDTO tempUserDto = new UserDTO();

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
      tempUserDto = userDTO;

      return new NotificationDTO().builder()
          .endpoint("/api/v1/users/verify")
          .message(TypeMessage.SUCCESS)
          .messageDetail("Check Your Email")
          .build();
    }
  }

  public void resetPassword(UserDTO userDTO) {
    String email = userDTO.getEmail();
  }

  public NotificationDTO verify(String token) {
    Boolean validate = jwtUtil.validateToken(token);
    if (validate) {
      try {
        String encodePw = bCryptPasswordEncoder.encode(tempUserDto.getPassword());
        tempUserDto.setPassword(encodePw);
        tempUserDto.
        User user = userMapper.dtoToEntity(tempUserDto);
        userRepository.save(user);

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
