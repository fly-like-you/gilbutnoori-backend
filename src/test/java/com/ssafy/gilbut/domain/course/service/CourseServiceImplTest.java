package com.ssafy.gilbut.domain.course.service;

import com.ssafy.gilbut.domain.course.mapper.CourseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseServiceImplTest {

    @Autowired
    private CourseServiceImpl courseService;
    @Autowired
    private CourseMapper courseMapper;

    @Test
    void courseList() {
    }

    @Test
    void courseDetail() {

    }
}