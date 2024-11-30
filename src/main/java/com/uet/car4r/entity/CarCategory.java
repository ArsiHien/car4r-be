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
public class CarCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String type;
    String description;
    int numberOfPerson;
    int price;
    int promotionPrice;
    String mainImageUrl;

    @ManyToMany
    @JoinTable(
            name = "car_amenities",
            joinColumns = @JoinColumn(name = "car_category_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private Set<Amenity> amenities;
}
