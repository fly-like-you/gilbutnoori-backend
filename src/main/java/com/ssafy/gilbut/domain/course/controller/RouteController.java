package com.ssafy.gilbut.domain.course.controller;

import com.ssafy.gilbut.advice.ApiResponse;
import com.ssafy.gilbut.domain.course.model.dto.RouteResponse;
import com.ssafy.gilbut.domain.course.service.CourseService;
import com.ssafy.gilbut.util.SizeConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/routes")
public class RouteController implements RouteControllerDocs {
    private final CourseService courseService;

    @Override
    @GetMapping
    public ResponseEntity<?> routeList(
            @PageableDefault(size = SizeConstant.LIST_SIZE) Pageable page
    ) {
        RouteResponse.DetailPageResultDTO result = courseService.routeList(page);
        return ResponseEntity.ok(ApiResponse.onSuccess(result));
    }
}
