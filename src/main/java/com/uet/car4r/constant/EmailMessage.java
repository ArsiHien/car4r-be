package com.uet.car4r.constant;

import java.util.HashMap;
import java.util.Map;

public class EmailMessage {
  private static final String HOMEURL = "";
  private static final String PHONESUPPORT = "";
  private static final String EMAILSUPPORT = "";

  public static Map<String, String> getMailRegister(String username, String tokenRegister) {
    Map<String, String> res = new HashMap<>();

    String content = "<html><body>" +
        "<h1 style='color: #333;'>Welcome to CAR4R!</h1>" +
        "<p>Dear <strong>" + username + "</strong>,</p>" +
        "<p>Thank you for registering with CAR4R! We're excited to have you on board and help you book your rides easily.</p>" +
        "<p>To complete your registration and activate your account, please use the following token:</p>" +
        "<p style='text-align: center;'>" +
        "<strong style='font-size: 18px; color: #007bff;'>" + tokenRegister + "</strong></p>" +
        "<p>Simply enter this token in the app or website to validate your email and complete the registration process.</p>" +
        "<p>If you have any questions or need assistance, please don't hesitate to reach out to us at <strong>" + EMAILSUPPORT + "</strong> or call us at <strong>" + PHONESUPPORT + "</strong>.</p>" +
        "<p>Thank you for choosing CAR4R! We look forward to serving you.</p>" +
        "<br>" +
        "<p>Best regards,<br> The CAR4R Team</p>" +
        "</body></html>";

    res.put("subject", "Start Your Journey Today With CAR4R!");
    res.put("content", content);

    return res;
  }

  public static Map<String, String> getMailResetPw(String password) {
    Map<String, String> res = new HashMap<>();
    String content = "<html><body>" +
        "<h3>Password Reset Successful</h3>" +
        "<p>Dear User,</p>" +
        "<p>We have successfully reset your password for your CAR4R account. Here is your new password:</p>" +
        "<p><strong>" + password + "</strong></p>" +
        "<p>Use this password to log in to your account at <a href=''>CAR4R Login</a>.</p>" +
        "<p>We highly recommend that you change your password as soon as you log in to enhance your account security.</p>" +
        "<p>If you did not request this reset, please contact our support team immediately.</p>" +
        "<p>If you have any questions or need assistance, feel free to reach out to us at <strong>" + EMAILSUPPORT + "</strong>.</p>" +
        "<p>Thank you for choosing CAR4R!</p>" +
        "<p>Best regards,</p>" +
        "<p>The CAR4R Team</p>" +
        "<p><small>If you have any issues or did not request this reset, please contact us at " + EMAILSUPPORT + ".</small></p>" +
        "</body></html>";

    res.put("subject", "Your New Password For CAR4R");
    res.put("content", content);

    return res;
  }
}
