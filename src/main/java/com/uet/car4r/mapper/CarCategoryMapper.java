package com.uet.car4r.mapper;

import com.uet.car4r.dto.request.CarCategoryRequest;
import com.uet.car4r.dto.response.CarCategoryBasicResponse;
import com.uet.car4r.dto.response.CarCategoryCountResponse;
import com.uet.car4r.dto.response.CarCategoryDetailResponse;
import com.uet.car4r.entity.CarCategory;
import com.uet.car4r.projection.BasicCarCategoryProjection;
import com.uet.car4r.projection.CarCategoryCountProjection;
import com.uet.car4r.projection.DetailCarCategoryProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CarCategoryMapper {
    @Mapping(target = "steering", source = "steering", qualifiedByName = "mapSteering")
    @Mapping(target = "mainImage", ignore = true)
    @Mapping(target = "carImages", ignore = true)
    @Mapping(target = "amenities", ignore = true)
    CarCategory toCarCategory(CarCategoryRequest request);

    @Named("mapSteering")
    default CarCategory.Steering mapSteering(String steering) {
        return steering != null ? CarCategory.Steering.valueOf(steering.toUpperCase()) : null;
    }

    CarCategoryDetailResponse toCarCategoryDetailResponse(CarCategory carCategory);

    CarCategoryBasicResponse toCarCategoryBasicResponse(CarCategory carCategory);

    CarCategoryBasicResponse toCarCategoryBasicResponse(BasicCarCategoryProjection basicCarCategoryProjection);

    CarCategoryDetailResponse toCarCategoryDetailResponse(BasicCarCategoryProjection basicCarCategoryProjection);

    CarCategoryDetailResponse toCarCategoryDetailResponse(DetailCarCategoryProjection detailCarCategoryProjection);

    @Mapping(target = "steering", source = "steering", qualifiedByName = "mapSteering")
    @Mapping(target = "mainImage", ignore = true)
    @Mapping(target = "carImages", ignore = true)
    @Mapping(target = "amenities", ignore = true)
    void updateCarCategory(@MappingTarget CarCategory carCategory, CarCategoryRequest request);

    CarCategoryCountResponse toCarCategoryCountResponse(CarCategoryCountProjection carCategoryCountProjection);
}
