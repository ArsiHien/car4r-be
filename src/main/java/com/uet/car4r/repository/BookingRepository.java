package com.uet.car4r.repository;

import com.uet.car4r.entity.Booking;
import com.uet.car4r.projection.BookingProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

    @Query("""
            SELECT b.id AS id,
                   CONCAT(c.firstName, ' ', c.lastName) AS customerName,
                   cc.name AS carCategoryName,
                   car.licensePlate AS carLicensePlate,
                   b.bookingDate AS bookingDate,
                   b.startDate AS startDate,
                   b.returnDate AS returnDate,
                   b.loanPlace AS loanPlace,
                   b.returnPlace AS returnPlace,
                   b.totalPrice AS totalPrice,
                   b.status AS status
            FROM Booking b
                 LEFT JOIN b.customer c
                 LEFT JOIN b.carCategory cc
                 LEFT JOIN Car car ON car.id = b.assignedCar.id
            WHERE b.id = :bookingId
           """)
    Optional<BookingProjection> findBookingProjectionById(@Param("bookingId") String bookingId);

    @Query("""
            SELECT b.id AS id,
                   CONCAT(c.firstName, ' ', c.lastName) AS customerName,
                   cc.name AS carCategoryName,
                   car.licensePlate AS carLicensePlate,
                   b.bookingDate AS bookingDate,
                   b.startDate AS startDate,
                   b.returnDate AS returnDate,
                   b.loanPlace AS loanPlace,
                   b.returnPlace AS returnPlace,
                   b.totalPrice AS totalPrice,
                   b.status AS status
            FROM Booking b
                 LEFT JOIN b.customer c
                 LEFT JOIN b.carCategory cc
                 LEFT JOIN Car car ON car.id = b.assignedCar.id
           """)
    List<BookingProjection> findAllBookings();
}

