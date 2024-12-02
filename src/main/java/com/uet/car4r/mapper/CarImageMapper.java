package com.uet.car4r.mapper;

import com.uet.car4r.dto.response.CarImageResponse;
import com.uet.car4r.projection.CarImageProjection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarImageMapper {
    CarImageResponse toCarImageResponse(CarImageProjection projection);
}
