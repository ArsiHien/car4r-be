package com.uet.car4r.projection;

import com.uet.car4r.entity.CarCategory;

import java.util.Set;

public interface DetailCarCategoryProjection {
    String getId();

    String getName();

    String getType();

    int getNumberOfPerson();

    CarCategory.Steering getSteering();

    int getGasoline();

    int getPrice();

    int getPromotionPrice();

    String getMainImage();

    String getDescription();

    Set<CarImageProjection> getCarImages();

    Set<AmenityProjection> getAmenities();
}

