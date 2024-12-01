package com.uet.car4r.repository;

import com.uet.car4r.dto.response.CarImageResponse;
import com.uet.car4r.entity.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarImageRepository extends JpaRepository<CarImage, String> {
    @Query("SELECT ci FROM CarImage ci WHERE ci.category.id = :categoryId")
    Set<CarImage> findByCategoryId(@Param("categoryId") String categoryId);

    @Modifying
    @Query("DELETE FROM CarImage ci WHERE ci.category.id = :categoryId")
    void deleteByCategoryId(@Param("categoryId") String id);
}
