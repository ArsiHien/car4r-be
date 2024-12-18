package com.uet.car4r.dto.response.car;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarImageResponse {
    String id;
    String imageUrl;
}
