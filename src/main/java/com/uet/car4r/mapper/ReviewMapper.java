package com.uet.car4r.mapper;

import com.uet.car4r.dto.request.ReviewRequest;
import com.uet.car4r.dto.response.ReviewResponse;
import com.uet.car4r.entity.Review;
import com.uet.car4r.projection.ReviewProjection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ReviewMapper {


    Review toReview(ReviewRequest request);

    ReviewResponse toReviewResponse(ReviewProjection projection);

    ReviewResponse toReviewResponse(Review review);

    Set<ReviewResponse> toReviewResponse(Set<ReviewProjection> byCategoryId);

    void updateReview(@MappingTarget Review review, ReviewRequest request);
}
