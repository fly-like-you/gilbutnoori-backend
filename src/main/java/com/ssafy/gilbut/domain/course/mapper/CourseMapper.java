package com.ssafy.gilbut.domain.course.mapper;

import com.ssafy.gilbut.domain.course.model.dto.CourseDTO;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper {

    List<Map<String, Object>> courseList(Map<String, Object> paramMap);

    int countCourses();

    Optional<CourseDTO> courseDetail(String courseId);
}
