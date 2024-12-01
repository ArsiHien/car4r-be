package com.uet.car4r.service;

import com.uet.car4r.dto.request.CarCategoryCreationRequest;
import com.uet.car4r.dto.request.CarCategoryUpdateRequest;
import com.uet.car4r.dto.response.*;
import com.uet.car4r.entity.Amenity;
import com.uet.car4r.entity.CarCategory;
import com.uet.car4r.entity.CarImage;
import com.uet.car4r.mapper.AmenityMapper;
import com.uet.car4r.mapper.CarCategoryMapper;
import com.uet.car4r.mapper.CarImageMapper;
import com.uet.car4r.projection.BasicCarCategoryProjection;
import com.uet.car4r.repository.AmenityRepository;
import com.uet.car4r.repository.CarCategoryRepository;
import com.uet.car4r.repository.CarImageRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
    CarImageRepository carImageRepository;
    ImageUploadService imageUploadService;
    CarCategoryMapper carCategoryMapper;
    CarImageMapper carImageMapper;
    AmenityMapper amenityMapper;

    public List<CarCategoryDetailResponse> getDetailCarCategories() {
        List<BasicCarCategoryProjection> projections = carCategoryRepository.findAllBasicCarCategories();
        return projections.stream().map(basicCarCategoryProjection -> {
            CarCategoryDetailResponse response = carCategoryMapper.toCarCategoryDetailResponse(basicCarCategoryProjection);
            Set<CarImageResponse> carImages = carImageRepository.findByCategoryId(basicCarCategoryProjection.getId())
                    .stream()
                    .map(carImageMapper::toCarImageResponse)
                    .collect(Collectors.toSet());
            Set<AmenityResponse> amenities = amenityRepository.findByCarCategoryId(basicCarCategoryProjection.getId())
                    .stream()
                    .map(amenityMapper::toAmenityResponse)
                    .collect(Collectors.toSet());
            response.setCarImages(carImages);
            response.setAmenities(amenities);
            return response;
        }).collect(Collectors.toList());
    }

    public List<CarCategoryBasicResponse> getBasicCarCategories() {
        List<BasicCarCategoryProjection> projections = carCategoryRepository.findAllBasicCarCategories();

        return projections.stream()
                .map(carCategoryMapper::toCarCategoryBasicResponse)
                .collect(Collectors.toList());
    }

    public CarCategoryDetailResponse getCarCategory(String id) {
        return carCategoryMapper.toCarCategoryDetailResponse(
                carCategoryRepository.findById(id).orElse(null)
        );
    }

    public CarCategoryDetailResponse createCarCategory(CarCategoryCreationRequest request) {
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
        return carCategoryMapper.toCarCategoryDetailResponse(savedCarCategory);
    }

    @Transactional
    public CarCategoryDetailResponse updateCarCategory(String carCategoryId, CarCategoryUpdateRequest request) {
        CarCategory carCategory = carCategoryRepository.findById(carCategoryId)
                .orElseThrow(() -> new RuntimeException("Car category not found"));
        carCategoryMapper.updateCarCategory(carCategory, request);
        if (request.getMainImage() != null) {
            carCategory.setMainImage(uploadMainImage(request.getMainImage()));
        }
        if (request.getAmenityNames() != null) {
            carCategory.setAmenities(resolveAmenities(request.getAmenityNames()));
        }
        if (request.getCarImages() != null && !request.getCarImages().isEmpty()) {
            carCategory.setCarImages(uploadCarImages(request.getCarImages(), carCategory));
        }
        return carCategoryMapper.toCarCategoryDetailResponse(carCategoryRepository.save(carCategory));
    }

    public void deleteCarCategory(String id) {
        CarCategory carCategory = carCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CarCategory not found with id: " + id));

        carCategory.getAmenities().clear();
        carCategoryRepository.save(carCategory);
        carCategoryRepository.deleteById(id);
    }

    public List<CarCategoryCountResponse> getCarCategoryCountByType() {
        return carCategoryRepository.countCarCategoryByType().stream()
                .map(carCategoryMapper::toCarCategoryCountResponse).toList();
    }

    public List<CarCategoryCountResponse> getCarCategoryCountByNumberOfPerson() {
        return carCategoryRepository.countCarCategoryByNumberOfPerson().stream()
                .map(carCategoryMapper::toCarCategoryCountResponse).toList();
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
        deleteOldImages(carCategory);

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

    private void deleteOldImages(CarCategory carCategory) {
        Set<CarImage> oldImages = carCategory.getCarImages();

        System.out.println("old: " + oldImages);
        if (oldImages != null && !oldImages.isEmpty()) {
            carCategory.getCarImages().clear();
            carImageRepository.deleteByCategoryId(carCategory.getId());
        }
        System.out.println("after: " + carCategory.getCarImages());
        System.out.println("db " + carImageRepository.findByCategoryId(carCategory.getId()));
    }

}
