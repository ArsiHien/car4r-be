package com.uet.car4r.mapper;

import com.uet.car4r.dto.request.BookingRequest;
import com.uet.car4r.dto.response.booking.BookingResponse;
import com.uet.car4r.entity.Booking;
import com.uet.car4r.projection.BookingProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "customerName", expression = "java(booking.getCustomer().getFirstName() + ' ' + booking.getCustomer().getLastName())")
    @Mapping(target = "carCategoryName", source = "carCategory.name")
    @Mapping(target = "carLicensePlate", expression = "java(booking.getAssignedCar() != null ? booking.getAssignedCar().getLicensePlate() : null)")
    @Mapping(target = "carCategoryId", source = "carCategory.id")
    BookingResponse toBookingResponse(Booking booking);

    @Mapping(target = "carCategoryId", source = "carCategoryId")
    BookingResponse toBookingResponse(BookingProjection projection);

//    List<BookingResponse> toBookingResponseList(List<Booking> bookings);

    List<BookingResponse> toBookingResponseList(List<BookingProjection> bookingProjections);

    Booking toBooking(BookingRequest request);
}
