package com.uet.car4r.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;

@Configuration
public class BeanConfig {
  @Bean
  public Environment getEnvironment() {
    return new StandardEnvironment();
  }
}
