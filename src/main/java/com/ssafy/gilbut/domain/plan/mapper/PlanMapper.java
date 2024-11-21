package com.ssafy.gilbut.domain.plan.mapper;

import com.ssafy.gilbut.domain.plan.model.dto.request.PlanCreateRequestDTO;
import com.ssafy.gilbut.domain.plan.model.dto.request.PlanUpdateRequestDTO;
import com.ssafy.gilbut.domain.plan.model.dto.response.PlanResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PlanMapper {

    void insertPlans(@Param("dto") List<PlanCreateRequestDTO> dto);

    Optional<PlanResponseDTO> findPlanById(@Param("planId") Integer planId);

    List<PlanResponseDTO> findPlansByUserId(
            @Param("userId") Integer userId,
            @Param("page") Pageable page
    );

    int countPlansByUserId(@Param("userId") Integer userId);

    void updatePlan(
            @Param("planId") Integer planId,
            @Param("dto") PlanUpdateRequestDTO dto
    );

    void deletePlan(@Param("planId") Integer planId);
}
