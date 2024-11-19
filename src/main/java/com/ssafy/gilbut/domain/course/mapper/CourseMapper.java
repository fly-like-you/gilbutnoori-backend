package com.ssafy.gilbut.domain.course.mapper;

import com.ssafy.gilbut.domain.course.model.dto.CourseDTO;
import com.ssafy.gilbut.domain.course.model.dto.CourseSearchCriteria;
import com.ssafy.gilbut.domain.course.model.dto.RouteDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CourseMapper {
    List<CourseDTO> courseList(@Param("pageable") Pageable pageable);

    Optional<CourseDTO> courseDetail(@Param("courseId") String courseId);

    List<RouteDTO> routeList(@Param("pageable") Pageable page);

    int countCourses();

    int countRoutes();

    int countCoursesBy(@Param("criteria") CourseSearchCriteria criteria);

    List<CourseDTO> courseSearchBy(@Param("criteria") CourseSearchCriteria criteria,
                                  @Param("pageable") Pageable pageable);
}
