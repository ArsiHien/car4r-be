package com.uet.car4r.repository;

import com.uet.car4r.dto.response.RatingResponse;
import com.uet.car4r.entity.Review;
import com.uet.car4r.projection.ReviewProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    @Query("""
            SELECT r.id AS id,
                   CONCAT(b.customer.firstName, ' ', b.customer.lastName) AS customerName,
                   cc.name AS carCategoryName,
                   r.review AS review,
                   r.rating AS rating,
                   r.reviewDate AS reviewDate
            FROM Review r
            JOIN r.booking b
            JOIN b.assignedCar c
            JOIN c.category cc
            WHERE cc.id = :categoryId
            """)
    Set<ReviewProjection> findByCategoryId(@Param("categoryId") String categoryId);

    @Query("""
            SELECT r.id AS id,
                   CONCAT(b.customer.firstName, ' ', b.customer.lastName) AS customerName,
                   cc.name AS carCategoryName,
                   r.review AS review,
                   r.rating AS rating,
                   r.reviewDate AS reviewDate
            FROM Review r
            JOIN r.booking b
            JOIN b.assignedCar c
            JOIN c.category cc
            WHERE b.id = :bookingId
            """)
    ReviewProjection findByBookingId(@Param("bookingId") String bookingId);

    @Query("""
            SELECT COUNT(r.id)
            FROM Review r
            WHERE r.rating = 1
            """)
    long getOneStar();

    @Query("""
            SELECT COUNT(r.id)
            FROM Review r
            WHERE r.rating = 2
            """)
    long getTwoStar();

    @Query("""
            SELECT COUNT(r.id)
            FROM Review r
            WHERE r.rating = 3
            """)
    long getThreeStar();

    @Query("""
            SELECT COUNT(r.id)
            FROM Review r
            WHERE r.rating = 4
            """)
    long getFourStar();

    @Query("""
            SELECT COUNT(r.id)
            FROM Review r
            WHERE r.rating = 5
            """)
    long getFiveStar();
}
