package com.ssafy.gilbut.domain.course.mapper;

import com.ssafy.gilbut.domain.course.model.dto.CourseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CourseMapper {

    List<CourseDTO> courseList(@Param("pageable") Pageable pageable);

    int countCourses();

    Optional<CourseDTO> courseDetail(@Param("courseId") String courseId);
}
