package com.uet.car4r.dto.response.car;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarDetailResponse {
    String id;
    String licensePlate;
    String categoryName;
    String categoryType;
    String mainImage;
    String status;
    LocalDate currentBookingStartDate;
    LocalDate currentBookingReturnDate;
    String currentBookingLoanPlace;
    String currentBookingReturnPlace;
    Long currentBookingTotalPrice;
}