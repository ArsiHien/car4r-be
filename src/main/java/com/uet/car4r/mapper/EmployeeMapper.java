package com.uet.car4r.mapper;

import com.uet.car4r.dto.request.AddEmployeeRequest;
import com.uet.car4r.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus")
    Employee toEmployee(AddEmployeeRequest addEmployeeRequest);
}
