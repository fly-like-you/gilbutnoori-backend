package com.ssafy.gilbut.domain.plan.service;

import com.ssafy.gilbut.domain.plan.model.dto.request.PlanCreateRequestDTO;
import com.ssafy.gilbut.domain.plan.model.dto.request.PlanUpdateRequestDTO;
import com.ssafy.gilbut.domain.plan.model.dto.response.PlanResponseDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PlanService {

    /**
     * 새로운 여행 계획을 생성합니다.
     * @param accessToken 사용자 인증 토큰
     * @param plans 계획 생성 요청 정보
     */
    void createPlans(String accessToken, List<PlanCreateRequestDTO> plans);

    /**
     * 특정 계획의 상세 정보를 조회합니다.
     * @param accessToken 사용자 인증 토큰
     * @param planId 조회할 계획 ID
     * @return 계획 상세 정보
     */
    PlanResponseDTO getPlan(String accessToken, Integer planId);

    /**
     * 사용자의 여행 계획 목록을 페이지네이션하여 조회합니다.
     * @param accessToken 사용자 인증 토큰
     * @param page 페이지네이션 정보
     * @return 계획 목록
     */
    Page<PlanResponseDTO> getPlanList(String accessToken, Pageable page);

    /**
     * 기존 계획을 수정합니다.
     * @param accessToken 사용자 인증 토큰
     * @param planId 수정할 계획 ID
     * @param requestDTO 수정 요청 정보
     * @return 수정된 계획 정보
     */
    PlanResponseDTO updatePlan(String accessToken, Integer planId, PlanUpdateRequestDTO requestDTO);

    /**
     * 계획을 삭제합니다.
     * @param accessToken 사용자 인증 토큰
     * @param planId 삭제할 계획 ID
     */
    void deletePlan(String accessToken, Integer planId);
}
