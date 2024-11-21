package com.ssafy.gilbut.domain.course.controller;

import com.ssafy.gilbut.advice.ApiResponse;
import com.ssafy.gilbut.advice.status.ErrorStatus;
import com.ssafy.gilbut.domain.course.model.dto.CourseDetailResponseDTO;
import com.ssafy.gilbut.domain.course.model.dto.CourseSearchCriteria;
import com.ssafy.gilbut.domain.course.service.CourseService;
import com.ssafy.gilbut.exception.handler.GeneralExceptionHandler;
import com.ssafy.gilbut.util.SizeConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController implements CourseControllerDocs {
    private final CourseService courseService;
    @Override
    @GetMapping("/search")
    public ResponseEntity<?> courseSearch(
            @ModelAttribute CourseSearchCriteria criteria,
            @PageableDefault(size = SizeConstant.LIST_SIZE) Pageable page
    ) {
        log.trace("Search criteria: {}", criteria);
        // TODO: 코스의 테마에 맞게 추천
        Page<CourseDetailResponseDTO> result = courseService.courseSearch(criteria, page);
        log.trace("result={}", result);

        return ResponseEntity.ok().body(ApiResponse.onSuccess(result));

    }

    @Override
    @GetMapping
    public ResponseEntity<?> courseList(
            @RequestParam Map<String, Object> paramMap,
            @PageableDefault(size = SizeConstant.LIST_SIZE) Pageable page
    ) {
        Page<CourseDetailResponseDTO> result =  courseService.courseList(page);
        log.trace("result={}", result);

        return ResponseEntity.ok().body(ApiResponse.onSuccess(result));
    }

    @Override
    @GetMapping("/{courseId}")
    public ResponseEntity<?> courseDetail(@PathVariable String courseId) {
        CourseDetailResponseDTO courseDetailResponseDTO = courseService.courseDetail(courseId);

        return ResponseEntity.ok(ApiResponse.onSuccess(courseDetailResponseDTO));
    }

    @Override
    @PostMapping("/map/{courseId}")
    public ResponseEntity<?> courseMapData(@PathVariable String courseId) throws IOException {
        // 파일 경로 설정: resources/static/courses/courseId.gpx
        Path filePath;
        try {
            filePath = Paths.get(new ClassPathResource("static/courses/" + courseId + ".gpx").getURI());
        } catch (IOException e) {
            throw new GeneralExceptionHandler(ErrorStatus.COURSE_NOT_FOUND);
        }

        // 파일 읽기
        String fileContent = Files.readString(filePath);

        // 파일 내용을 응답으로 반환
        return ResponseEntity.ok(ApiResponse.onSuccess(fileContent));
    }

}
