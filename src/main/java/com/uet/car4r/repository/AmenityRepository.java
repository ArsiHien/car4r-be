package com.uet.car4r.repository;

import com.uet.car4r.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, String> {
    Optional<Amenity> findByName(String name);

    @Query(value = "SELECT a.* FROM amenity a " +
            "JOIN car_amenities ca ON a.id = ca.amenity_id " +
            "WHERE ca.car_category_id = :categoryId", nativeQuery = true)
    Set<Amenity> findByCarCategoryId(@Param("categoryId") String categoryId);
}
