package com.uet.car4r.controller;

import com.uet.car4r.dto.request.CarCategoryCreationRequest;
import com.uet.car4r.dto.request.CarCategoryUpdateRequest;
import com.uet.car4r.dto.response.CarCategoryBasicResponse;
import com.uet.car4r.dto.response.CarCategoryCountResponse;
import com.uet.car4r.dto.response.CarCategoryDetailResponse;
import com.uet.car4r.service.CarCategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car-category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CarCategoryController {
    CarCategoryService carCategoryService;

    @GetMapping("/detail")
    List<CarCategoryDetailResponse> getCarCategoryDetails() {
        return carCategoryService.getDetailCarCategories();
    }

    @GetMapping("/basic")
    List<CarCategoryBasicResponse> getBasicCarCategories() {
        return carCategoryService.getBasicCarCategories();
    }

    @GetMapping("/{carCategoryId}")
    CarCategoryDetailResponse getCarCategoryDetail(@PathVariable String carCategoryId) {
        return carCategoryService.getCarCategory(carCategoryId);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CarCategoryDetailResponse> createCarCategory(@ModelAttribute CarCategoryCreationRequest request) {
        System.out.println("Request in controller: " + request);
        CarCategoryDetailResponse response = carCategoryService.createCarCategory(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{carCategoryId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CarCategoryDetailResponse> updateCarCategoryDetail(@PathVariable String carCategoryId, @ModelAttribute CarCategoryUpdateRequest request) {
        System.out.println("PUT Request in controller: " + request);
        CarCategoryDetailResponse response = carCategoryService.updateCarCategory(carCategoryId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{carCategoryId}")
    String deleteCarCategory(@PathVariable String carCategoryId) {
        carCategoryService.deleteCarCategory(carCategoryId);
        return "Car category has been deleted";
    }

    @GetMapping("/count-by-type")
    public ResponseEntity<List<CarCategoryCountResponse>> countCarCategoryByType() {
        List<CarCategoryCountResponse> response = carCategoryService.getCarCategoryCountByType();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/count-by-person")
    public ResponseEntity<List<CarCategoryCountResponse>> countCarCategoryByNumberOfPerson() {
        List<CarCategoryCountResponse> response = carCategoryService.getCarCategoryCountByNumberOfPerson();
        return ResponseEntity.ok(response);
    }
}
