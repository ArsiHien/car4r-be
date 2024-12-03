package com.uet.car4r.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    String licensePlate;
    CarStatus status = CarStatus.AVAILABLE;

    public enum CarStatus {
        AVAILABLE, RENTED, MAINTENANCE
    }

}
