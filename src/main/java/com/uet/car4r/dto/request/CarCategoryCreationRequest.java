package com.uet.car4r.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarCategoryCreationRequest {
    String name;
    String type;
    String description;
    int numberOfPerson;
    int price;
    int promotionPrice;
    String mainImageUrl;
    private Set<Amenity> amenities;
}
