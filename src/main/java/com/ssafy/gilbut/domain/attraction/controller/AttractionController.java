package com.ssafy.gilbut.domain.attraction.controller;

import com.ssafy.gilbut.advice.ApiResponse;
import com.ssafy.gilbut.domain.attraction.model.dto.AttractionResponse;
import com.ssafy.gilbut.domain.attraction.service.AttractionService;
import com.ssafy.gilbut.util.SizeConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/attractions")
public class AttractionController implements AttractionControllerDocs {

    private final AttractionService attractionService;

    // 둘렛길 근처 관광지 선택
    @Override
    @GetMapping("/courses/{courseId}/travelPoint")
    public ResponseEntity<?> travelingPointList(
            @PathVariable String courseId,
            @RequestParam Integer contentTypeId,
            @PageableDefault(size = SizeConstant.LIST_SIZE) Pageable page
    ) {

        AttractionResponse.DetailPageResultDTO result = attractionService.searchTravelingPointByCourseId(courseId, contentTypeId, page);
        log.trace("result size -> {}", result.getListSize());


        return ResponseEntity.ok(ApiResponse.onSuccess(result));
    }
}
