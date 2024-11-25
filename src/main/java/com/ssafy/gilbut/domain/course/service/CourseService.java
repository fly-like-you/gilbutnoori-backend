package com.ssafy.gilbut.domain.course.service;


import com.ssafy.gilbut.domain.course.model.dto.CourseRequest;
import com.ssafy.gilbut.domain.course.model.dto.CourseResponse;
import com.ssafy.gilbut.domain.course.model.dto.RouteResponse;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    CourseResponse.SimplePageResultDTO courseList(Pageable page);

    CourseResponse.DetailResultDTO courseDetail(String courseId);

    RouteResponse.DetailPageResultDTO routeList(Pageable page);

    CourseResponse.SimplePageResultDTO courseSearch(CourseRequest.SearchCriteria criteria, Pageable page);

    RouteResponse.DetailResultDTO getRouteDetail(String routeId);
}
