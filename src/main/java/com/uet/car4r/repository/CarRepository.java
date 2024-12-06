package com.uet.car4r.repository;

import com.uet.car4r.entity.Car;
import com.uet.car4r.projection.CarProjection;
import com.uet.car4r.projection.CarWithCategoryProjection;
import com.uet.car4r.projection.CarWithStatusProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    @Query("""
                SELECT c.category.id AS categoryId,
                       cc.mainImage AS mainImage,
                       COUNT(c) AS numberOfCar
                FROM Car c
                LEFT JOIN c.category cc
                WHERE c.category.id = :carCategoryId
                GROUP BY c.category.id
            """)
    CarWithCategoryProjection findCarWithCountByCategoryId(@Param("carCategoryId") String carCategoryId);

    @Query("""
                SELECT c.id AS id,
                       cc.mainImage AS mainImage,
                       cc.name AS categoryName,
                       cc.type AS categoryType,
                       c.licensePlate AS licensePlate,
                       c.status AS status
                FROM Car c
                LEFT JOIN c.category cc
                WHERE c.category.id = :carCategoryId
            """)
    List<CarProjection> findCarsByCategoryId(@Param("carCategoryId") String carCategoryId);

    @Query("""
            SELECT c.id AS id,
                   c.licensePlate AS licensePlate,
                   cc.name AS categoryName,
                   cc.type AS categoryType,
                   cc.mainImage AS mainImage,
                   c.status AS status
            FROM Car c
            LEFT JOIN c.category cc
            WHERE c.status = :carStatus
           """)
    List<CarProjection> findCarsByStatus(@Param("carStatus") Car.CarStatus carStatus);

    @Query("""
            SELECT c.id AS id,
                   c.licensePlate AS licensePlate,
                   cc.name AS categoryName,
                   cc.type AS categoryType,
                   cc.mainImage AS mainImage,
                   c.status AS status,
                   b.startDate AS currentBookingStartDate,
                   b.returnDate AS currentBookingReturnDate,
                   b.loanPlace AS currentBookingLoanPlace,
                   b.returnPlace AS currentBookingReturnPlace,
                   b.totalPrice AS currentBookingTotalPrice
            FROM Car c
            LEFT JOIN c.category cc
            LEFT JOIN c.booking b ON b.status = com.uet.car4r.entity.Booking.BookingStatus.APPROVED
            WHERE c.status = :carStatus
           """)
    List<CarProjection> findCarsCurrentlyRented(@Param("carStatus") Car.CarStatus carStatus);

    @Query("""
             SELECT c.status AS status,
                    COUNT(c) AS numberOfCar
             FROM Car c
             WHERE c.status = :carStatus
             GROUP BY c.status
            """)
    CarWithStatusProjection findCarStatusCount(@Param("carStatus") Car.CarStatus carStatus);
}
