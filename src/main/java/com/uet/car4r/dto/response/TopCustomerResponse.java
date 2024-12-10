package com.uet.car4r.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopCustomerResponse {
    String id;
    String name;
    String email;
    long bookingCount;
    long totalRevenue;
}
