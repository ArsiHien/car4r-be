package com.uet.car4r.projection;

import com.uet.car4r.entity.CarCategory;

public interface CarCategoryRentalStatisticsProjection {
    String getId();

    String getName();

    String getType();

    int getNumberOfPerson();

    CarCategory.Steering getSteering();

    int getGasoline();

    int getPrice();

    int getPromotionPrice();

    String getMainImage();

    double getAverageRating();

    long getBookingCount();

    long getTotalRevenue();
}
