package com.ssafy.gilbut.domain.course.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name="코스 정보 컨트롤러", description = "코스 정보 상세보기, 리스트 보기 등의 처리를 하는 클래스")
public interface RouteControllerDocs {
    @Operation(
            summary="해파랑길, 남파랑길 등의 길 정보 가져오기"
    )
    ResponseEntity<?> routeList();
}
