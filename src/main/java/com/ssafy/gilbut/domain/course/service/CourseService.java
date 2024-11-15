package com.ssafy.gilbut.domain.course.service;


import com.ssafy.gilbut.domain.course.model.dto.CourseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    Page<CourseDTO> courseList(Pageable page);

    CourseDTO courseDetail(String courseId);

    CourseDTO courseAttractionList(String courseId);
}
