package com.uet.car4r.mapper;

import com.uet.car4r.dto.request.BookingRequest;
import com.uet.car4r.dto.response.BookingResponse;
import com.uet.car4r.entity.Booking;
import com.uet.car4r.projection.BookingProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "customerName", expression = "java(booking.getCustomer().getFirstName() + ' ' + booking.getCustomer().getLastName())")
    @Mapping(target = "carCategoryName", source = "carCategory.name")
    @Mapping(target = "carLicensePlate", expression = "java(booking.getAssignedCar() != null ? booking.getAssignedCar().getLicensePlate() : null)")
    @Mapping(target = "carCategoryId", source = "carCategory.id")
    BookingResponse toBookingResponse(Booking booking);

    @Mapping(target = "carCategoryId", source = "carCategoryId")
    BookingResponse toBookingResponse(BookingProjection projection);

    Booking toBooking(BookingRequest request);
}
