package com.ssafy.gilbut.domain.course.service;

import com.ssafy.gilbut.domain.course.mapper.CourseMapper;
import com.ssafy.gilbut.domain.course.model.dto.CourseDTO;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{
    private final CourseMapper courseMapper;
    @Override
    public Page<Map<String, Object>> courseList(Map<String, Object> paramMap, Pageable page) {
        paramMap.put("offset", page.getOffset());
        paramMap.put("pageSize",page.getPageSize());

        log.trace("offset = {}, pageSize = {}", page.getOffset(), page.getPageSize());

        List<Map<String, Object>> contents = courseMapper.courseList(paramMap);
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
