package com.uet.car4r.repository;

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
                   CONCAT(r.customer.firstName, ' ', r.customer.lastName) AS customerName,
                   r.category.name AS carCategoryName,
                   r.review AS review,
                   r.rating AS rating,
                   r.reviewDate AS reviewDate
            FROM Review r
            WHERE r.category.id = :categoryId
            """)
    Set<ReviewProjection> findByCategoryId(@Param("categoryId") String categoryId);
}
