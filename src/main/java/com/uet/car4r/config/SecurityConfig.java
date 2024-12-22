package com.uet.car4r.config;

import com.uet.car4r.filters.JwtAuthenticationFilter;
import com.uet.car4r.repository.UserRepository;
import com.uet.car4r.utils.JwtUtil;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * SecurityConfig
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  private final Dotenv env = Dotenv.load();
  private final JwtUtil jwtUtil;
  private final UserRepository userRepository;

    // encrypt password
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .csrf(crsf -> crsf.disable())
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll())
        .addFilterBefore(new HostValidationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost"); // postman
        configuration.addAllowedOrigin("http://localhost:5173");//fe
        configuration.addAllowedMethod("*"); // allow all http method
        configuration.addAllowedHeader("*"); // allow all header
        configuration.setAllowCredentials(true); // allow sent credental include cookie

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);
        return urlBasedCorsConfigurationSource;
    }


}