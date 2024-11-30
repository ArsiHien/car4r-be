package com.uet.car4r.Utils;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * Jwt
 */
@Component
public class Jwt {
  private final String SECRETKEY = "";

//  public String generateToken() {
//    return Jwts.builder()
//        .setHeader(Header.JWT_TYPE)
//        .setClaims()
//        .signWith(SECRETKEY);
//  }
}
