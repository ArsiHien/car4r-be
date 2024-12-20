package com.uet.car4r.controller;

import com.uet.car4r.dto.request.ReviewRequest;
import com.uet.car4r.dto.response.ReviewResponse;
import com.uet.car4r.service.ReviewService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/reviews")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewController {
    ReviewService reviewService;

    @PostMapping
    ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest request) {
        System.out.println(request);
        ReviewResponse response = reviewService.createReview(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{reviewId}")
    ResponseEntity<ReviewResponse> updateReview(@PathVariable String reviewId, @RequestBody ReviewRequest request) {
        System.out.println(request);
        ReviewResponse response = reviewService.updateReview(reviewId, request);
        return ResponseEntity.ok(response);
    }
}
