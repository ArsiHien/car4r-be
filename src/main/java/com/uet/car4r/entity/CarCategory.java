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
@EqualsAndHashCode(exclude = {"cars", "carImages", "amenities"})
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
    Steering steering;
    int gasoline;
    int promotionPrice;
    String mainImage;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Car> cars;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<CarImage> carImages;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "car_amenities",
            joinColumns = @JoinColumn(name = "car_category_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    Set<Amenity> amenities;

    public enum Steering {
        MANUAL, ELECTRIC
    }
}
