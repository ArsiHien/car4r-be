package com.uet.car4r.service;

import com.uet.car4r.entity.Booking;
import com.uet.car4r.repository.BookingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAllBookings();
    }

    public Booking getBookingById(String bookingId) {
        return bookingRepository.findBookingById(bookingId);
    }

    public void createBooking(Booking booking) {
        bookingRepository.saveBooking(booking);
    }

    public void deleteBooking(String bookingId) {
        bookingRepository.deleteBookingById(bookingId);
    }
}
