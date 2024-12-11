package com.uet.car4r.dto;

import com.uet.car4r.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * CustomerDTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CustomerDTO extends UserDTO {
  private String address;
  private String drivingLicenseNo;
  private String identityCardNo;
}
