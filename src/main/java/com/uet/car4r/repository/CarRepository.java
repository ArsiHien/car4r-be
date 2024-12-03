package com.uet.car4r.repository;

import com.uet.car4r.entity.Car;
import com.uet.car4r.projection.CarProjection;
import com.uet.car4r.projection.CarWithCountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    @Query("""
                SELECT c.category.id AS categoryId, 
                       COUNT(c) AS numberOfCar 
                FROM Car c 
                WHERE c.category.id = :carCategoryId
                GROUP BY c.category.id
            """)
    CarWithCountProjection findCarCountByCategoryId(@Param("carCategoryId") String carCategoryId);

    @Query("""
                SELECT c.id AS id, 
                       c.licensePlate AS licensePlate, 
                       c.status AS status 
                FROM Car c 
                WHERE c.category.id = :carCategoryId
            """)
    List<CarProjection> findCarsByCategoryId(@Param("carCategoryId") String carCategoryId);


}
