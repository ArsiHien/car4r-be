package com.uet.car4r.repository;

import com.uet.car4r.entity.CarImage;
import com.uet.car4r.projection.CarImageProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarImageRepository extends JpaRepository<CarImage, String> {
    @Modifying
    @Query("DELETE FROM CarImage ci WHERE ci.category.id = :categoryId")
    void deleteByCategoryId(@Param("categoryId") String id);

    @Query("""
            SELECT ci AS carImages
            FROM CarImage ci
            WHERE ci.category.id = :categoryId
            """)
    Set<CarImageProjection> findByCategoryId(@Param("categoryId") String categoryId);

}
