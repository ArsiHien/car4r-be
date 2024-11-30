package com.uet.car4r.mapper;

import com.uet.car4r.dto.request.CarCategoryCreationRequest;
import com.uet.car4r.dto.response.CarCategoryResponse;
import com.uet.car4r.entity.CarCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CarCategoryMapper {
    @Mapping(target = "steering", source = "steering", qualifiedByName = "uppercaseSteering")
    @Mapping(target = "mainImage", ignore = true)
    @Mapping(target = "carImages", ignore = true)
    @Mapping(target = "amenities", ignore = true)
    CarCategory toCarCategory(CarCategoryCreationRequest request);

    @Named("uppercaseSteering")
    default CarCategory.Steering uppercaseSteering(String steering) {
        return steering != null ? CarCategory.Steering.valueOf(steering.toUpperCase()) : null;
    }

    CarCategoryResponse toCarCategoryResponse(CarCategory carCategory);
}
