package com.uet.car4r.dto.response.booking;


import com.uet.car4r.dto.response.ReviewResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {
    String id;
    String customerName;
    String carCategoryName;
    String carCategoryId;
    String carLicensePlate;
    LocalDate bookingDate;
    LocalDate startDate;
    LocalDate returnDate;
    String loanPlace;
    String returnPlace;
    Long totalPrice;
    String status;
    ReviewResponse review;
}
