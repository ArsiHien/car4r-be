package com.uet.car4r.dto.response.booking;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingPartitionResponse {
    List<BookingResponse> currentBookings;
    List<BookingResponse> pastBookings;
}
