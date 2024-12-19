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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Customer customer;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Booking booking;

    String review;
    int rating;
    LocalDate reviewDate;
}
