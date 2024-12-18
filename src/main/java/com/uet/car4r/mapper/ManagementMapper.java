package com.uet.car4r.mapper;

import com.uet.car4r.dto.response.carcategory.CarCategoryRentalStatisticResponse;
import com.uet.car4r.dto.response.MonthlyRevenueResponse;
import com.uet.car4r.dto.response.RevenueByCategoryTypeResponse;
import com.uet.car4r.projection.CarCategoryRentalStatisticsProjection;
import com.uet.car4r.projection.MonthlyRevenueProjection;
import com.uet.car4r.projection.RevenueByCategoryProjection;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ManagementMapper {

    CarCategoryRentalStatisticResponse toCarCategoryRentalStatisticResponse(CarCategoryRentalStatisticsProjection projection);

    List<CarCategoryRentalStatisticResponse> toCarCategoryRentalStatisticResponses(List<CarCategoryRentalStatisticsProjection> projections);

    MonthlyRevenueResponse toMonthlyRevenueResponse(MonthlyRevenueProjection projection);

    RevenueByCategoryTypeResponse toRevenueByCategoryResponse(RevenueByCategoryProjection projection);

    List<MonthlyRevenueResponse> toMonthlyRevenueResponseList(List<MonthlyRevenueProjection> projections);

    List<RevenueByCategoryTypeResponse> toRevenueByCategoryResponseList(List<RevenueByCategoryProjection> projections);
}

