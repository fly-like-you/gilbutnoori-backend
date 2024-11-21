package com.ssafy.gilbut.domain.plan.service;

import com.ssafy.gilbut.advice.status.ErrorStatus;
import com.ssafy.gilbut.domain.plan.mapper.PlanMapper;
import com.ssafy.gilbut.domain.plan.model.dto.request.PlanCreateRequestDTO;
import com.ssafy.gilbut.domain.plan.model.dto.request.PlanUpdateRequestDTO;
import com.ssafy.gilbut.domain.plan.model.dto.response.PlanResponseDTO;
import com.ssafy.gilbut.exception.handler.GeneralExceptionHandler;
import com.ssafy.gilbut.util.JWTUtil;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanServiceImpl implements PlanService {

    private final JWTUtil jwtUtil;
    private final PlanMapper planMapper;

    @Override
    public void createPlans(String accessToken, List<PlanCreateRequestDTO> plans) {
        Integer userId = jwtUtil.getUserId(accessToken);
        log.info("Creating plan for user: {}", userId);

        validatePlans(plans);
        planMapper.insertPlans(plans);
    }



    @Override
    public PlanResponseDTO getPlan(String accessToken, Integer planId) {
        Integer userId = jwtUtil.getUserId(accessToken);
        log.info("Retrieving plan {} for user: {}", planId, userId);

        Optional<PlanResponseDTO> plan = planMapper.findPlanById(planId);
        log.debug("Retrieved plan: {}", plan);

        return plan.orElseThrow(() -> new RuntimeException("Plan not found"));
    }

    @Override
    public Page<PlanResponseDTO> getPlanList(String accessToken, Pageable page) {
        Integer userId = jwtUtil.getUserId(accessToken);
        log.info("Retrieving plan list for user: {}, offset: {}, pageSize: {}",
                userId, page.getOffset(), page.getPageSize());

        List<PlanResponseDTO> contents = planMapper.findPlansByUserId(userId, page);
        int count = planMapper.countPlansByUserId(userId);

        log.debug("Retrieved {} plans", contents.size());
        return new PageImpl<>(contents, page, count);
    }

    @Override
    public PlanResponseDTO updatePlan(String accessToken, Integer planId, List<PlanUpdateRequestDTO> plans) {
        Integer userId = jwtUtil.getUserId(accessToken);
        log.info("Updating plan {} for user: {}", planId, userId);

        planMapper.updatePlan(planId, requestDTO);
        Optional<PlanResponseDTO> result = planMapper.findPlanById(planId);
        log.debug("Updated plan: {}", result);

        return result.orElseThrow(() -> new RuntimeException("Plan update failed"));
    }

    @Override
    public void deletePlan(String accessToken, Integer planId) {
        Integer userId = jwtUtil.getUserId(accessToken);
        log.info("Deleting plan {} for user: {}", planId, userId);

        planMapper.deletePlan(planId);
        log.debug("Plan deleted successfully");
    }

    private static void validatePlans(List<PlanCreateRequestDTO> plans) {
        // 상호 베타 검증
        if (!plans.stream().allMatch((plan) -> (plan.getAttractionId() == null) ^ (plan.getCourseId() == null))) {
            throw new GeneralExceptionHandler(ErrorStatus.PLAN_ATTRACTION_DUPLICATED_ERROR);
        }
        // order 중복 검증
        if (plans.stream()
                .map(PlanCreateRequestDTO::getOrder)
                .distinct()
                .count() != plans.size()) {
            throw new GeneralExceptionHandler(ErrorStatus.PLAN_ORDER_DUPLICATED_ERROR);
        }
    }
}
