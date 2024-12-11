package com.uet.car4r.service;

import com.github.javafaker.Faker;
import com.uet.car4r.constant.EmailMessage;
import com.uet.car4r.controller.UserController;
import com.uet.car4r.exception.CustomException;
import jakarta.mail.internet.MimeMessage;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  private static Logger logger = LoggerFactory.getLogger(EmailService.class);

  public Boolean sendEmailRegister(String to, String tokenRegister) {
    System.out.println("EmailService.sendEmailRegister(): " + javaMailSender);
    String username = to.split("@")[0];
    Map<String, String> mailRegister = EmailMessage.getMailRegister(username, tokenRegister);

    try {
      MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);

      mimeMessageHelper.setFrom(environment.getProperty("spring.mail.username"));
      mimeMessageHelper.setTo(to);
      mimeMessageHelper.setSubject(mailRegister.get("subject"));
      mimeMessageHelper.setText(mailRegister.get("content"), true);

      javaMailSender.send(mimeMailMessage);

      logger.info("EmailService.sendEmailRegister(): Send Email Success");
      return true;
    } catch (Exception e) {
      logger.info("EmailService.sendEmailRegister(): Send Email Error");
      return false;
    }
  }

  public void sendEmailResetPassword(String to, String password) {
    Map<String, String> mailResetPassword = EmailMessage.getMailResetPw(password);

    try {
      MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);

      mimeMessageHelper.setFrom(environment.getProperty("spring.mail.username"));
      mimeMessageHelper.setTo(to);
      mimeMessageHelper.setSubject(mailResetPassword.get("subject"));
      mimeMessageHelper.setText(mailResetPassword.get("content"), true);

      javaMailSender.send(mimeMailMessage);
    } catch (Exception e) {
      throw new CustomException("Send Email To Reset Password Error");
    }
  }
}
