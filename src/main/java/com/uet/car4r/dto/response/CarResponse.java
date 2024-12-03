package com.uet.car4r.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarResponse {
    String categoryId;
    int numberOfCar;
    List<CarDetailResponse> cars;
}
