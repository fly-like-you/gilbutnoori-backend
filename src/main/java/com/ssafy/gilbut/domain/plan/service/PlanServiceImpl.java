package com.ssafy.gilbut.domain.plan.service;

import com.ssafy.gilbut.advice.status.ErrorStatus;
import com.ssafy.gilbut.domain.plan.converter.PlanConverter;
import com.ssafy.gilbut.domain.plan.mapper.PlanMapper;
import com.ssafy.gilbut.domain.plan.model.dto.PlanRequest;
import com.ssafy.gilbut.domain.plan.model.dto.PlanResponse;
import com.ssafy.gilbut.domain.plan.model.dto.PlanResponse.DetailResultDTO;
import com.ssafy.gilbut.domain.plan.model.entity.Plan;
import com.ssafy.gilbut.exception.handler.GeneralExceptionHandler;
import com.ssafy.gilbut.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanServiceImpl implements PlanService {

    private final JWTUtil jwtUtil;
    private final PlanMapper planMapper;

    @Override
    public PlanResponse.DetailResultListDTO createPlans(String accessToken, List<PlanRequest.CreateDTO> planListDTO) {
        Long userId = jwtUtil.getUserId(accessToken);
        log.info("Creating plan for user: {}", userId);

        validatePlans(planListDTO);
        planMapper.insertPlans(planListDTO);

        List<Long> ids = planListDTO.stream().map(PlanRequest.CreateDTO::getId).toList();
        List<Plan> plans = planMapper.findPlansByPlanIds(ids);

        return PlanConverter.toDetailResultListDTO(plans);
    }

    @Override
    public DetailResultDTO getPlan(String accessToken, Long planId) {
        Long userId = jwtUtil.getUserId(accessToken);

        Plan plan = planMapper.findPlanById(planId).orElseThrow();
        log.info("Retrieved plan {} for user: {}", plan, userId);

        return PlanConverter.toDetailResultDTO(plan);
    }

    @Override
    public PlanResponse.DetailResultPageDTO getPlanList(String accessToken, Pageable page) {
        Long userId = jwtUtil.getUserId(accessToken);
        log.info("Retrieving plan list for user: {}, offset: {}, pageSize: {}",
                userId, page.getOffset(), page.getPageSize());

        List<Plan> contents = planMapper.findPlansByUserId(userId, page);
        int count = planMapper.countPlansByUserId(userId);

        return PlanConverter.toDetailResultPageDTO(contents, page, count);
    }

    /**
     * Plan 단건 수정은 의미가 거의 없기 때문에 리스트를 받아서 모두 수정
     * @param accessToken 사용자 인증 토큰
     * @param travelId      수정할 계획 ID
     * @param plans  수정 요청 정보
     * @return
     */

    @Override
    public PlanResponse.DetailResultListDTO updatePlan(String accessToken, Long travelId, List<PlanRequest.CreateDTO> plans) {
        Long userId = jwtUtil.getUserId(accessToken);
        log.info("Updating plan {} for user: {}", travelId, userId);

        // plan 모두 비우기
        planMapper.deletePlanByTravelId(travelId);

        // plan 새로 생성하기
        return createPlans(accessToken, plans);
    }

    @Override
    public void deletePlanByTravelId(String accessToken, Long travelId) {
        Long userId = jwtUtil.getUserId(accessToken);
        log.info("Deleting travelId {} for user: {}", travelId, userId);

        planMapper.deletePlanByTravelId(travelId);
        log.debug("Plan deleted successfully");
    }

    private static void validatePlans(List<PlanRequest.CreateDTO> plans) {
        // 상호 베타 검증
        if (!plans.stream().allMatch((plan) -> (plan.getAttractionId() == null) ^ (plan.getCourseId() == null))) {
            throw new GeneralExceptionHandler(ErrorStatus.PLAN_ATTRACTION_DUPLICATED_ERROR);
        }

        // order 중복 검증
        if (plans.stream()
                .map(PlanRequest.CreateDTO::getSequence)
                .distinct()
                .count() != plans.size()) {
            throw new GeneralExceptionHandler(ErrorStatus.PLAN_ORDER_DUPLICATED_ERROR);
        }

        // course 개수 검증 (무조건 1개)
        if (plans.stream()
                .map(PlanRequest.CreateDTO::getCourseId)
                .filter(Objects::nonNull) // null 값 제외
                .count() != 1)
            throw new GeneralExceptionHandler(ErrorStatus.PLAN_COURSE_NOT_ONE_ERROR);

    }
}
