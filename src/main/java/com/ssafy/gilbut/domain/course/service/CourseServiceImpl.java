package com.ssafy.gilbut.domain.course.service;

import com.ssafy.gilbut.advice.status.ErrorStatus;
import com.ssafy.gilbut.domain.course.converter.CourseConverter;
import com.ssafy.gilbut.domain.course.converter.RouteConverter;
import com.ssafy.gilbut.domain.course.mapper.CourseMapper;
import com.ssafy.gilbut.domain.course.model.dto.CourseRequest;
import com.ssafy.gilbut.domain.course.model.dto.CourseResponse;
import com.ssafy.gilbut.domain.course.model.dto.RouteResponse;
import com.ssafy.gilbut.domain.course.model.entity.Course;
import com.ssafy.gilbut.domain.course.model.entity.Route;
import com.ssafy.gilbut.exception.handler.GeneralExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{
    private final CourseMapper courseMapper;

    @Override
    public CourseResponse.SimplePageResultDTO courseList(Pageable page) {
        log.trace("offset = {}, pageSize = {}", page.getOffset(), page.getPageSize());
        List<Sort.Order> orders = page.getSort().stream()
                .map(order -> new Sort.Order(order.getDirection(), "c." + order.getProperty()))
                .collect(Collectors.toList());

        // 새로운 Sort 객체 생성
        Sort updatedSort = Sort.by(orders);
        List<Course> courses = courseMapper.courseList(page, updatedSort);
        int count = courseMapper.countCourses();

        // 기존 Sort 객체에서 Order 리스트 생성

        return CourseConverter.toSimplePageResultDTO(courses, page, count);
    }

    @Override
    public CourseResponse.SimplePageResultDTO courseSearch(CourseRequest.SearchCriteria criteria, Pageable page) {
        log.trace("offset = {}, pageSize = {}", page.getOffset(), page.getPageSize());

        List<Course> courses = courseMapper.courseSearchBy(criteria, page);
        int count = courseMapper.countCoursesBy(criteria);

        return CourseConverter.toSimplePageResultDTO(courses, page, count);

    }

    /**
     * @param routeId
     * @return
     */
    @Override
    public RouteResponse.DetailResultDTO getRouteDetail(String routeId) {
        log.info("입력받은 길 번호 -> {}", routeId);
        Route route = getSafeRouteByRouteId(routeId);
        return RouteConverter.toDetailResultDTO(route);
    }

    @Override
    public CourseResponse.DetailResultDTO courseDetail(String courseId) {

        Course course = courseMapper
                .courseDetail(courseId)
                .orElseThrow(() -> new NoSuchElementException("해당 코스가 존재하지 않습니다."));

        return CourseConverter.toDetailResultDTO(course);
    }



    @Override
    public RouteResponse.DetailPageResultDTO routeList(Pageable page) {
        log.trace("offset = {}, pageSize = {}", page.getOffset(), page.getPageSize());

        List<Route> routes = courseMapper.routeList(page);
        int count = courseMapper.countRoutes();

        return RouteConverter.toDetailPageResultDTO(routes, page, count);


    }

    private Route getSafeRouteByRouteId(String routeId) {
        return courseMapper.getRouteByRouteId(routeId).orElseThrow(
                () -> new GeneralExceptionHandler(ErrorStatus.ROUTE_NOT_FOUND)
        );

    }


}
