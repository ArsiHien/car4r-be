package com.uet.car4r.dto.response;

import com.uet.car4r.entity.CarCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarCategoryBasicResponse {
    String id;
    String name;
    String type;
    int numberOfPerson;
    CarCategory.Steering steering;
    int gasoline;
    int price;
    int promotionPrice;
    String mainImage;
}
