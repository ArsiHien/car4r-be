package com.uet.car4r.mapper;

import com.uet.car4r.dto.response.TopCustomerResponse;
import com.uet.car4r.projection.TopCustomerProjection;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    TopCustomerResponse toTopCustomerResponse(TopCustomerProjection projection);

    List<TopCustomerResponse> toTopCustomerResponse(List<TopCustomerProjection> projections);
}
