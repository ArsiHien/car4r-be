package com.uet.car4r.filters;

import com.uet.car4r.repository.UserRepository;
import com.uet.car4r.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private JwtUtil jwtUtil;

  private UserRepository userRepository;

  public JwtAuthenticationFilter(JwtUtil jwtUtil, UserRepository userRepository) {
    this.jwtUtil = jwtUtil;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    if (requestURI.equals("/api/v1/users/signUp")) {
      filterChain.doFilter(request, response);
      return;
    }

    String authHeader = request.getHeader("authorization");
    String token = null;

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      token = authHeader.substring(7);
    } else {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
      response.getWriter().write("Authorization token is missing or invalid.");
      response.getWriter().flush();
      return;
    }

    if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      try {
        Claims claims = jwtUtil.extractToken(token);

        String email = claims.get("email").toString();
        String role = claims.get("roles").toString();

        // neu dang con han va ton tai user
        boolean checkExpiration = claims.getExpiration().before(new Date());
        boolean checkExistUser = userRepository.existsUsersByEmail(email);

        if (!checkExpiration && checkExistUser) {
          List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null, authorities);
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } else {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          response.getWriter().write("Token has expired or the user is not found.");
          response.getWriter().flush();
        }
      } catch (Exception e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write("An error occurred while processing your token. Please try again.");
        response.getWriter().flush();
      }
    } else {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
      response.getWriter().write("Authorization token is missing or invalid.");
      response.getWriter().flush();
    }
  }
}
