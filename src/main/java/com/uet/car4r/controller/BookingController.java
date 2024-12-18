package com.uet.car4r.controller;

import com.uet.car4r.dto.request.BookingRequest;
import com.uet.car4r.dto.response.BookingResponse;
import com.uet.car4r.entity.Booking;
import com.uet.car4r.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.InvalidPropertiesFormatException;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {

    BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getBookings() {
        return ResponseEntity.ok(bookingService.getBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable String id) {
        return ResponseEntity.ok(bookingService.getBooking(id));
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable String id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<BookingResponse> cancelBooking(@PathVariable String bookingId) {
        BookingResponse response = bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{bookingId}/assign-car/{carId}")
    public ResponseEntity<BookingResponse> assignCarToBooking(@PathVariable String bookingId, @PathVariable String carId) {
        return ResponseEntity.ok(bookingService.assignCarToBooking(bookingId, carId));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingResponse> updateBookingStatus(@PathVariable String bookingId, @RequestParam String status) throws InvalidPropertiesFormatException {
        return ResponseEntity.ok(bookingService.updateBookingStatus(bookingId, status));
    }

}