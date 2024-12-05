package com.uet.car4r.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    CarCategory category;

    @OneToMany(mappedBy = "assignedCar", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Booking> booking;

    String licensePlate;
    CarStatus status = CarStatus.AVAILABLE;

    public enum CarStatus {
        AVAILABLE, RENTED, MAINTENANCE
    }

}
