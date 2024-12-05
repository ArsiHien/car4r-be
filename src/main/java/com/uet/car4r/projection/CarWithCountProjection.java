package com.uet.car4r.projection;

import java.util.List;

public interface CarWithCountProjection {
    String getCategoryId();

    int getNumberOfCar();

    List<CarProjection> getCars();
}
