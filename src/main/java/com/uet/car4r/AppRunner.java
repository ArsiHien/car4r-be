package com.uet.car4r;

import com.uet.car4r.constant.AppConstants;
import com.uet.car4r.service.LoadGpsLogService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppRunner implements CommandLineRunner {

    LoadGpsLogService loadGpsLogService;

    @Override
    public void run(String... args) {
        for (int i = 1; i <= AppConstants.NUMBER_OF_ROUTE_FILES; i++) {
            loadGpsLogService.readGpsLogFile("src/main/java/com/uet/car4r/data/route" + i + ".txt", i);
        }
    }
}