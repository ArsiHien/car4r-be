package com.uet.car4r.service;

import com.github.javafaker.Faker;
import com.uet.car4r.constant.TypeMessage;
import com.uet.car4r.dto.NotificationDTO;
import com.uet.car4r.dto.UserDTO;
import com.uet.car4r.entity.User;
import com.uet.car4r.exception.CustomException;
import com.uet.car4r.mapper.UserMapper;
import com.uet.car4r.repository.UserRepository;
import com.uet.car4r.utils.JwtUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
  private final TokenService tokenService;
  private final EmailService emailService;

  private final UserRepository userRepository;

  private final JwtUtil jwtUtil;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final UserMapper userMapper;
  private final Faker faker;

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  private UserDTO tempUserDto = new UserDTO();

  /**
   * login
   * @param userDTO: {@link UserDTO}
   * @return Optional
   */
  public Optional login(UserDTO userDTO) {
    String email = userDTO.getEmail();

    boolean checkUserExistByEmail = userRepository.existsUsersByEmail(email);

    if (checkUserExistByEmail) {
      User user = userRepository.getAllByEmail(userDTO.getEmail());

      boolean checkValidatePassword = bCryptPasswordEncoder.matches(userDTO.getPassword(), user.getPassword());
      if (checkValidatePassword) {
        Map<String, String> res = tokenService.handleLogin(user);
        return Optional.of(res);
      }
    }

    NotificationDTO notificationDTO = NotificationDTO
        .builder()
        .endpoint("/api/v1/users/login")
        .message(TypeMessage.FAIL)
        .messageDetail("Invalid username or password. Please check your credentials and try again.")
        .build();
    return Optional.of(notificationDTO);
  }

  /**
   * register
   * @param userDTO: {@link UserDTO}
   * @return NotificationDTO
   */
  public NotificationDTO register(UserDTO userDTO) {
    String email = userDTO.getEmail();

    if (userRepository.existsUsersByEmail(email)) {
      return new NotificationDTO().builder()
          .endpoint("/api/v1/users/register")
          .message(TypeMessage.FAIL)
          .messageDetail("User Already Exists")
          .build();
    } else {
      String token = jwtUtil.generateVerifyToken() + "-" + email;
      tempUserDto = userDTO;

      boolean checkSendEmail = emailService.sendEmailRegister(email, token);
      if (checkSendEmail) {
        return new NotificationDTO().builder()
            .endpoint("/api/v1/users/register")
            .message(TypeMessage.SUCCESS)
            .messageDetail("Please Check Your Email")
            .build();
      }
      else {
        throw new CustomException("Unable to send email to your email address.");
      }
    }
  }

  /**
   * Verify register if user click button email
   * @param token: String
   * @return Optional
   */
  public Optional verifyRegister(String token) {
    String [] splitString = token.split("-");
    String tokenVerify = splitString[0];
    String email = splitString[1];

    Boolean validate = jwtUtil.validateVerifyToken(tokenVerify);

    if (validate || !userRepository.existsUsersByEmail(email)) {
      try {
        String encodePw = bCryptPasswordEncoder.encode(tempUserDto.getPassword());
        tempUserDto.setPassword(encodePw);

        // save user
        User user = userMapper.dtoToEntity(tempUserDto);
        userRepository.save(user);

        // result access, refresh token
        Map<String, String> res = tokenService.getAccessAndRefreshToken(user);

        return Optional.of(res);
      } catch (Exception e) {
        throw new CustomException("Not validate verify register");
      }
    } else {
      return Optional.of(NotificationDTO
                             .builder()
                             .endpoint("/api/v1/users/register")
                             .message(TypeMessage.FAIL)
                             .messageDetail("Not validate verify your email. Please Sign Up Again")
                             .build());
    }
  }

  /**
   * Reset Password
   * @param email: String
   * @return NotificationDTO
   */
  public NotificationDTO resetPassword(String email) {
    if (userRepository.existsUsersByEmail(email)) {
      String password = faker.regexify("[A-Z]{1}[a-z]{1}\\d{1}[@#$%^&+=]{1}{4,6}");
      String passwordEncoder = bCryptPasswordEncoder.encode(password);
      int update = userRepository.updatePasswordByEmail(email, passwordEncoder);

      if (update != 0) {
        emailService.sendEmailResetPassword(email, password);
        return NotificationDTO
            .builder()
            .endpoint("/api/v1/users/resetPassword")
            .message(TypeMessage.SUCCESS)
            .messageDetail("Please Check Your Email And Log In With New Password")
            .build();
      } else {
        return NotificationDTO
            .builder()
            .endpoint("/api/v1/users/resetPassword")
            .message(TypeMessage.FAIL)
            .messageDetail("Not Update Successfully")
            .build();
      }

    } else {
      return NotificationDTO
            .builder()
            .endpoint("/api/v1/users/resetPassword")
            .message(TypeMessage.FAIL)
            .messageDetail("Not Exist User With Email")
            .build();
    }
  }

  /**
   * ValidateAccessToken
   * @param token: String
   */
  public void validateAccessToken(String token) {

  }


  public String refreshAccessToken(String refreshToken) {
    return null;
  }

  public Optional authWithGoogle(UserDTO userDTO) {
    String email = userDTO.getEmail();
    User user = userMapper.dtoToEntity(userDTO);

    if (userRepository.existsUsersByEmail(email)) {
      User userExist = userRepository.getAllByEmail(email);

      // update userexist
      userExist.setFirstName(userDTO.getFirstName());
      userExist.setLastName(userDTO.getLastName());
      userExist.setUsername(userDTO.getUsername());
      userExist.setAvatar(userDTO.getAvatar());

      userRepository.saveAndFlush(userExist);
    } else {
      String password = bCryptPasswordEncoder.encode("CAR4R");
      user.setPassword(password);

      userRepository.saveAndFlush(user);
    }

    // access token refresh token
    User refreshUser = userRepository.getAllByEmail(email);

    Map<String, String> res = tokenService.handleLogin(refreshUser);
    return Optional.of(res);
  }
}
