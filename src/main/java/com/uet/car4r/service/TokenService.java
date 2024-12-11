package com.uet.car4r.service;

import com.uet.car4r.constant.Role;
import com.uet.car4r.constant.TokenType;
import com.uet.car4r.constant.TypeMessage;
import com.uet.car4r.dto.NotificationDTO;
import com.uet.car4r.entity.Token;
import com.uet.car4r.entity.User;
import com.uet.car4r.exception.CustomException;
import com.uet.car4r.mapper.UserMapper;
import com.uet.car4r.repository.CustomerRepository;
import com.uet.car4r.repository.TokenRepository;
import com.uet.car4r.repository.UserRepository;
import com.uet.car4r.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
  private final JwtUtil jwtUtil;
  private final TokenRepository tokenRepository;
  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final CustomerRepository customerRepository;

  @Value("${jwt.expiration.accessToken}")
  private Long expirationAccessToken;

  @Value("${jwt.expiration.refreshToken}")
  private Long expirationRefreshToken;

  // token is exist? -> delete token and generate new token

  /**
   * Update access, refresh token
   * @param user: {@link User}
   * @return Map<String, String>
   */
  public Map<String, String> handleLogin(User user) {
    // delete row
    tokenRepository.deleteAllByUserId(user.getId());

    return getAccessAndRefreshToken(user);
  }

  /**
   * Get and save token
   * @param user: {@link User} tai sao khong la UserDTO -> userId
   * @return Map<String, String>
   */
  public Map<String, String> getAccessAndRefreshToken(User user) {
    long now = System.currentTimeMillis();
    // generate date issuedAt, expirationAt of accessToken & refreshToken
    Date issuedAt = new Date(now);
    Date expirationAccessTokenAt = new Date(now + expirationAccessToken);
    Date expirationRefreshTokenAt = new Date(now + expirationRefreshToken);

    // generate accessToken & refreshToken
    String accessToken = jwtUtil.generateAccessToken(userMapper.entityToDto(user), issuedAt, expirationAccessTokenAt);
    String refreshToken = generateRefreshToken(user.getEmail());

    Map<String, String> res = new HashMap<>();
    res.put("accessToken", accessToken);
    res.put("refreshToken", refreshToken);

    // generate accessToken and refreshToken Entity
    Token accessTokenEntity = Token
        .builder()
        .user(user)
        .token(accessToken)
        .tokenType(TokenType.ACCESS)
        .issuedAt(issuedAt)
        .expiredAt(expirationAccessTokenAt)
        .build();

    Token refreshTokenEntity = Token
        .builder()
        .user(user)
        .token(refreshToken)
        .tokenType(TokenType.REFRESH)
        .issuedAt(issuedAt)
        .expiredAt(expirationRefreshTokenAt)
        .build();

    // save accessToken and refreshToken
    tokenRepository.save(accessTokenEntity);
    tokenRepository.save(refreshTokenEntity);

    return res;
  }

  private String generateRefreshToken(String email) {
    return UUID.randomUUID() + "----------" + email;

  }

  public Optional refreshAccessToken(String token) {
    Token existRefreshToken = tokenRepository.findByToken(token);

    // xem co ton tai refreshToken khong hoac refreshToken do con han khong
    if (existRefreshToken == null || existRefreshToken.getExpiredAt().before(new Date())) {
      return Optional.of(NotificationDTO.builder()
                             .message(TypeMessage.FAIL)
                             .messageDetail("Please Log In Again Or Sign Up")
                             .build());
    } else {
      String email = token.split("----------")[1];

      User user = userRepository.getAllByEmail(email);

      long now = System.currentTimeMillis();
      String accessToken = jwtUtil.generateAccessToken(userMapper.entityToDto(user), new Date(now), new Date(now + expirationAccessToken));

      Map<String, String> res = new HashMap<>();
      res.put("accessToken", accessToken);

      return Optional.of(res);
    }
  }

  /**
   * validate access token
   * @param token: String
   * @return Optional
   */
  public Optional validateAccessToken(String token) {
    try {
      Claims claims = jwtUtil.extractToken(token);

      String email = claims.get("email", String.class);
      String role = claims.get("roles", String.class);
      Date expiration = claims.getExpiration();

      if (expiration.before(new Date()) || !userRepository.existsUsersByEmail(email)) {
        return Optional.of(false);
      } else {
        if (role.equals(Role.CUSTOMER)) {
          return Optional.of(customerRepository.getCustomersByEmail(email));
        }

        return Optional.of(userRepository.getUserByEmail(email));
      }
    } catch (ExpiredJwtException expiredJwtException) {
      System.out.println("Token expired: invalid");
    } catch (SignatureException signatureException) {
      System.out.println("Token signature: invalid");
    } catch (Exception e) {
      System.out.println("Token invalid");
    }
    return Optional.of(false);
  }

  /**
   * validate verify token
   * @param token : String
   * @return Boolean
   */
  public Boolean validateVerifyToken(String token) {
    try {
      Claims claims = jwtUtil.extractToken(token);
      // neu dang con han va subject=CAR$R
      if (!claims.getExpiration().before(new Date()) && claims.getSubject().equals("CAR$R")) {
        return true;
      }
      return false;
    } catch (ExpiredJwtException expiredJwtException) {
      System.out.println("Token expired: invalid");
    } catch (SignatureException signatureException) {
      System.out.println("Token signature: invalid");
    } catch (Exception e) {
      System.out.println("Token invalid");
    }
    return false;
  }
}
