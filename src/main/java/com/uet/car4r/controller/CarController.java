package com.uet.car4r.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * CarController
 */
@RestController
@RequestMapping(path = "/api/v1/cars")
public class CarController {
  @GetMapping(path = "/{id}")
  public ResponseEntity getCarById(@PathVariable Long id) {
    return null;
  }

  @GetMapping
  public ResponseEntity getCarByFilterAndPaging(@RequestParam Map<String, Object> params) {
    return null;
  }

  @PostMapping
  public ResponseEntity addCar(@RequestBody String temp) {
    return null;
  }

  @PutMapping
  public ResponseEntity updateCar(@PathVariable Long id, @RequestBody String temp) {
    return null;
  }

  @DeleteMapping
  public ResponseEntity delCar(@PathVariable Long id) {
    return null;
  }
}
