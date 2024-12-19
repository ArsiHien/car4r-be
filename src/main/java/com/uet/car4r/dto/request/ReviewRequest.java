package com.uet.car4r.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewRequest {
    String customerId;
    String bookingId;
    String review;
    int rating;
    LocalDate reviewDate;
}
