package com.ssafy.gilbut.domain.course.controller;

import com.ssafy.gilbut.domain.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/routes")
public class RouteController implements RouteControllerDocs{
    private final CourseService courseService;
    @Override
    @GetMapping("/routes")
    public ResponseEntity<?> routeList() {
        // TODO: 길 정보를 모두 가져오는 메서드
        return null;
    }
}
