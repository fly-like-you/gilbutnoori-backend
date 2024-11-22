package com.ssafy.gilbut.domain.plan.mapper;

import com.ssafy.gilbut.domain.plan.model.dto.request.PlanRequest;
import com.ssafy.gilbut.domain.plan.model.entity.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PlanMapper {

    void insertPlans(@Param("dto") List<PlanRequest.CreateDTO> dto);

    Optional<Plan> findPlanById(@Param("planId") Integer planId);

    List<Plan> findPlansByUserId(
            @Param("userId") Integer userId,
            @Param("pageable") Pageable pageable
    );

    List<Plan> findPlansByPlanIds(
            @Param("ids") List<Integer> ids
    );

    int countPlansByUserId(@Param("userId") Integer userId);

    void updatePlans(
            @Param("planId") Integer planId,
            @Param("dto") List<PlanRequest.UpdateDTO> dto
    );


    void deletePlanByTravelId(@Param("travelId") Integer travelId);
}
