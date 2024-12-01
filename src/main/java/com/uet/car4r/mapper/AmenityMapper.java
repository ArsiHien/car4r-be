package com.uet.car4r.mapper;

import com.uet.car4r.dto.response.AmenityResponse;
import com.uet.car4r.entity.Amenity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AmenityMapper {
    AmenityResponse toAmenityResponse(Amenity amenity);
}
