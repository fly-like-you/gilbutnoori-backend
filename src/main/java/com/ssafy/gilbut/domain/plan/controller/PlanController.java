package com.ssafy.gilbut.domain.plan.controller;

import com.ssafy.gilbut.advice.ApiResponse;
import com.ssafy.gilbut.domain.plan.model.dto.request.PlanCreateRequestDTO;
import com.ssafy.gilbut.domain.plan.model.dto.request.PlanUpdateRequestDTO;
import com.ssafy.gilbut.domain.plan.model.dto.response.PlanResponseDTO;
import com.ssafy.gilbut.domain.plan.service.PlanService;
import com.ssafy.gilbut.util.SizeConstant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping
    public ResponseEntity<?> createPlan(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody List<PlanCreateRequestDTO> plans
    ) {
        planService.createPlans(accessToken, plans);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{planId}")
    public ResponseEntity<?> getPlan(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable Integer planId
    ) {
        PlanResponseDTO result = planService.getPlan(accessToken, planId);
        log.info("Retrieved plan: {}", result);

        return ResponseEntity.ok().body(ApiResponse.onSuccess(result));
    }

    @GetMapping
    public ResponseEntity<?> getPlanList(
            @RequestHeader("Authorization") String accessToken,
            @PageableDefault(size = SizeConstant.LIST_SIZE) Pageable page
    ) {
        Page<PlanResponseDTO> result = planService.getPlanList(accessToken, page);
        log.info("Retrieved plan list: {}", result);

        return ResponseEntity.ok().body(ApiResponse.onSuccess(result));
    }

    @PutMapping("/{planId}")
    public ResponseEntity<?> updatePlan(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable Integer planId,
            @RequestBody PlanUpdateRequestDTO requestDTO
    ) {
        PlanResponseDTO result = planService.updatePlan(accessToken, planId, requestDTO);
        log.info("Updated plan: {}", result);

        return ResponseEntity.ok().body(ApiResponse.onSuccess(result));
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<?> deletePlan(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable Integer planId
    ) {
        planService.deletePlan(accessToken, planId);
        log.info("Deleted plan with id: {}", planId);

        return ResponseEntity.ok().body(ApiResponse.onSuccess(null));
    }
}
