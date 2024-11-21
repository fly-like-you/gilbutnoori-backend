package com.ssafy.gilbut.domain.course.service;


import com.ssafy.gilbut.domain.course.model.dto.CourseDetailResponseDTO;
import com.ssafy.gilbut.domain.course.model.dto.CourseSearchCriteria;
import com.ssafy.gilbut.domain.course.model.dto.RouteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    Page<CourseDetailResponseDTO> courseList(Pageable page);

    CourseDetailResponseDTO courseDetail(String courseId);

    Page<RouteDTO> routeList(Pageable page);

    Page<CourseDetailResponseDTO> courseSearch(CourseSearchCriteria criteria, Pageable page);
}
