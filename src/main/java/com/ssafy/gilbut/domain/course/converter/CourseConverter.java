package com.ssafy.gilbut.domain.course.converter;

import com.ssafy.gilbut.domain.course.model.dto.CourseResponse;
import com.ssafy.gilbut.domain.course.model.dto.RouteResponse;
import com.ssafy.gilbut.domain.course.model.entity.Course;
import com.ssafy.gilbut.domain.course.model.entity.Route;

import java.util.Optional;

public class CourseConverter {

    public static CourseResponse.DetailResultDTO toDetailResultDTO(Course course) {
        Optional<Route> route = Optional.ofNullable(course.getRoute());

        RouteResponse.DetailResultDTO routeDTO = RouteConverter
                .toDetailResultDTO(route.orElseGet(Route::new));

        return CourseResponse.DetailResultDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .dist(course.getDist())
                .turnaround(course.getTurnaround())
                .level(course.getLevel())
                .cycle(course.getCycle())
                .summary(course.getSummary())
                .detail(course.getDetail())
                .travelPoint(course.getTravelPoint())
                .travelerInfo(course.getTravelerInfo())
                .sigun(course.getSigun())
                .roadOrBike(course.getRoadOrBike())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .route(routeDTO)
                .build();
    }
}
