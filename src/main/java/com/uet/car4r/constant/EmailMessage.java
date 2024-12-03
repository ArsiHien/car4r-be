package com.uet.car4r.constant;

import java.util.HashMap;
import java.util.Map;

public class EmailMessage {
  private static final String HOMEURL = "";
  private static final String PHONESUPPORT = "";
  private static final String EMAILSUPPORT = "";

  public static Map<String, String> getMailRegister(String username, String urlRegister) {
    Map<String, String> res = new HashMap<>();
    String content = "<html><body>" +
        "<h1>Welcome to CAR4R!</h1>" +
        "<p>Dear <strong>" + username + "</strong>,</p>" +
        "<p>We're thrilled to have you on board! You can start exploring our services and book your first ride today.</p>" +
        "<p>Click the button below to validate your email:</p>" +
        "<a href='" + urlRegister + "' style='background-color: #007bff; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px;'>Validate Your Email</a>" +
        "<p>If you have any questions, feel free to contact us at <strong>" + EMAILSUPPORT + "</strong> or call us at <strong>" + PHONESUPPORT + "</strong>.</p>" +
        "<p>Thank you for choosing CAR4R. We look forward to serving you!</p>" +
        "</body></html>";

    res.put("subject", "Start Your Journey Today With CAR4R!");
    res.put("content", content);

    return res;
  }

  public static Map<String, String> getMailResetPw(String password) {
    Map<String, String> res = new HashMap<>();
    String content = "<html><body>" +
        "<h1>Password Reset Successful</h1>" +
        "<p>Dear User,</p>" +
        "<p>We have successfully reset your password for your CAR4R account. Here is your new password:</p>" +
        "<p><strong>" + password + "</strong></p>" +
        "<p>Use this password to log in to your account at <a href='https://www.car4r.com/login'>CAR4R Login</a>.</p>" +
        "<p>We highly recommend that you change your password as soon as you log in to enhance your account security.</p>" +
        "<p>If you did not request this reset, please contact our support team immediately.</p>" +
        "<p>If you have any questions or need assistance, feel free to reach out to us at <strong>" + EMAILSUPPORT + "</strong>.</p>" +
        "<p>Thank you for choosing CAR4R!</p>" +
        "<p>Best regards,</p>" +
        "<p>The CAR4R Team</p>" +
        "<p><small>If you have any issues or did not request this reset, please contact us at " + EMAILSUPPORT + ".</small></p>" +
        "</body></html>";

    res.put("subject", "Your New Password for CAR4R");
    res.put("content", content);

    return res;
  }
}
