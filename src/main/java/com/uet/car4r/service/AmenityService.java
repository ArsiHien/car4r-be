package com.uet.car4r.service;

import com.uet.car4r.dto.response.AmenityResponse;
import com.uet.car4r.mapper.AmenityMapper;
import com.uet.car4r.repository.AmenityRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AmenityService {
    AmenityRepository amenityRepository;
    AmenityMapper amenityMapper;

    public List<AmenityResponse> getAmenities() {
        return amenityRepository.findAll().stream()
                .map(amenityMapper::toAmenityResponse).toList();
    }
}
