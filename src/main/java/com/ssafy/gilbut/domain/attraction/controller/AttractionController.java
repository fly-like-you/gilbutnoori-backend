package com.ssafy.gilbut.domain.attraction.controller;

import com.ssafy.gilbut.advice.ApiResponse;
import com.ssafy.gilbut.domain.attraction.service.AttractionService;
import com.ssafy.gilbut.util.SizeConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attractions")
public class AttractionController implements AttractionControllerDocs {

    private final AttractionService attractionService;

    // TODO: courseId를 기반으로 주변의 관광지 정보를 불러오기, 관광지 API를 사용할 수고 있고 직접 코드를 짤 수도 있음
    @Override
    @GetMapping("/courses/{courseId}/travelPoint")
    public ResponseEntity<?> travelingPointList(
            String courseId,
            @PageableDefault(size = SizeConstant.LIST_SIZE) Pageable page
    ) {


        return ResponseEntity.ok(ApiResponse.onSuccess(""));
    }
}
