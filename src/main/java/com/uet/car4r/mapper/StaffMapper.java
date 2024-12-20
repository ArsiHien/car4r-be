package com.uet.car4r.mapper;

import com.uet.car4r.dto.response.staff.StaffCreationResponse;
import com.uet.car4r.dto.response.staff.StaffResponse;
import com.uet.car4r.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    @Mapping(target = "name", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    StaffResponse toStaffResponse(User user);

    @Mapping(target = "name", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    StaffCreationResponse toStaffCreationResponse(User user);
}
