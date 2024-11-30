package com.uet.car4r.service;

import com.uet.car4r.dto.request.CarCategoryCreationRequest;
import com.uet.car4r.dto.response.CarCategoryResponse;
import com.uet.car4r.entity.Amenity;
import com.uet.car4r.entity.CarCategory;
import com.uet.car4r.entity.CarImage;
import com.uet.car4r.mapper.CarCategoryMapper;
import com.uet.car4r.repository.AmenityRepository;
import com.uet.car4r.repository.CarCategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CarCategoryService {
    CarCategoryRepository carCategoryRepository;
    AmenityRepository amenityRepository;
    ImageUploadService imageUploadService;
    CarCategoryMapper carCategoryMapper;

    public List<CarCategoryResponse> getCarCategories() {
        return carCategoryRepository.findAll().stream().map(carCategoryMapper::toCarCategoryResponse).toList();
    }

    public CarCategoryResponse createCarCategory(CarCategoryCreationRequest request) {
        String mainImageUrl = uploadMainImage(request.getMainImage());

        CarCategory carCategory = carCategoryMapper.toCarCategory(request);
        carCategory.setMainImage(mainImageUrl);

        if (request.getAmenityNames() != null) {
            carCategory.setAmenities(resolveAmenities(request.getAmenityNames()));
        }

        if (request.getCarImages() != null && !request.getCarImages().isEmpty()) {
            carCategory.setCarImages(uploadCarImages(request.getCarImages(), carCategory));
        }

        CarCategory savedCarCategory = carCategoryRepository.save(carCategory);
        return carCategoryMapper.toCarCategoryResponse(savedCarCategory);
    }

    private String uploadMainImage(MultipartFile mainImage) {
        try {
            return imageUploadService.uploadImage(mainImage);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload main image: " + e.getMessage(), e);
        }
    }

    private Set<Amenity> resolveAmenities(Set<String> amenityNames) {
        return amenityNames.stream()
                .map(name -> amenityRepository.findByName(name)
                        .orElseGet(() -> Amenity.builder().name(name).build()))
                .collect(Collectors.toSet());
    }

    private Set<CarImage> uploadCarImages(Set<MultipartFile> carImages, CarCategory carCategory) {
        return carImages.stream()
                .map(imageFile -> {
                    try {
                        String imageUrl = imageUploadService.uploadImage(imageFile);
                        return CarImage.builder()
                                .category(carCategory)
                                .imageUrl(imageUrl)
                                .build();
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to upload car image: " + e.getMessage(), e);
                    }
                })
                .collect(Collectors.toSet());
    }

}
