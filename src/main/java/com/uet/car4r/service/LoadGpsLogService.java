package com.uet.car4r.service;

import com.uet.car4r.entity.CarGpsLog;
import com.uet.car4r.repository.CarGpsLogRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoadGpsLogService {

    CarGpsLogRepository carGpsLogRepository;

    public void readGpsLogFile(String fileName, int index) {
        String carId = String.valueOf(index);
        boolean exists = carGpsLogRepository.existsByCarId(carId);
        if (exists) {
            System.out.println("Car ID " + carId + " already exists in the database. Skipping file: " + fileName);
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int sequenceOrder = 1;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    double longitude = Double.parseDouble(parts[0]);
                    double latitude = Double.parseDouble(parts[1]);

                    CarGpsLog gpsLog = new CarGpsLog();
                    gpsLog.setCarId(carId);
                    gpsLog.setLongitude(longitude);
                    gpsLog.setLatitude(latitude);
                    gpsLog.setSequenceOrder(sequenceOrder);

                    carGpsLogRepository.save(gpsLog);
                    sequenceOrder++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading GPS log file: " + fileName, e);
        }
    }
}
