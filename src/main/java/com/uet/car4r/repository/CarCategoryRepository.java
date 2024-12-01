package com.uet.car4r.repository;

import com.uet.car4r.entity.CarCategory;
import com.uet.car4r.projection.BasicCarCategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarCategoryRepository extends JpaRepository<CarCategory, String> {

    @Query("SELECT c.id AS id, c.name AS name, c.type AS type, c.numberOfPerson AS numberOfPerson, " +
            "c.steering AS steering, c.gasoline AS gasoline, c.price AS price, c.promotionPrice AS promotionPrice, " +
            "c.mainImage AS mainImage FROM CarCategory c")
    List<BasicCarCategoryProjection> findAllBasicCarCategories();

}
