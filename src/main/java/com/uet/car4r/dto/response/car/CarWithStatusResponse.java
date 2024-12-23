package com.uet.car4r.dto.response.car;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarWithStatusResponse {
    String status;
    int numberOfCar;
    List<CarDetailResponse> cars;
}
