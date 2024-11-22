package com.ssafy.gilbut.domain.course.converter;

import com.ssafy.gilbut.domain.course.model.dto.RouteResponse;
import com.ssafy.gilbut.domain.course.model.entity.Route;

public class RouteConverter {
    public static RouteResponse.DetailResultDTO toDetailResultDTO(Route route) {
        return RouteResponse.DetailResultDTO.builder()
                .id(route.getId())
                .name(route.getName())
                .summary(route.getSummary())
                .detail(route.getDetail())
                .roadOrBike(route.getRoadOrBike())
                .createdAt(route.getCreatedAt())
                .updatedAt(route.getUpdatedAt())
                .build();
    }
}
