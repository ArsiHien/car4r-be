package com.uet.car4r.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User
 */
@Getter
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

}
