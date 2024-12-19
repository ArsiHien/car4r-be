package com.uet.car4r.service;

import com.uet.car4r.dto.request.ReviewRequest;
import com.uet.car4r.dto.response.ReviewResponse;
import com.uet.car4r.entity.Booking;
import com.uet.car4r.entity.Customer;
import com.uet.car4r.entity.Review;
import com.uet.car4r.mapper.ReviewMapper;
import com.uet.car4r.repository.BookingRepository;
import com.uet.car4r.repository.CustomerRepository;
import com.uet.car4r.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewService {
    ReviewRepository reviewRepository;
    CustomerRepository customerRepository;
    BookingRepository bookingRepository;
    ReviewMapper reviewMapper;

    public ReviewResponse createReview(ReviewRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        Review review = reviewMapper.toReview(request);

        review.setCustomer(customer);
        review.setBooking(booking);

        review = reviewRepository.save(review);

        return reviewMapper.toReviewResponse(review);
    }

    public ReviewResponse updateReview(String reviewId, ReviewRequest request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        reviewMapper.updateReview(review, request);

        return reviewMapper.toReviewResponse(reviewRepository.save(review));
    }
}
