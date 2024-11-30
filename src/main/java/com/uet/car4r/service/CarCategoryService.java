package com.uet.car4r.service;

import com.uet.car4r.dto.response.CarCategoryResponse;
import com.uet.car4r.mapper.CarCategoryMapper;
import com.uet.car4r.repository.CarCategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CarCategoryService {
    CarCategoryRepository carCategoryRepository;
    CarCategoryMapper carCategoryMapper;

    public List<CarCategoryResponse> getCarCategories() {
        return carCategoryRepository.findAll().stream().map(carCategoryMapper::toCarCategoryResponse).toList();
    }
}
