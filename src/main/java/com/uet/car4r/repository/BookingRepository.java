package com.uet.car4r.repository;

import com.uet.car4r.entity.Booking;
import com.uet.car4r.projection.BookingProjection;
import com.uet.car4r.projection.MonthlyRevenueProjection;
import com.uet.car4r.projection.RevenueByCategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    @Query("SELECT SUM(b.totalPrice) FROM Booking b WHERE b.status = com.uet.car4r.entity.Booking.BookingStatus.APPROVED")
    long calculateTotalRevenue();

    @Query("""
             SELECT MONTH(b.bookingDate) AS month, YEAR(b.bookingDate) AS year, COALESCE(SUM(b.totalPrice), 0) AS revenue
             FROM Booking b
             WHERE b.status = com.uet.car4r.entity.Booking.BookingStatus.APPROVED AND b.bookingDate BETWEEN :startDate AND :endDate
             GROUP BY YEAR(b.bookingDate), MONTH(b.bookingDate)
             ORDER BY YEAR(b.bookingDate), MONTH(b.bookingDate)
            """)
    List<MonthlyRevenueProjection> findLast12MonthsRevenue(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("""
             SELECT cc.type AS categoryType,
                    COALESCE(SUM(b.totalPrice), 0) AS revenue
             FROM Booking b
             JOIN b.carCategory cc
             WHERE b.status = com.uet.car4r.entity.Booking.BookingStatus.APPROVED
             GROUP BY cc.type
             ORDER BY revenue DESC
            """)
    List<RevenueByCategoryProjection> findRevenueByCategoryType();

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
           WHERE b.status IN (:statuses)
           """)
    List<BookingProjection> findAllCurrentBookings(@Param("statuses") List<Booking.BookingStatus> statuses);

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
           WHERE b.status IN (:statuses)
           """)
    List<BookingProjection> findAllPastBookings(@Param("statuses") List<Booking.BookingStatus> statuses);
}

