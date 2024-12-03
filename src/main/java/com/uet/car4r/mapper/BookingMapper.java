package com.uet.car4r.mapper;

import com.uet.car4r.dto.request.BookingRequest;
import com.uet.car4r.dto.response.BookingResponse;
import com.uet.car4r.entity.Booking;
import com.uet.car4r.projection.BookingProjection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingResponse toBookingResponse(Booking booking);

    BookingResponse toBookingResponse(BookingProjection projection);

    Booking toBooking(BookingRequest request);
}
