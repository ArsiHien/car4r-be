package com.uet.car4r.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class CarGpsLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String carId;
    Double latitude;
    Double longitude;
    Integer sequenceOrder;
}
