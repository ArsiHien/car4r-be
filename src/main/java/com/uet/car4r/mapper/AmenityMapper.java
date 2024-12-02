package com.uet.car4r.mapper;

import com.uet.car4r.dto.response.AmenityResponse;
import com.uet.car4r.projection.AmenityProjection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AmenityMapper {
    AmenityResponse toAmenityResponse(AmenityProjection projection);
}
