package com.uet.car4r.projection;

import java.time.LocalDate;

public interface BookingProjection {
    String getId();

    String getCustomerName();

    String getCarCategoryName();

    String getCarLicensePlate();

    LocalDate getBookingDate();

    LocalDate getStartDate();

    LocalDate getReturnDate();

    String getLoanPlace();

    String getReturnPlace();

    Long getTotalPrice();

    String getStatus();
}
