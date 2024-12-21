package com.uet.car4r.service;

import com.uet.car4r.constant.Role;
import com.uet.car4r.dto.request.StaffCreationRequest;
import com.uet.car4r.dto.response.staff.StaffCreationResponse;
import com.uet.car4r.dto.response.staff.StaffResponse;
import com.uet.car4r.entity.User;
import com.uet.car4r.mapper.StaffMapper;
import com.uet.car4r.mapper.UserMapper;
import com.uet.car4r.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffService {

    UserRepository userRepository;
    StaffMapper staffMapper;
    UserMapper userMapper;
    BCryptPasswordEncoder bCryptPasswordEncoder;

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

    public StaffCreationResponse createStaff(StaffCreationRequest request) {
        String baseUsername = generateUsername(request.getFirstName(), request.getLastName());
        String uniqueUsername = ensureUniqueUsername(baseUsername);
        String password = uniqueUsername + request.getPhone();

        User user = userMapper.toUser(request)
                .toBuilder()
                .username(uniqueUsername)
                .avatar("https://picsum.photos/500")
                .email(String.format("%s@car4r.com", uniqueUsername))
                .password(bCryptPasswordEncoder.encode(password))
                .role(Role.STAFF)
                .build();
        User response = userRepository.save(user);
        response.setPassword(password);
        return staffMapper.toStaffCreationResponse(response);
    }

    private String generateUsername(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("First and last name must not be null");
        }
        String baseUsername = firstName.charAt(0) + lastName;
        return baseUsername.toLowerCase().replaceAll("[^a-z0-9]", "");
    }

    private String ensureUniqueUsername(String baseUsername) {
        String uniqueUsername = baseUsername;
        int counter = 1;
        while (userRepository.existsByUsername(uniqueUsername)) {
            uniqueUsername = baseUsername + counter;
            counter++;
        }
        return uniqueUsername;
    }

}
