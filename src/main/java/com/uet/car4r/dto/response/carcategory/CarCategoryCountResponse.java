package com.uet.car4r.dto.response.carcategory;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarCategoryCountResponse {
    String groupKey;
    int count;
}
