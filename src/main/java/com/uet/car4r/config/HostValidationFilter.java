package com.uet.car4r.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;

public class HostValidationFilter extends OncePerRequestFilter {
  private static final String[] ALLOWED_HOSTS = {"localhost"};


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    String host = request.getHeader("Host");
    System.out.println(host);
    if (host == null || !isHostAllowed(host)) {
      response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied for host: " + host);
      return;
    }
    filterChain.doFilter(request, response);
  }

  private boolean isHostAllowed(String host) {
    for (String allowedHost : ALLOWED_HOSTS) {
      if (host.contains(allowedHost)) {
        return true;
      }
    }
    return false;
  }
}
