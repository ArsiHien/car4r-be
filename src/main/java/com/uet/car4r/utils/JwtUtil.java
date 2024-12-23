package com.uet.car4r.utils;

import com.uet.car4r.constant.Role;
import com.uet.car4r.dto.UserDTO;
import com.uet.car4r.repository.CustomerRepository;
import com.uet.car4r.repository.UserRepository;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Generate, Validate access, verify token
 */
@Component
public class JwtUtil {
  private final Dotenv dotenv = Dotenv.load();
  private final String SECRETE_KEY = dotenv.get("SECRET_KEY");

  @Value("${jwt.expiration.verifyToken}")
  private Long expirationVerifyToken;

  private final long expirationTimeTokenLogin = 1000 * 60 * 30;
  private final long now = System.currentTimeMillis();

  /**
   * generate access token
   * @param userDTO: {@link UserDTO}
   * @param issuedAt: {@link Date}
   * @param expirationAt: {@link Date}
   * @return accessToken: {@link String}
   */
  public String generateAccessToken(UserDTO userDTO, Date issuedAt, Date expirationAt) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("email", userDTO.getEmail());
    claims.put("roles", userDTO.getRole());

    String accessToken =  Jwts.builder()
                          .setClaims(claims)
                          .setIssuedAt(issuedAt)
                          .setExpiration(expirationAt)
                          .signWith(SignatureAlgorithm.HS256, SECRETE_KEY)
                          .compact();

    return accessToken;
  }


  /**
   * genearte verify token
   * @return verify token:String
   */
  public String generateVerifyToken() {
    return Jwts.builder()
        .setSubject("CAR$R")
        .setIssuedAt(new Date(now))
        .setExpiration(new Date(now + expirationVerifyToken))
        .signWith(SignatureAlgorithm.HS256, SECRETE_KEY)
        .compact();
  }

  /**
   * extract token to claims
   * @param token: String
   * @return claims: Claims
   */
  public Claims extractToken(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(SECRETE_KEY)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public static void main(String[] args) {
    JwtUtil jwtUtil = new JwtUtil();
    System.out.println(jwtUtil.extractToken("eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6IkNVU1RPTUVSIiwiZW1haWwiOiIyMjAyODIyMUB2bnUuZWR1LnZuIiwiaWF0IjoxNzMzODQ5MTA1LCJleHAiOjE3MzM5MzU1MDV9.FR9jTYBzEoVVjibrbfTCifif41DyfnBW81L9wenHaqM").getExpiration());
    System.out.println(jwtUtil.extractToken("eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6IkNVU1RPTUVSIiwiZW1haWwiOiIyMjAyODIyMUB2bnUuZWR1LnZuIiwiaWF0IjoxNzMzODQ5MTA1LCJleHAiOjE3MzM5MzU1MDV9.FR9jTYBzEoVVjibrbfTCifif41DyfnBW81L9wenHaqM").getExpiration().before(new Date()));
  }
}
