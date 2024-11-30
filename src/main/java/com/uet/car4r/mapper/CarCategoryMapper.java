package com.uet.car4r.mapper;

import com.uet.car4r.dto.response.CarCategoryResponse;
import com.uet.car4r.entity.CarCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarCategoryMapper {
    CarCategory toCarCategory(CarCategory carCategory);
    CarCategoryResponse toCarCategoryResponse(CarCategory user);
}
