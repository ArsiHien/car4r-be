package com.uet.car4r.repository;

import com.uet.car4r.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, String> {
    Optional<Amenity> findByName(String name);
}
