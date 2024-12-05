package com.uet.car4r.utils;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * JwtUtil: generate token, extract token
 */
@Component
public class JwtUtil {
  private final Dotenv dotenv = Dotenv.load();
  private final String SECRETE_KEY = dotenv.get("SECRET_KEY");
  private final long expirationTime = 1000 * 60 * 3;

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("userName", userDetails.getUsername());
    claims.put("roles", userDetails.getAuthorities());

    return Jwts.builder()
        .setClaims(claims)
        .signWith(SignatureAlgorithm.HS256, SECRETE_KEY)
        .compact();
  }

  public String generateTokenValidateRegister() {
    long now = System.currentTimeMillis();
    return Jwts.builder()
        .setSubject("CAR$R")
        .setIssuedAt(new Date(now))
        .setExpiration(new Date(now + expirationTime))
        .signWith(SignatureAlgorithm.HS256, SECRETE_KEY)
        .compact();
  }

  public Claims extractToken(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(SECRETE_KEY)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public Boolean validateToken(String token) {
    System.out.println(dotenv);
    try {
      Claims claims = extractToken(token);
      if (claims.getExpiration().before(new Date()) || !claims.getSubject().equals("CAR$R")) {
        return false;
      }
      return true;
    } catch (ExpiredJwtException expiredJwtException) {
      System.out.println("Token expired: invalid");
    } catch (SignatureException signatureException) {
      System.out.println("Token signature: invalid");
    } catch (Exception e) {
      System.out.println("Token invalid");
    }
    return false;
  }


  public static void main(String[] args) {
    JwtUtil jwtUtil = new JwtUtil();
    System.out.println(jwtUtil.extractToken("eyJhbGciOiJub25lIn0.eyJzdWIiOiJDQVIkUiIsImlhdCI6MTczMzMxNDc4MCwiZXhwIjoxNzMzMzE0OTYwfQ."));
  }
}
