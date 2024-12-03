package com.uet.car4r.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {
    String customerId;
    String carCategoryId;
    LocalDate bookingDate;
    LocalDate startDate;
    LocalDate returnDate;
    String loanPlace;
    String returnPlace;
    Long totalPrice;
}
