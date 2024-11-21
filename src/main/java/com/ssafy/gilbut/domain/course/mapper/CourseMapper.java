package com.ssafy.gilbut.domain.course.mapper;

import com.ssafy.gilbut.domain.course.model.dto.CourseDetailResponseDTO;
import com.ssafy.gilbut.domain.course.model.dto.CourseSearchCriteria;
import com.ssafy.gilbut.domain.course.model.dto.RouteDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CourseMapper {
    List<CourseDetailResponseDTO> courseList(@Param("pageable") Pageable pageable);

    Optional<CourseDetailResponseDTO> courseDetail(@Param("courseId") String courseId);

    List<RouteDTO> routeList(@Param("pageable") Pageable page);

    int countCourses();

    int countRoutes();

    int countCoursesBy(@Param("criteria") CourseSearchCriteria criteria);

    List<CourseDetailResponseDTO> courseSearchBy(@Param("criteria") CourseSearchCriteria criteria,
                                                 @Param("pageable") Pageable pageable);
}
