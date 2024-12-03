package com.uet.car4r.config;

import java.util.Properties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@AllArgsConstructor
public class EmailConfig {
  private Environment environment;

  @Bean
  public JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
    // config mail
    javaMailSender.setHost(environment.getProperty("spring.mail.host"));
    javaMailSender.setPort(Integer.valueOf(environment.getProperty("spring.mail.port")));
    javaMailSender.setUsername(environment.getProperty("spring.mail.username"));
    javaMailSender.setPassword(environment.getProperty("spring.mail.password"));

    Properties properties = javaMailSender.getJavaMailProperties();
    properties.put("mail.smtp.auth", true);
    properties.put("mail.smtp.starttls.enable", true);
    properties.put("mail.smtp.starttls.required", true);

    return javaMailSender;
  }
}
