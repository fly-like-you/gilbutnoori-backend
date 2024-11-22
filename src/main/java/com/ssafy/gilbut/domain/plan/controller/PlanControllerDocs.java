package com.ssafy.gilbut.domain.plan.controller;

import com.ssafy.gilbut.domain.plan.model.dto.request.PlanRequest;
import com.ssafy.gilbut.domain.plan.model.dto.response.PlanResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Plan", description = "여행 계획 관련 API")
public interface PlanControllerDocs {

    @Operation(summary = "특정 계획 조회", description = "계획 ID를 이용하여 특정 여행 계획의 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "계획 조회 성공",
                    content = @Content(
                            schema = @Schema(implementation = PlanResponse.DetailResultDTO.class))
            ),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
            @ApiResponse(responseCode = "404", description = "계획을 찾을 수 없음")
    })
    ResponseEntity<?> getPlan(
            @Parameter(description = "사용자 인증 토큰", required = true) String accessToken,
            @Parameter(description = "조회할 계획 ID", required = true) Integer planId
    );

    @Operation(summary = "계획 목록 조회", description = "페이지네이션을 적용하여 여행 계획 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "계획 목록 조회 성공",
                    content = @Content(schema = @Schema(implementation = PlanResponse.DetailResultPageDTO.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자")
    })
    ResponseEntity<?> getPlanList(
            @Parameter(description = "사용자 인증 토큰", required = true) String accessToken,
            Pageable page
    );

    @Operation(summary = "새로운 계획 생성", description = "새로운 여행 계획을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "계획 생성 성공",
                    content = @Content(schema = @Schema(implementation = PlanResponse.DetailResultListDTO.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    ResponseEntity<?> createPlan(
            @Parameter(description = "사용자 인증 토큰", required = true) String accessToken,
            @Parameter(description = "생성할 계획 정보 리스트", required = true) List<PlanRequest.CreateDTO> planDTOList
    );

    @Operation(summary = "계획 수정", description = "여행 ID에 해당하는 계획을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "계획 수정 성공",
                    content = @Content(schema = @Schema(implementation = PlanResponse.DetailResultListDTO.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
            @ApiResponse(responseCode = "404", description = "여행을 찾을 수 없음")
    })
    ResponseEntity<?> updatePlan(
            @Parameter(description = "사용자 인증 토큰", required = true) String accessToken,
            @Parameter(description = "수정할 여행 ID", required = true) Integer travelId,
            @Parameter(description = "수정할 계획 정보 리스트", required = true) List<PlanRequest.CreateDTO> plans
    );

    @Operation(summary = "계획 삭제", description = "여행 ID에 해당하는 모든 계획을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "계획 삭제 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
            @ApiResponse(responseCode = "404", description = "여행을 찾을 수 없음")
    })
    ResponseEntity<?> deletePlan(
            @Parameter(description = "사용자 인증 토큰", required = true) String accessToken,
            @Parameter(description = "삭제할 여행 ID", required = true) Integer travelId
    );
}