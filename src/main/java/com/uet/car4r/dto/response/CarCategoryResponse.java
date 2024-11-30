package com.uet.car4r.dto.response;

import com.uet.car4r.entity.Amenity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarCategoryResponse {
    String id;
    String name;
    String type;
    String description;
    int numberOfPerson;
    int price;
    int promotionPrice;
    String mainImageUrl;
    Set<Amenity> amenities;
}
