package com.ssafy.gilbut.domain.plan.mapper;

import com.ssafy.gilbut.domain.plan.model.dto.PlanRequest;
import com.ssafy.gilbut.domain.plan.model.entity.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PlanMapper {

    void insertPlans(@Param("dto") List<PlanRequest.CreateDTO> dto);

    Optional<Plan> findPlanById(@Param("planId") Long planId);

    List<Plan> findPlansByUserId(
            @Param("userId") Long userId,
            @Param("pageable") Pageable pageable
    );

    List<Plan> findPlansByPlanIds(
            @Param("ids") List<Long> ids
    );

    int countPlansByUserId(@Param("userId") Long userId);

    void updatePlans(
            @Param("planId") Long planId,
            @Param("dto") List<PlanRequest.UpdateDTO> dto
    );


    void deletePlanByTravelId(@Param("travelId") Long travelId);
}
