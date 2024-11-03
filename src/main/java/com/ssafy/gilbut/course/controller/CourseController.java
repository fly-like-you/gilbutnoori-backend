package com.ssafy.gilbut.course.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController implements CourseControllerDocs {
    @Override
    @GetMapping("/search")
    public ResponseEntity<?> courseSearch(Map<String, String> map) {
        return null;
    }

    @Override
    @GetMapping
    public ResponseEntity<?> courseList(@RequestParam Map<String, String> map) {
        return null;
    }

    @Override
    @GetMapping("/{courseId}")
    public ResponseEntity<?> courseDetail(@PathVariable int courseId, @RequestParam Map<String, String> map) {
        return null;
    }

    @Override
    @PostMapping("/{courseId}")
    public ResponseEntity<?> courseMapData(@PathVariable int courseId) {
        return null;
    }

    // TODO: courseId를 기반으로 주변의 관광지 정보를 불러오기, 관광지 API를 사용할 수고 있고 직접 코드를 짤 수도 있음
    @Override
    @GetMapping("/travelPoint/{courseId}")
    public ResponseEntity<?> travelingPointList(int courseId) {
        return null;
    }


}
