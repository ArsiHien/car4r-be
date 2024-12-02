package com.uet.car4r.controller;

import com.uet.car4r.dto.response.AmenityResponse;
import com.uet.car4r.service.AmenityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/amenities")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AmenityController {
    AmenityService amenityService;

    @GetMapping()
    ResponseEntity<List<AmenityResponse>> getAmenities() {
        return ResponseEntity.ok(amenityService.getAmenities());
    }
}
