package com.ssafy.gilbut.domain.course.mapper;

import com.ssafy.gilbut.domain.course.model.dto.CourseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CourseMapper {

    List<CourseDTO> courseList(@Param("offset") long offset, @Param("pageSize") int pageSize);

    int countCourses();

    Optional<CourseDTO> courseDetail(String courseId);
}
