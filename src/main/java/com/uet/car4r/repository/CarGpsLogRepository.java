package com.uet.car4r.repository;

import com.uet.car4r.entity.CarGpsLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarGpsLogRepository extends JpaRepository<CarGpsLog, String> {
    boolean existsByCarId(String carId);

    List<CarGpsLog> findAllByCarIdOrderBySequenceOrder(String carId);
}
