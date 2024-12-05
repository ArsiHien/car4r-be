package com.uet.car4r.controller;

import com.uet.car4r.dto.request.CarRequest;
import com.uet.car4r.dto.response.CarDetailResponse;
import com.uet.car4r.dto.response.CarResponse;
import com.uet.car4r.service.CarService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * CarController
 */
@RestController
@RequestMapping(path = "/cars")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CarController {
    CarService carService;

    @GetMapping(path = "/{carCategoryId}")
    public ResponseEntity<CarResponse> getCarByCarCategory(@PathVariable String carCategoryId) {
        return ResponseEntity.ok(carService.getCarByCarCategory(carCategoryId));
    }

    @PostMapping
    public ResponseEntity<CarDetailResponse> createCar(@RequestBody CarRequest carRequest) {
        CarDetailResponse carResponse = carService.createCar(carRequest);
        return new ResponseEntity<>(carResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDetailResponse> updateCar(@PathVariable String id, @RequestBody CarRequest carRequest) {
        CarDetailResponse carResponse = carService.updateCar(id, carRequest);
        return ResponseEntity.ok(carResponse);
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable String id) {
        carService.deleteCar(id);
        return "Car has been deleted";
    }
}
