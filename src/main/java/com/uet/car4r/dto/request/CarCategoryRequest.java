package com.uet.car4r.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarCategoryRequest {
    String name;
    String type;
    String description;
    int numberOfPerson;
    String steering;
    int gasoline;
    int price;
    int promotionPrice;
    MultipartFile mainImage;
    Set<MultipartFile> carImages;
    Set<String> amenityNames;
}
