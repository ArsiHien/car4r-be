package com.uet.car4r.utils;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
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

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("userName", userDetails.getUsername());
    claims.put("roles", userDetails.getAuthorities());

    return Jwts.builder()
        .setClaims(claims)
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

  public static void main(String[] args) {
    JwtUtil jwtUtil = new JwtUtil();
    System.out.println(jwtUtil.extractToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6IkpvaG4gRG9lIiwicm9sZSI6IkFETUlOIn0.QOd54qI2lZyVbhgj7cLsBp98Sgw5OfYyVOpJ-t2LZQM").get("role"));
  }
}
