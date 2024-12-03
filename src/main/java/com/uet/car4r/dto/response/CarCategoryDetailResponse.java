package com.uet.car4r.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarCategoryDetailResponse {
    String id;
    String name;
    String type;
    String description;
    int numberOfPerson;
    String steering;
    int gasoline;
    int price;
    int promotionPrice;
    String mainImage;
    Set<CarImageResponse> carImages;
    Set<AmenityResponse> amenities;
    Set<ReviewResponse> reviews;
}
