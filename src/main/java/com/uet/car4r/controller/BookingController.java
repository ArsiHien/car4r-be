package com.uet.car4r.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * BookingController
 */
public class BookingController {
  @GetMapping
  public ResponseEntity getBookingById(@PathVariable Long id) {
    return null;
  }
}
