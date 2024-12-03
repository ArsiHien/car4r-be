package com.uet.car4r.mapper;

import com.uet.car4r.dto.request.CarRequest;
import com.uet.car4r.dto.response.CarDetailResponse;
import com.uet.car4r.dto.response.CarResponse;
import com.uet.car4r.entity.Car;
import com.uet.car4r.projection.CarProjection;
import com.uet.car4r.projection.CarWithCountProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarResponse toCarResponse(CarWithCountProjection projection);

    CarDetailResponse toCarDetailResponse(CarProjection projection);

    CarResponse toCarResponse(Car car);

    CarDetailResponse toCarDetailResponse(Car car);

    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus")
    Car toCar(CarRequest carRequest);

    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus")
    void updateCar(@MappingTarget Car car, CarRequest request);

    @Named("mapStatus")
    default Car.CarStatus mapStatus(String status) {
        return status != null ? Car.CarStatus.valueOf(status.toUpperCase()) : null;
    }
}