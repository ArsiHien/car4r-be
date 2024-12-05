package com.uet.car4r.projection;

import java.util.List;

public interface CarWithStatusProjection {
    String getStatus();

    int getNumberOfCar();

    List<CarProjection> getCars();
}
