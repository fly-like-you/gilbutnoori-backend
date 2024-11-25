package com.ssafy.gilbut.domain.course.converter;

import com.ssafy.gilbut.domain.course.model.dto.CourseResponse;
import com.ssafy.gilbut.domain.course.model.dto.CourseResponse.SimpleResultDTO;
import com.ssafy.gilbut.domain.course.model.dto.RouteResponse;
import com.ssafy.gilbut.domain.course.model.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class CourseConverter {

    public static CourseResponse.DetailResultDTO toDetailResultDTO(Course course) {
        RouteResponse.SimpleResultDTO routeSimpleDTO = Optional.ofNullable(course.getRoute())
                .map(RouteConverter::toSimpleResultDTO)
                .orElse(null);

        return CourseResponse.DetailResultDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .route(routeSimpleDTO)
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
                .build();
    }

    public static CourseResponse.SimpleResultDTO toSimpleResultDTO(Course course) {

        RouteResponse.SimpleResultDTO routeDTO = Optional.ofNullable(course.getRoute())
                .map(RouteConverter::toSimpleResultDTO)
                .orElse(null);

        return CourseResponse.SimpleResultDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .summary(course.getSummary())
                .sigun(course.getSigun())
                .route(routeDTO)
                .dist(course.getDist())
                .turnaround(course.getTurnaround())
                .level(course.getLevel())
                .build();
    }

    public static CourseResponse.SimplePageResultDTO toSimplePageResultDTO(List<Course> courses, Pageable pageable,
                                                                           Integer totalCount) {
        List<SimpleResultDTO> list = courses.stream().map(CourseConverter::toSimpleResultDTO).toList();
        Page<CourseResponse.SimpleResultDTO> page = new PageImpl<>(list, pageable, totalCount);

        return CourseResponse.SimplePageResultDTO.builder()
                .courses(list)
                .listSize(list.size())
                .isFirstPage(page.isFirst())
                .isLastPage(page.isLast())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
