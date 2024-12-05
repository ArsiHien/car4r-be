package com.uet.car4r.service;

import com.github.javafaker.Faker;
import com.uet.car4r.constant.EmailMessage;
import jakarta.mail.internet.MimeMessage;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
  private final JavaMailSender javaMailSender;
  private final Environment environment;

  public void sendEmailRegister(String to, String urlRegister) {
    System.out.println(javaMailSender);
    String username = to.split("@")[0];
    Map<String, String> mailRegister = EmailMessage.getMailRegister(username, urlRegister);

    try {
      MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);

      mimeMessageHelper.setFrom(environment.getProperty("spring.mail.username"));
      mimeMessageHelper.setTo(to);
      mimeMessageHelper.setSubject(mailRegister.get("subject"));
      mimeMessageHelper.setText(mailRegister.get("content"), true);

      javaMailSender.send(mimeMailMessage);
    } catch (Exception e) {
      System.out.println("ERR");
    }
  }

  public void sendEmailResetPassword(String to, String password) {
    Map<String, String> mailResetPassword = EmailMessage.getMailResetPw(password);

    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setFrom(environment.getProperty("spring.mail.username"));
    simpleMailMessage.setTo(to);
    simpleMailMessage.setSubject(mailResetPassword.get("subject"));
    simpleMailMessage.setText(mailResetPassword.get("content"));
  }
}
