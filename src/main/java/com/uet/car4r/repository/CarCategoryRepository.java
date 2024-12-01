package com.uet.car4r.repository;

import com.uet.car4r.entity.CarCategory;
import com.uet.car4r.projection.BasicCarCategoryProjection;
import com.uet.car4r.projection.CarCategoryCountProjection;
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

    @Query("SELECT c.type AS groupKey, COUNT(c.id) AS count " +
            "FROM CarCategory c GROUP BY c.type")
    List<CarCategoryCountProjection> countCarCategoryByType();

    @Query("SELECT " +
            "CASE " +
            "  WHEN c.numberOfPerson = 4 THEN '4' " +
            "  WHEN c.numberOfPerson = 5 THEN '5' " +
            "  WHEN c.numberOfPerson = 7 THEN '7' " +
            "  WHEN c.numberOfPerson = 9 THEN '9' " +
            "  ELSE '9+' " +
            "END AS groupKey, COUNT(c.id) AS count " +
            "FROM CarCategory c GROUP BY groupKey")
    List<CarCategoryCountProjection> countCarCategoryByNumberOfPerson();
}
