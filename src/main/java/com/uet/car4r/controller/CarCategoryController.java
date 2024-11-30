package com.uet.car4r.controller;

import com.uet.car4r.dto.request.CarCategoryCreationRequest;
import com.uet.car4r.dto.response.CarCategoryResponse;
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

    @GetMapping
    List<CarCategoryResponse> getCarCategories() {
        return carCategoryService.getCarCategories();
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CarCategoryResponse> createCarCategory(@ModelAttribute CarCategoryCreationRequest request) {
        CarCategoryResponse response = carCategoryService.createCarCategory(request);
        return ResponseEntity.ok(response);
    }
}
