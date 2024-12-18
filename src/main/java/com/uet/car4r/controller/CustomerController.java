package com.uet.car4r.controller;

import com.uet.car4r.dto.response.booking.BookingPartitionResponse;
import com.uet.car4r.dto.response.booking.BookingResponse;
import com.uet.car4r.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * CustomerController
 */
@RestController
@RequestMapping(path = "/customers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {
  CustomerService customerService;

  @GetMapping("/{customerId}/bookings")
  public ResponseEntity<BookingPartitionResponse> getBookings(@PathVariable String customerId) {
    BookingPartitionResponse bookings = customerService.getBookings(customerId);
    return ResponseEntity.ok(bookings);
  }

}
