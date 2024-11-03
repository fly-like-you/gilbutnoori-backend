package com.ssafy.gilbut.course.service;


import com.ssafy.gilbut.course.model.dto.CourseDTO;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    Page<Map<String, Object>> courseList(Map<String, Object> paramMap, Pageable page);

    CourseDTO courseDetail(String courseId);
}
