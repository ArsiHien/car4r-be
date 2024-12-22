package com.uet.car4r.controller;

import com.uet.car4r.dto.request.AddEmployeeRequest;
import com.uet.car4r.entity.Employee;
import com.uet.car4r.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/manager")
public class ManagerController {
    private ManagerService managerService;
    @GetMapping("/revenue")
    public ResponseEntity<String> getRevenue () {
        return null;
    }

    @GetMapping("/staffs")
    public ResponseEntity<List<Employee>> getEmployees() {
        return null;
    }

    @PostMapping("/staffs/add")
    public Employee addEmployee(@RequestBody AddEmployeeRequest addEmployeeRequest) {
        Employee employee = managerService.addEmployee(addEmployeeRequest);
    }
}
