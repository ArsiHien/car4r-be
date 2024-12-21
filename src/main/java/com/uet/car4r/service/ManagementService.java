package com.uet.car4r.service;

import com.uet.car4r.dto.response.RevenueResponse;
import com.uet.car4r.dto.response.carcategory.CarCategoryRentalStatisticResponse;
import com.uet.car4r.dto.response.MonthlyRevenueResponse;
import com.uet.car4r.dto.response.RevenueByCategoryTypeResponse;
import com.uet.car4r.dto.response.TopCustomerResponse;
import com.uet.car4r.mapper.CustomerMapper;
import com.uet.car4r.mapper.ManagementMapper;
import com.uet.car4r.projection.CarCategoryRentalStatisticsProjection;
import com.uet.car4r.projection.MonthlyRevenueProjection;
import com.uet.car4r.projection.RevenueByCategoryProjection;
import com.uet.car4r.projection.TopCustomerProjection;
import com.uet.car4r.repository.BookingRepository;
import com.uet.car4r.repository.CarCategoryRepository;
import com.uet.car4r.repository.CarRepository;
import com.uet.car4r.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ManagementService {
    BookingRepository bookingRepository;
    CarCategoryRepository carCategoryRepository;
    CustomerRepository customerRepository;
    ManagementMapper managementMapper;
    CustomerMapper customerMapper;
    CarRepository carRepository;

    public RevenueResponse getRevenueData() {
        long totalRevenue = getTotalRevenue();
        //long todayRevenue = getTodayRevenue();
        long totalCar = carRepository.getTotalCar();
        long rentedCar = carRepository.getRentedCar();
        long availableCar = carRepository.getAvailableCar();
        RevenueResponse response = new RevenueResponse();
        response.setTotalRevenue(totalRevenue);
        //response.setTodayRevenue(todayRevenue);
        response.setTotalCar(totalCar);
        response.setRentedCar(rentedCar);
        response.setAvailableCar(availableCar);
        return response;
    }
    public long getTotalRevenue() {
        return bookingRepository.calculateTotalRevenue();
    }

    /**public long getTodayRevenue() {
        LocalDate today = LocalDate.now();
        LocalDate start = LocalDate.from(today.atTime(0, 0));
        LocalDate end = LocalDate.from(today.atTime(23, 59));
        return bookingRepository.calculateTodayRevenue(start, end);
    }**/

    public List<MonthlyRevenueResponse> getLast12MonthsRevenue() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(11).withDayOfMonth(1);

        List<MonthlyRevenueProjection> projections = bookingRepository.findLast12MonthsRevenue(startDate, endDate);

        Map<String, Long> revenueMap = projections.stream()
                .collect(Collectors.toMap(
                        projection -> String.format("%d-%02d", projection.getYear(), projection.getMonth()),
                        MonthlyRevenueProjection::getRevenue
                ));

        Map<String, Long> fullRevenueMap = new LinkedHashMap<>();
        LocalDate currentMonth = startDate;
        while (!currentMonth.isAfter(endDate)) {
            String monthKey = currentMonth.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            fullRevenueMap.put(monthKey, revenueMap.getOrDefault(monthKey, 0L));
            currentMonth = currentMonth.plusMonths(1);
        }

        return fullRevenueMap.entrySet().stream()
                .map(entry -> new MonthlyRevenueResponse(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<RevenueByCategoryTypeResponse> getRevenueByCategoryType() {
        List<RevenueByCategoryProjection> projections = bookingRepository.findRevenueByCategoryType();

        return managementMapper.toRevenueByCategoryResponseList(projections);
    }

    public List<CarCategoryRentalStatisticResponse> getMostRentedCarCategories() {
        List<CarCategoryRentalStatisticsProjection> projections = carCategoryRepository.findMostRentedCarCategories();
        return managementMapper.toCarCategoryRentalStatisticResponses(projections);
    }

    public List<CarCategoryRentalStatisticResponse> getLeastRentedCarCategories() {
        List<CarCategoryRentalStatisticsProjection> projections = carCategoryRepository.findLeastRentedCarCategories();
        return managementMapper.toCarCategoryRentalStatisticResponses(projections);
    }

    public List<CarCategoryRentalStatisticResponse> getBestPerformingCarCategories() {
        List<CarCategoryRentalStatisticsProjection> projections = carCategoryRepository.findBestPerformingCategories();
        return managementMapper.toCarCategoryRentalStatisticResponses(projections);
    }

    public List<TopCustomerResponse> getTopCustomers() {
        List<TopCustomerProjection> projections = customerRepository.findTopCustomersByBookingFrequencyAndRevenue();
        return customerMapper.toTopCustomerResponse(projections);
    }
}
