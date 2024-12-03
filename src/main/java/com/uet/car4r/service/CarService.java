package com.uet.car4r.service;

import com.uet.car4r.dto.request.CarRequest;
import com.uet.car4r.dto.response.CarDetailResponse;
import com.uet.car4r.dto.response.CarResponse;
import com.uet.car4r.entity.Car;
import com.uet.car4r.entity.CarCategory;
import com.uet.car4r.mapper.CarMapper;
import com.uet.car4r.projection.CarProjection;
import com.uet.car4r.projection.CarWithCountProjection;
import com.uet.car4r.repository.CarCategoryRepository;
import com.uet.car4r.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CarService {
    CarRepository carRepository;
    CarMapper carMapper;
    CarCategoryRepository carCategoryRepository;

    public CarResponse getCarByCarCategory(String carCategoryId) {
        CarWithCountProjection countProjection = carRepository.findCarWithCountByCategoryId(carCategoryId);
        if (countProjection == null) {
            throw new EntityNotFoundException("No cars found for the specified category");
        }

        List<CarProjection> carProjections = carRepository.findCarsByCategoryId(carCategoryId);
        List<CarDetailResponse> carDetails = carProjections.stream()
                .map(carMapper::toCarDetailResponse)
                .collect(Collectors.toList());

        CarResponse response = new CarResponse();
        response.setCategoryId(countProjection.getCategoryId());
        response.setNumberOfCar(countProjection.getNumberOfCar());
        response.setCars(carDetails);

        return response;
    }

    public CarDetailResponse createCar(CarRequest carRequest) {
        Car car = carMapper.toCar(carRequest);
        CarCategory category = carCategoryRepository
                .findById(carRequest.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Car category not found"));
        car.setCategory(category);
        Car savedCar = carRepository.save(car);
        return carMapper.toCarDetailResponse(savedCar);
    }

    @Transactional
    public CarDetailResponse updateCar(String id, CarRequest carRequest) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));
        carMapper.updateCar(car, carRequest);

        CarCategory category = carCategoryRepository.findById(carRequest.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Car category not found"));
        car.setCategory(category);

        Car updatedCar = carRepository.save(car);
        return carMapper.toCarDetailResponse(updatedCar);
    }

    @Transactional
    public void deleteCar(String id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + id));
        CarCategory category = car.getCategory();
        if (category != null) {
            category.getCars().remove(car);
            carCategoryRepository.save(category);
        }
        carRepository.deleteById(id);
    }
}
