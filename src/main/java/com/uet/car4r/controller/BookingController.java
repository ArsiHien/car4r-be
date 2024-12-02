package com.uet.car4r.controller;

import com.uet.car4r.entity.Booking;
import com.uet.car4r.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

  private final BookingService bookingService;

  public BookingController(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @GetMapping
  public List<Booking> getAllBookings() {
    return bookingService.getAllBookings();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Booking> getBookingById(@PathVariable String id) {
    Booking booking = bookingService.getBookingById(id);
    return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<Void> createBooking(@RequestBody Booking booking) {
    bookingService.createBooking(booking);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBooking(@PathVariable String id) {
    bookingService.deleteBooking(id);
    return ResponseEntity.noContent().build();
  }
}