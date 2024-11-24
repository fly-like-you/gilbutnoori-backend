package com.ssafy.gilbut.domain.travel.controller;

import com.ssafy.gilbut.domain.travel.model.dto.TravelRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@Tag(name = "여행 계획 컨트롤러", description = "여행 계획 CRUD")
public interface TravelControllerDocs {
    // 여행 계획
    @Operation(summary = "여행 계획 생성하기")
    ResponseEntity<?> createTravel(
            @Parameter(description = "유저 토큰", required = true) String accessToken,
            @Parameter(description = "여행 생성 DTO", required = true) TravelRequest.CreateDTO createDTO
    );

    @Operation(summary = "사용자의 여행 계획 리스트 불러오기")
    ResponseEntity<?> getTravelListByUserId(
            @Parameter(description = "유저 토큰", required = true) String accessToken,
            Pageable pageable
    );

    @Operation(summary = "여행 ID로 여행 계획 가져오기")
    ResponseEntity<?> getTravelById(
            @Parameter(description = "유저 토큰", required = true) String accessToken,
            @Parameter(description = "여행 ID", required = true) Long travelId
    );

    @Operation(summary = "여행 ID로 여행 계획 삭제하기")
    ResponseEntity<?> deleteTravelById(
            @Parameter(description = "유저 토큰", required = true) String accessToken,
            @Parameter(description = "여행 ID", required = true) Long travelId
    );

    @Operation(
            summary = "여행 ID로 여행 계획 수정하기",
            description = "수정할 여행 계획 DTO도 포함"
    )
    ResponseEntity<?> updateTravelById(
            @Parameter(description = "유저 토큰", required = true) String accessToken,
            @Parameter(description = "수정할 여행 ID", required = true) Long travelId,
            @Parameter(description = "수정할 여행 DTO", required = true) TravelRequest.UpdateDTO updateDTO
    );

}

