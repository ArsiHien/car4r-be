package com.uet.car4r.projection;

import com.uet.car4r.entity.CarCategory;

public interface BasicCarCategoryProjection {
    String getId();

    String getName();

    String getType();

    int getNumberOfPerson();

    CarCategory.Steering getSteering();

    int getGasoline();

    int getPrice();

    int getPromotionPrice();

    String getMainImage();
}
