package com.uet.car4r.service;

import com.uet.car4r.dto.request.BookingRequest;
import com.uet.car4r.dto.response.booking.BookingResponse;
import com.uet.car4r.entity.Booking;
import com.uet.car4r.entity.Car;
import com.uet.car4r.entity.CarCategory;
import com.uet.car4r.entity.Customer;
import com.uet.car4r.mapper.BookingMapper;
import com.uet.car4r.mapper.ReviewMapper;
import com.uet.car4r.projection.BookingProjection;
import com.uet.car4r.projection.ReviewProjection;
import com.uet.car4r.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {
    BookingRepository bookingRepository;
    CustomerRepository customerRepository;
    CarCategoryRepository carCategoryRepository;
    CarRepository carRepository;
    ReviewRepository reviewRepository;
    BookingMapper bookingMapper;
    ReviewMapper reviewMapper;

    public BookingResponse getBooking(String bookingId) {
        BookingProjection projection = bookingRepository.findBookingProjectionById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        BookingResponse response = bookingMapper.toBookingResponse(projection);

        ReviewProjection reviewProjection = reviewRepository.findByBookingId(bookingId);
        if(reviewProjection != null) {
            response.setReview(reviewMapper.toReviewResponse(reviewProjection));
        }

        return response;
    }

    public List<BookingResponse> getBookings() {
        List<BookingProjection> projections = bookingRepository.findAllBookings();
        return mapBookingsToResponses(projections);
    }

    public List<BookingResponse> mapBookingsToResponses(List<BookingProjection> bookings) {
        return bookings.stream()
                .map(projection -> {
                    BookingResponse response = bookingMapper.toBookingResponse(projection);
                    ReviewProjection reviewProjection = reviewRepository.findByBookingId(projection.getId());
                    if (reviewProjection != null) {
                        response.setReview(reviewMapper.toReviewResponse(reviewProjection));
                    }
                    return response;
                })
                .collect(Collectors.toList());
    }

    public BookingResponse createBooking(BookingRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        CarCategory carCategory = carCategoryRepository.findById(request.getCarCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Car category not found"));

        Booking booking = bookingMapper.toBooking(request);
        booking.setCustomer(customer);
        booking.setCarCategory(carCategory);
        booking.setStatus(Booking.BookingStatus.IN_PROCESS);
        Booking savedBooking = bookingRepository.save(booking);
        BookingProjection projection = bookingRepository.findBookingProjectionById(savedBooking.getId())
                .orElseThrow(() -> new EntityNotFoundException("Booking not saved"));
        return bookingMapper.toBookingResponse(projection);
    }

    public void deleteBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        bookingRepository.delete(booking);
    }

    @Transactional
    public BookingResponse assignCarToBooking(String bookingId, String carId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));

        booking.setAssignedCar(car);
        booking.setStatus(Booking.BookingStatus.APPROVED);
        car.setStatus(Car.CarStatus.RENTED);
        bookingRepository.save(booking);

        return bookingMapper.toBookingResponse(booking);
    }

    @Transactional
    public BookingResponse updateBookingStatus(String bookingId, String status) throws InvalidPropertiesFormatException {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        booking.setStatus(validateStatus(status));
        bookingRepository.save(booking);
        return bookingMapper.toBookingResponse(booking);
    }

    @Transactional
    public BookingResponse cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        booking.setStatus(Booking.BookingStatus.CANCELED);
        bookingRepository.save(booking);

        return bookingMapper.toBookingResponse(booking);
    }

    public Booking.BookingStatus validateStatus(String status) throws InvalidPropertiesFormatException {
        try {
            return Booking.BookingStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new InvalidPropertiesFormatException("Invalid status value");
        }
    }
}
