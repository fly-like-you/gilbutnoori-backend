package com.ssafy.gilbut.domain.course.service;


import com.ssafy.gilbut.domain.course.model.dto.CourseDTO;
import com.ssafy.gilbut.domain.course.model.dto.CourseSearchCriteria;
import com.ssafy.gilbut.domain.course.model.dto.RouteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    Page<CourseDTO> courseList(Pageable page);

    CourseDTO courseDetail(String courseId);

    Page<RouteDTO> routeList(Pageable page);

    Page<CourseDTO> courseSearch(CourseSearchCriteria criteria, Pageable page);
}
