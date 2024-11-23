package com.ssafy.gilbut.domain.course.mapper;

import com.ssafy.gilbut.domain.course.model.dto.CourseRequest;
import com.ssafy.gilbut.domain.course.model.entity.Course;
import com.ssafy.gilbut.domain.course.model.entity.Route;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Mapper
public interface CourseMapper {
    List<Course> courseList(@Param("pageable") Pageable pageable, @Param("sort") Sort sort);
    List<Course> courseSearchBy(@Param("criteria") CourseRequest.SearchCriteria criteria,
                                @Param("pageable") Pageable pageable);
    Optional<Course> courseDetail(@Param("courseId") String courseId);

    List<Route> routeList(@Param("pageable") Pageable page);

    int countCourses();

    int countRoutes();

    int countCoursesBy(@Param("criteria") CourseRequest.SearchCriteria criteria);


}
