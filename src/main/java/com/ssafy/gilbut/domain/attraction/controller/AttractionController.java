package com.ssafy.gilbut.domain.attraction.controller;

import com.ssafy.gilbut.advice.ApiResponse;
import com.ssafy.gilbut.domain.attraction.model.dto.AttractionDTO;
import com.ssafy.gilbut.domain.attraction.service.AttractionService;
import com.ssafy.gilbut.util.SizeConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/attractions")
public class AttractionController implements AttractionControllerDocs {

    private final AttractionService attractionService;

    // TODO: courseId를 기반으로 주변의 관광지 정보를 불러오기, 관광지 API를 사용할 수고 있고 직접 코드를 짤 수도 있음
    @Override
    @GetMapping("/courses/{courseId}/travelPoint")
    public ResponseEntity<?> travelingPointList(
            @PathVariable String courseId,
            @PageableDefault(size = SizeConstant.LIST_SIZE) Pageable page
    ) {

        Page<AttractionDTO> result = attractionService.searchTravelingPointByCourseId(courseId, page);
        log.trace("result={}", result);


        return ResponseEntity.ok(ApiResponse.onSuccess(result));
    }
}
