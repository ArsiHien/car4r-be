package com.uet.car4r.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarCategoryRentalStatisticResponse {
    String id;
    String name;
    String type;
    int numberOfPerson;
    String steering;
    int gasoline;
    int price;
    int promotionPrice;
    String mainImage;
    double averageRating;
    long bookingCount;
    long totalRevenue;
}
