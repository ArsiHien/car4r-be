package com.uet.car4r.dto;

import com.uet.car4r.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * User
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserDTO {
  private String id;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private String username;
  private String phone;
  private Role role;
  private String avatar;

  public CustomerDTO getCustomerDTO() {
    if (role.equals(Role.CUSTOMER)) {
      return CustomerDTO
          .builder()
          .build();
    }
    return null;
  }
}
