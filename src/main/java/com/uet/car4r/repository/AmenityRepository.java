package com.uet.car4r.repository;

import com.uet.car4r.entity.Amenity;
import com.uet.car4r.projection.AmenityProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, String> {
    Optional<Amenity> findByName(String name);

    @Query("""
            SELECT a.id AS id, 
                   a.name AS name
            FROM CarCategory c
            JOIN c.amenities a
            WHERE c.id = :categoryId
            """)
    Set<AmenityProjection> findByCategoryId(@Param("categoryId") String categoryId);


}
