package com.uet.car4r.projection;

import java.time.LocalDate;

public interface ReviewProjection {
    String getId();

    String getCustomerName();

    String getCarCategoryName();

    String getReview();

    int getRating();

    LocalDate getReviewDate();
}
