package com.ssafy.gilbut.domain.plan.controller;

import com.ssafy.gilbut.advice.ApiResponse;
import com.ssafy.gilbut.domain.plan.model.dto.request.PlanRequest;
import com.ssafy.gilbut.domain.plan.model.dto.response.PlanResponse;
import com.ssafy.gilbut.domain.plan.service.PlanService;
import com.ssafy.gilbut.util.SizeConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
@Tag(name = "Plan", description = "계획 관련 API")
public class PlanController {

    private final PlanService planService;

    @GetMapping("/{planId}")
    public ResponseEntity<?> getPlan(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable Integer planId
    ) {
        PlanResponse.DetailResultDTO plan = planService.getPlan(accessToken, planId);

        log.info("Retrieved plan: {}", plan);

        return ResponseEntity.ok().body(ApiResponse.onSuccess(plan));
    }

    @GetMapping
    public ResponseEntity<?> getPlanList(
            @RequestHeader("Authorization") String accessToken,
            @PageableDefault(size = SizeConstant.LIST_SIZE) Pageable page
    ) {
        PlanResponse.DetailResultPageDTO planList = planService.getPlanList(accessToken, page);
        log.info("Retrieved plan list: {}", planList);

        return ResponseEntity.ok().body(ApiResponse.onSuccess(planList));
    }

    @Operation(summary = "계획 생성", description = "새로운 계획을 생성합니다.")
    @PostMapping
    public ResponseEntity<?> createPlan(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody List<PlanRequest.CreateDTO> planDTOList
    ) {
        PlanResponse.DetailResultListDTO planResultDTO = planService.createPlans(accessToken, planDTOList);
        log.info(planResultDTO.toString());
        return ResponseEntity.ok(ApiResponse.onSuccess(planResultDTO));
    }

    @PutMapping("/{travelId}")
    public ResponseEntity<?> updatePlan(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable("travelId") Integer travelId,
            @RequestBody List<PlanRequest.CreateDTO> plans
    ) {
        PlanResponse.DetailResultListDTO result = planService.updatePlan(accessToken, travelId, plans);
        log.info("Updated plan: {}", result);

        return ResponseEntity.ok().body(ApiResponse.onSuccess(result));
    }

    @DeleteMapping("/{travelId}")
    public ResponseEntity<?> deletePlan(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable Integer travelId
    ) {
        planService.deletePlanByTravelId(accessToken, travelId);
        log.info("Deleted plan with id: {}", travelId);

        return ResponseEntity.ok().body(ApiResponse.onSuccess(null));
    }
}
