package com.uet.car4r.projection;

import java.util.List;

public interface CarWithCategoryProjection {
    String getCategoryId();

    int getNumberOfCar();

    List<CarProjection> getCars();
}
