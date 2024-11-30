package com.uet.car4r.dto.response;

import com.uet.car4r.entity.Amenity;
import com.uet.car4r.entity.Car;
import com.uet.car4r.entity.CarCategory;
import com.uet.car4r.entity.CarImage;
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
    CarCategory.Steering steering;
    int gasoline;
    int price;
    int promotionPrice;
    String mainImage;
    Set<Car> cars;
    Set<CarImage> carImages;
    Set<Amenity> amenities;
}
