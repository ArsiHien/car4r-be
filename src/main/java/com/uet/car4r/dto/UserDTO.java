package com.uet.car4r.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private String userName;
  private String phone;
  private String avatar;
}
