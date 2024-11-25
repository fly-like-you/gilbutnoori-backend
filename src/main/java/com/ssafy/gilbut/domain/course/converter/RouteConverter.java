package com.ssafy.gilbut.domain.course.converter;

import com.ssafy.gilbut.domain.course.model.dto.RouteResponse;
import com.ssafy.gilbut.domain.course.model.dto.RouteResponse.DetailPageResultDTO;
import com.ssafy.gilbut.domain.course.model.entity.Route;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

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
        List<RouteResponse.DetailResultDTO> routeDTOs = routes.stream().map(RouteConverter::toDetailResultDTO).collect(Collectors.toList());
        PageImpl<RouteResponse.DetailResultDTO> page = new PageImpl<>(routeDTOs, pageable, totalCount);

        return RouteResponse.DetailPageResultDTO.builder()
                .routes(routeDTOs)
                .listSize(routeDTOs.size())
                .isFirstPage(page.isFirst())
                .isLastPage(page.isLast())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }
}
