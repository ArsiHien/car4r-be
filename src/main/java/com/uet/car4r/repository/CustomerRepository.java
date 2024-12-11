package com.uet.car4r.repository;

import com.uet.car4r.entity.Customer;
import com.uet.car4r.projection.TopCustomerProjection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("""
            SELECT c.id AS id, CONCAT(c.firstName, ' ', c.lastName) AS name, c.email AS email,
                   COUNT(b) AS bookingCount, COALESCE(SUM(b.totalPrice), 0) AS totalRevenue
            FROM Customer c
            LEFT JOIN c.bookings b
            WHERE b.status = com.uet.car4r.entity.Booking.BookingStatus.APPROVED
            GROUP BY c.id, c.firstName, c.lastName, c.email
            ORDER BY COUNT(b) DESC, SUM(b.totalPrice) DESC
            LIMIT 5
            """)
    List<TopCustomerProjection> findTopCustomersByBookingFrequencyAndRevenue();

    Optional getCustomersById(String id);

    Object getCustomersByEmail(String email);
}

