package com.ssafy.gilbut.domain.course.controller;

import com.ssafy.gilbut.advice.ApiResponse;
import com.ssafy.gilbut.advice.status.ErrorStatus;
import com.ssafy.gilbut.domain.course.model.dto.CourseDTO;
import com.ssafy.gilbut.domain.course.service.CourseService;
import com.ssafy.gilbut.exception.handler.TempHandler;
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
    public ResponseEntity<?> courseSearch(Map<String, String> map) {
        // TODO: 코스의 테마에 맞게 추천
        return null;
    }

    @Override
    @GetMapping
    public ResponseEntity<?> courseList(
            @RequestParam Map<String, Object> paramMap,
            @PageableDefault(size = SizeConstant.LIST_SIZE) Pageable page
    ) {
        Page<CourseDTO> result =  courseService.courseList(page);
        log.trace("result={}", result);

        return ResponseEntity.ok().body(ApiResponse.onSuccess(result));
    }

    @Override
    @GetMapping("/{courseId}")
    public ResponseEntity<?> courseDetail(@PathVariable String courseId) {
        CourseDTO courseDTO = courseService.courseDetail(courseId);

        return ResponseEntity.ok(ApiResponse.onSuccess(courseDTO));
    }

    @Override
    @PostMapping("/map/{courseId}")
    public ResponseEntity<?> courseMapData(@PathVariable String courseId) throws IOException {
        // 파일 경로 설정: resources/static/courses/courseId.gpx
        Path filePath;
        try {
            filePath = Paths.get(new ClassPathResource("static/courses/" + courseId + ".gpx").getURI());
        } catch (IOException e) {
            throw new TempHandler(ErrorStatus._INTERNAL_SERVER_ERROR);
        }

        // 파일 읽기
        String fileContent = Files.readString(filePath);

        // 파일 내용을 응답으로 반환
        return ResponseEntity.ok(ApiResponse.onSuccess(fileContent));
    }

    // TODO: courseId를 기반으로 주변의 관광지 정보를 불러오기, 관광지 API를 사용할 수고 있고 직접 코드를 짤 수도 있음
    @Override
    @GetMapping("/travelPoint/{courseId}")
    public ResponseEntity<?> travelingPointList(String courseId) {
        // https://apis.data.go.kr/B551011/KorService1/areaBasedList1 요청
        return null;
    }




}
