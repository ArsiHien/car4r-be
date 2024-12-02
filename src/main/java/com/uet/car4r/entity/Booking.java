package com.uet.car4r.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String bookingId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "car_category_id", nullable = false)
    private CarCategory carCategory;
    private Date startDate;
    private Date returnDate;
    private String loanPlace;
    private String returnPlace;
    private Long totalPrice;

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingId() {
        return bookingId;
    }


    //    @Enumerated(EnumType.STRING)
    private enum BookingStatus  {
        inProcess,canceled,approved,completed,
    }

}
