package com.uet.car4r.controller;

import com.uet.car4r.dto.response.RevenueResponse;
import com.uet.car4r.dto.response.carcategory.CarCategoryRentalStatisticResponse;
import com.uet.car4r.dto.response.MonthlyRevenueResponse;
import com.uet.car4r.dto.response.RevenueByCategoryTypeResponse;
import com.uet.car4r.dto.response.TopCustomerResponse;
import com.uet.car4r.service.ManagementService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/management")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ManagementController {
    ManagementService managementService;

    @GetMapping("/revenue/overview")
    public ResponseEntity<RevenueResponse> getRevenueData() {
        return ResponseEntity.ok(managementService.getRevenueData());
    }

    @GetMapping("/revenue/total")
    public ResponseEntity<Long> getTotalRevenue() {
        return ResponseEntity.ok(managementService.getTotalRevenue());
    }

    @GetMapping("/revenue/last12months")
    public ResponseEntity<List<MonthlyRevenueResponse>> getLast12MonthsRevenue() {
        return ResponseEntity.ok(managementService.getLast12MonthsRevenue());
    }

    @GetMapping("/revenue/by-category-type")
    public ResponseEntity<List<RevenueByCategoryTypeResponse>> getRevenueByCategoryType() {
        return ResponseEntity.ok(managementService.getRevenueByCategoryType());
    }

    @GetMapping("car-categories/most-rented")
    public ResponseEntity<List<CarCategoryRentalStatisticResponse>> getMostRentedCarCategories() {
        return ResponseEntity.ok(managementService.getMostRentedCarCategories());
    }

    @GetMapping("car-categories/least-rented")
    public ResponseEntity<List<CarCategoryRentalStatisticResponse>> getLeastRentedCarCategories() {
        return ResponseEntity.ok(managementService.getLeastRentedCarCategories());
    }

    @GetMapping("car-categories/best-performing")
    public ResponseEntity<List<CarCategoryRentalStatisticResponse>> getBestPerformingCarCategories() {
        return ResponseEntity.ok(managementService.getBestPerformingCarCategories());
    }

    @GetMapping("customers/top")
    public ResponseEntity<List<TopCustomerResponse>> getTopCustomers() {
        return ResponseEntity.ok(managementService.getTopCustomers());
    }
}
