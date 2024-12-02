package com.uet.car4r.repository;

import com.uet.car4r.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

    @Query(value = "SELECT * FROM bookings", nativeQuery = true)
    List<Booking> findAllBookings();

    @Query(value = "SELECT * FROM bookings WHERE booking_id = :bookingId", nativeQuery = true)
    Booking findBookingById(@Param("bookingId") String bookingId);

    @Query(value = """
    INSERT INTO bookings (booking_id, customer_id, car_category_id, start_date, return_date, loan_place, return_place, total_price, status)
    VALUES (:#{#booking.bookingId}, :#{#booking.customer.customerId}, :#{#booking.carCategory.carCategoryId}, 
            :#{#booking.startDate}, :#{#booking.returnDate}, :#{#booking.loanPlace}, 
            :#{#booking.returnPlace}, :#{#booking.totalPrice}, :#{#booking.status})
""", nativeQuery = true)
    void saveBooking(@Param("booking") Booking booking);

    // Delete booking
    @Query(value = "DELETE FROM bookings WHERE booking_id = :bookingId", nativeQuery = true)
    void deleteBookingById(@Param("bookingId") String bookingId);
}



