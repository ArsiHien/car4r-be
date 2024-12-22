package com.uet.car4r.service;

import com.uet.car4r.dto.request.AddEmployeeRequest;
import com.uet.car4r.entity.Employee;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ManagerService {
    //add employee
    public Employee addEmployee(AddEmployeeRequest addEmployeeRequest) {
        Employee employee =
    }
}
