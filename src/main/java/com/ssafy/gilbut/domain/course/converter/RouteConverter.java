package com.ssafy.gilbut.domain.course.converter;

import com.ssafy.gilbut.domain.course.model.dto.RouteResponse;
import com.ssafy.gilbut.domain.course.model.dto.RouteResponse.DetailPageResultDTO;
import com.ssafy.gilbut.domain.course.model.entity.Route;
import java.util.List;
import org.springframework.data.domain.Pageable;

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

    public static RouteResponse.SimpleResultDTO toSimpleResultDTO(Route route) {
        return RouteResponse.SimpleResultDTO.builder()
                .id(route.getId())
                .name(route.getName())
                .summary(route.getSummary())
                .build();
    }

    public static DetailPageResultDTO toDetailPageResultDTO(List<Route> routes, Pageable pageable, int totalCount) {

        return null;
    }
}
