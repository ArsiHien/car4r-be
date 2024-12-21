package com.uet.car4r.controller;

import com.uet.car4r.dto.request.StaffCreationRequest;
import com.uet.car4r.dto.response.staff.StaffCreationResponse;
import com.uet.car4r.dto.response.staff.StaffResponse;
import com.uet.car4r.service.StaffService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/staffs")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffController {
    StaffService staffService;

    @GetMapping
    List<StaffResponse> getStaffs(){
        return staffService.getStaffs();
    }

    @GetMapping("/{staffId}")
    StaffResponse getUser(@PathVariable("staffId") String staffId){
        return staffService.getStaff(staffId);
    }

    @PostMapping
    ResponseEntity<StaffCreationResponse> createStaff(@RequestBody StaffCreationRequest request){
        StaffCreationResponse response = staffService.createStaff(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{staffId}")
    String deleteStaff(@PathVariable String staffId){
        staffService.deleteStaff(staffId);
        return "Staff has been deleted";
    }
}
