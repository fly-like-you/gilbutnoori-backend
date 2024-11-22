package com.ssafy.gilbut.domain.plan.service;

import com.ssafy.gilbut.domain.plan.model.dto.request.PlanRequest;
import com.ssafy.gilbut.domain.plan.model.dto.response.PlanResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlanService {

    /**
     * 새로운 여행 계획을 생성합니다.
     * @param accessToken 사용자 인증 토큰
     * @param planListDTO 계획 생성 요청 정보
     */
    PlanResponse.DetailResultListDTO createPlans(String accessToken, List<PlanRequest.CreateDTO> planListDTO);


    /**
     * 특정 계획의 상세 정보를 조회합니다.
     * @param accessToken 사용자 인증 토큰
     * @param planId 조회할 계획 ID
     * @return 계획 상세 정보
     */
    PlanResponse.DetailResultDTO getPlan(String accessToken, Integer planId);

    /**
     * 사용자의 여행 계획 목록을 페이지네이션하여 조회합니다.
     * @param accessToken 사용자 인증 토큰
     * @param page 페이지네이션 정보
     * @return 계획 목록
     */
    PlanResponse.DetailResultPageDTO getPlanList(String accessToken, Pageable page);

    /**
     * 기존 계획을 수정합니다.
     * @param accessToken 사용자 인증 토큰
     * @param travelId 수정할 여행 ID
     * @param plans 수정 요청 정보
     * @return 수정된 계획 정보
     */
    PlanResponse.DetailResultListDTO updatePlan(String accessToken, Integer travelId, List<PlanRequest.CreateDTO> plans);

    /**
     * 계획을 삭제합니다.
     * @param accessToken 사용자 인증 토큰
     * @param travelId 삭제할 계획 ID
     */
    void deletePlanByTravelId(String accessToken, Integer travelId);
}
