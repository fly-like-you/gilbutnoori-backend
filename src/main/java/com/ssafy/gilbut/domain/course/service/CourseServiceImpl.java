package com.ssafy.gilbut.domain.course.service;

import com.ssafy.gilbut.domain.course.mapper.CourseMapper;
import com.ssafy.gilbut.domain.course.model.dto.CourseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{
    private final CourseMapper courseMapper;
    @Override
    public Page<CourseDTO> courseList(Pageable page) {
        log.trace("offset = {}, pageSize = {}", page.getOffset(), page.getPageSize());

        List<CourseDTO> contents = courseMapper.courseList(page.getOffset(), page.getPageSize());
        int count = courseMapper.countCourses();

        return new PageImpl<>(contents,page,count);
    }

    @Override
    public CourseDTO courseDetail(String courseId) {

        return courseMapper
                .courseDetail(courseId)
                .orElseThrow(() -> new NoSuchElementException("해당 코스가 존재하지 않습니다."));
    }


}
