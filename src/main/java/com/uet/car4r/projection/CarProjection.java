package com.uet.car4r.projection;

import java.time.LocalDate;

public interface CarProjection {
    String getId();

    String getLicensePlate();

    String getCategoryName();

    String getCategoryType();

    String getMainImage();

    String getStatus();

    LocalDate getCurrentBookingStartDate();

    LocalDate getCurrentBookingReturnDate();

    String getCurrentBookingLoanPlace();

    String getCurrentBookingReturnPlace();

    Long getCurrentBookingTotalPrice();
}
