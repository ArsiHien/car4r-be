package com.uet.car4r.service;

import com.uet.car4r.constant.AppConstants;
import com.uet.car4r.dto.request.CarRequest;
import com.uet.car4r.dto.response.CarDetailResponse;
import com.uet.car4r.dto.response.CarWithCategoryResponse;
import com.uet.car4r.dto.response.CarWithStatusResponse;
import com.uet.car4r.entity.Car;
import com.uet.car4r.entity.CarCategory;
import com.uet.car4r.entity.CarGpsLog;
import com.uet.car4r.mapper.CarMapper;
import com.uet.car4r.projection.CarProjection;
import com.uet.car4r.projection.CarWithCategoryProjection;
import com.uet.car4r.projection.CarWithStatusProjection;
import com.uet.car4r.repository.CarCategoryRepository;
import com.uet.car4r.repository.CarGpsLogRepository;
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
    CarGpsLogRepository carGpsLogRepository;
    CarCategoryRepository carCategoryRepository;
    CarMapper carMapper;

    public CarWithCategoryResponse getCarByCarCategory(String carCategoryId) {
        CarWithCategoryProjection projection = carRepository.findCarWithCountByCategoryId(carCategoryId);
        if (projection == null) {
            throw new EntityNotFoundException("No cars found for the specified category");
        }

        List<CarProjection> carProjections = carRepository.findCarsByCategoryId(carCategoryId);
        List<CarDetailResponse> carDetails = carProjections.stream()
                .map(carMapper::toCarDetailResponse)
                .collect(Collectors.toList());

        CarWithCategoryResponse response = new CarWithCategoryResponse();
        response.setCategoryId(projection.getCategoryId());
        response.setNumberOfCar(projection.getNumberOfCar());
        response.setCars(carDetails);

        return response;
    }

    public CarWithStatusResponse getCarByStatus(String status) {
        Car.CarStatus carStatus = Car.CarStatus.valueOf(status.toUpperCase());
        CarWithStatusProjection statusProjection = carRepository.findCarStatusCount(carStatus);

        List<CarProjection> cars;
        if (carStatus == Car.CarStatus.RENTED)
            cars = carRepository.findCarsCurrentlyRented(carStatus);
        else
            cars = carRepository.findCarsByStatus(carStatus);

        List<CarDetailResponse> carDetails = cars.stream()
                .map(carMapper::toCarDetailResponse)
                .toList();

        CarWithStatusResponse response = new CarWithStatusResponse();
        response.setStatus(statusProjection.getStatus());
        response.setNumberOfCar(statusProjection.getNumberOfCar());
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

    public List<CarGpsLog> getRouteForCar(String carId) {
        int fileIndex = Math.abs(carId.hashCode() % AppConstants.NUMBER_OF_ROUTE_FILES) + 1;
        String mappedCarId = String.valueOf(fileIndex);

        return carGpsLogRepository.findAllByCarIdOrderBySequenceOrder(mappedCarId);
    }

}
