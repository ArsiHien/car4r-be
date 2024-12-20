package com.uet.car4r.dto.response.staff;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StaffCreationResponse {
    String id;
    String username;
    String password;
    String name;
    String email;
    String phone;
    String avatar;
}
