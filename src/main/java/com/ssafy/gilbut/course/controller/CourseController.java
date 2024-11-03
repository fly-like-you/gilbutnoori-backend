package com.ssafy.gilbut.course.controller;

import com.ssafy.gilbut.course.model.dto.CourseDTO;
import com.ssafy.gilbut.course.service.CourseService;
import com.ssafy.gilbut.util.SizeConstant;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController implements CourseControllerDocs {
    private final CourseService courseService;
    @Override
    @GetMapping("/search")
    public ResponseEntity<?> courseSearch(Map<String, String> map) {

        return null;
    }

    @Override
    @GetMapping
    public ResponseEntity<?> courseList(
            @RequestParam Map<String, Object> paramMap,
            @PageableDefault(size = SizeConstant.LIST_SIZE) Pageable page

    ) {

        HashMap<String, Object> resultMap = new HashMap<>();

        Page<Map<String, Object>> result =  courseService.courseList(paramMap, page);
        resultMap.put("pages", result);
        resultMap.put("size", page.getPageSize());
        log.trace("result={}, size={}", result, page.getPageSize());

        return ResponseEntity.ok().body(resultMap);

    }

    @Override
    @GetMapping("/{courseId}")
    public ResponseEntity<?> courseDetail(@PathVariable String courseId) {
        CourseDTO courseDTO = courseService.courseDetail(courseId);

        return ResponseEntity.ok(courseDTO);
    }

    @Override
    @PostMapping("/map/{courseId}")
    public ResponseEntity<?> courseMapData(@PathVariable String courseId) throws IOException {
        // 파일 경로 설정: resources/static/courses/courseId.gpx
        Path filePath = Paths.get(new ClassPathResource("static/courses/" + courseId + ".gpx").getURI());

        // 파일 읽기
        String fileContent = Files.readString(filePath);

        // 파일 내용을 응답으로 반환
        return new ResponseEntity<>(fileContent, HttpStatus.OK);
    }

    // TODO: courseId를 기반으로 주변의 관광지 정보를 불러오기, 관광지 API를 사용할 수고 있고 직접 코드를 짤 수도 있음
    @Override
    @GetMapping("/travelPoint/{courseId}")
    public ResponseEntity<?> travelingPointList(String courseId) {
        return null;
    }


}
