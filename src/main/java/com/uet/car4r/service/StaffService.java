package com.uet.car4r.service;

import com.uet.car4r.constant.Role;
import com.uet.car4r.dto.response.StaffResponse;
import com.uet.car4r.mapper.StaffMapper;
import com.uet.car4r.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffService {

    UserRepository userRepository;
    StaffMapper staffMapper;

    public List<StaffResponse> getStaffs() {
        return userRepository.getStaffs(Role.STAFF).stream().map(staffMapper::toStaffResponse).toList();
    }

    public StaffResponse getStaff(String staffId) {
        return staffMapper.toStaffResponse(userRepository.getStaffById(staffId, Role.STAFF)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public void deleteStaff(String staffId) {
        userRepository.deleteById(staffId);
    }
}
