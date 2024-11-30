package com.uet.car4r.service;

import com.uet.car4r.entity.Car;
import com.uet.car4r.repository.CarRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CarService {
    CarRepository carRepository;

    public Optional<Car> findById(String id) {
        return carRepository.findById(id);
    }
}
