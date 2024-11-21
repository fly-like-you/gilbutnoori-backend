package com.ssafy.gilbut.domain.plan.service;

import com.ssafy.gilbut.domain.plan.mapper.PlanMapper;
import com.ssafy.gilbut.domain.plan.model.dto.request.PlanCreateRequestDTO;
import com.ssafy.gilbut.domain.plan.model.dto.request.PlanUpdateRequestDTO;
import com.ssafy.gilbut.domain.plan.model.dto.response.PlanResponseDTO;
import com.ssafy.gilbut.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanServiceImpl implements PlanService {

    private final JWTUtil jwtUtil;
    private final PlanMapper planMapper;

    @Override
    public PlanResponseDTO createPlan(String accessToken, PlanCreateRequestDTO requestDTO) {
        Integer userId = jwtUtil.getUserId(accessToken);
        log.info("Creating plan for user: {}", userId);

        planMapper.insertPlan(requestDTO);
        Optional<PlanResponseDTO> result = planMapper.findPlanById(requestDTO.getId());
        log.debug("Created plan: {}", result);

        return result.orElseThrow(() -> new RuntimeException("Plan creation failed"));
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
    public PlanResponseDTO updatePlan(String accessToken, Integer planId, PlanUpdateRequestDTO requestDTO) {
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
}
