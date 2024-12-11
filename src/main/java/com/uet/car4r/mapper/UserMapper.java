package com.uet.car4r.mapper;

import com.uet.car4r.dto.UserDTO;
import com.uet.car4r.entity.Customer;
import com.uet.car4r.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User dtoToEntity(UserDTO userDTO);

  UserDTO entityToDto(User user);

  Customer userEntityToCustomerEntity(User user);
}
