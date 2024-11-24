package com.ssafy.gilbut.domain.plan.converter;

import static com.ssafy.gilbut.domain.plan.model.dto.PlanResponse.DetailResultDTO;

import com.ssafy.gilbut.domain.attraction.converter.AttractionConverter;
import com.ssafy.gilbut.domain.course.converter.CourseConverter;
import com.ssafy.gilbut.domain.plan.model.dto.PlanResponse;
import com.ssafy.gilbut.domain.plan.model.entity.Plan;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PlanConverter {

    /**
     * 여행 계획 세부 정보 리스트 DTO를 페이징으로 변환 해주는 메서드
     * @param plan
     * @param pageable
     * @param totalCount
     * @return
     */
    public static PlanResponse.DetailResultPageDTO toDetailResultPageDTO(List<Plan> plan, Pageable pageable, Integer totalCount) {
        List<DetailResultDTO> list = plan.stream().map(PlanConverter::toDetailResultDTO).toList();
        PageImpl<DetailResultDTO> page = new PageImpl<>(list, pageable, totalCount);

        return PlanResponse.DetailResultPageDTO.builder()
                .planResult(list)
                .listSize(list.size())
                .isFirstPage(page.isFirst())
                .isLastPage(page.isLast())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }

    /**
     * 세부 정보 결과 DTO로 변환해주는 메서드
     * @param plan
     * @return
     */
    public static DetailResultDTO toDetailResultDTO(Plan plan) {
        return DetailResultDTO.builder()
                .id(plan.getId())
                .attraction(Optional.ofNullable(plan.getAttraction())
                        .map(AttractionConverter::toDetailResultDTO)
                        .orElse(null))
                .course(Optional.ofNullable(plan.getCourse())
                        .map(CourseConverter::toDetailResultDTO)
                        .orElse(null))
                .travelId(null)
                .sequence(plan.getSequence())
                .build();
    }

    public static PlanResponse.DetailResultListDTO toDetailResultListDTO(List<Plan> plans) {
        List<DetailResultDTO> list = plans.stream().map(PlanConverter::toDetailResultDTO).toList();

        return PlanResponse.DetailResultListDTO.builder()
                .planResult(list)
                .build();
    }
}
