package com.uet.car4r.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddEmployeeRequest {
    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    String phone;
}
