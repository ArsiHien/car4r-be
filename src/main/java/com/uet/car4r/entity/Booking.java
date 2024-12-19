package com.uet.car4r.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    Customer customer;

    @ManyToOne
    CarCategory carCategory;

    @OneToOne(mappedBy = "booking")
    Payment payment;

    @ManyToOne
    Car assignedCar;

    @OneToOne
    Review review;

    LocalDate bookingDate;
    LocalDate startDate;
    LocalDate returnDate;
    String loanPlace;
    String returnPlace;
    Long totalPrice;
    BookingStatus status;

    public enum BookingStatus {
        IN_PROCESS, CANCELED, APPROVED, COMPLETED,
    }
}
