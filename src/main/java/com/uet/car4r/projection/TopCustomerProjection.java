package com.uet.car4r.projection;

public interface TopCustomerProjection {
    String getId();

    String getName();

    String getEmail();

    long getBookingCount();

    long getTotalRevenue();
}
