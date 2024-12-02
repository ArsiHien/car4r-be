package com.uet.car4r.mapper;

import com.uet.car4r.dto.response.ReviewResponse;
import com.uet.car4r.projection.ReviewProjection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewResponse toReviewResponse(ReviewProjection projection);
}
