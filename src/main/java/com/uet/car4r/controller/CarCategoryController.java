package com.uet.car4r.controller;

import com.uet.car4r.dto.response.CarCategoryResponse;
import com.uet.car4r.service.CarCategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
