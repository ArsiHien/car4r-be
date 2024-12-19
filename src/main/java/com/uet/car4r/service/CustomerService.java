package com.uet.car4r.service;

import com.uet.car4r.dto.response.booking.BookingPartitionResponse;
import com.uet.car4r.dto.response.booking.BookingResponse;
import com.uet.car4r.entity.Booking;
import com.uet.car4r.mapper.BookingMapper;
import com.uet.car4r.projection.BookingProjection;
import com.uet.car4r.repository.BookingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {
    BookingService bookingService;
    BookingRepository bookingRepository;

    public BookingPartitionResponse getBookings(String customerId) {
        List<Booking.BookingStatus> currentStatuses = List.of(Booking.BookingStatus.IN_PROCESS, Booking.BookingStatus.APPROVED);
        List<Booking.BookingStatus> pastStatuses = List.of(Booking.BookingStatus.COMPLETED, Booking.BookingStatus.CANCELED);

        List<BookingProjection> currentBookings = bookingRepository.findAllCurrentBookings(currentStatuses, customerId);
        List<BookingProjection> pastBookings = bookingRepository.findAllPastBookings(pastStatuses, customerId);

        // Reuse the shared logic
        List<BookingResponse> currentBookingResponses = bookingService.mapBookingsToResponses(currentBookings);
        List<BookingResponse> pastBookingResponses = bookingService.mapBookingsToResponses(pastBookings);

        return BookingPartitionResponse.builder()
                .currentBookings(currentBookingResponses)
                .pastBookings(pastBookingResponses)
                .build();
    }

}
