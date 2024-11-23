package com.ssafy.gilbut.domain.travel.converter;

import com.ssafy.gilbut.domain.course.converter.CourseConverter;
import com.ssafy.gilbut.domain.course.model.dto.CourseResponse.SimpleResultDTO;
import com.ssafy.gilbut.domain.plan.converter.PlanConverter;
import com.ssafy.gilbut.domain.plan.model.dto.response.PlanResponse.DetailResultListDTO;
import com.ssafy.gilbut.domain.travel.model.dto.TravelResponse;
import com.ssafy.gilbut.domain.travel.model.entity.Travel;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class TravelConverter {

    /**
     * 여행 세부 DTO로 변환
     * @param travel
     * @return
     */
    public static TravelResponse.DetailResultDTO toDetailResultDTO(Travel travel) {
        SimpleResultDTO courseDTO = CourseConverter.toSimpleResultDTO(travel.getCourse());
        DetailResultListDTO planDTOList = PlanConverter.toDetailResultListDTO(travel.getPlans());
        return TravelResponse.DetailResultDTO.builder()
                .id(travel.getId())
                .title(travel.getTitle())
                .content(travel.getContent())
                .course(courseDTO)
                .plans(planDTOList)
                .startDate(travel.getStartDate())
                .build();
    }

    /**
     * 여행 간단 DTO로 변환
     * @param travel
     * @return
     */
    public static TravelResponse.SimpleResultDTO toSimpleResultDTO(Travel travel) {
        SimpleResultDTO courseDTO = CourseConverter.toSimpleResultDTO(travel.getCourse());
        DetailResultListDTO planDTOList = PlanConverter.toDetailResultListDTO(travel.getPlans());

        return TravelResponse.SimpleResultDTO.builder()
                .id(travel.getId())
                .title(travel.getTitle())
                .course(courseDTO)
                .plans(planDTOList)
                .startDate(travel.getStartDate())
                .build();
    }

    /**
     * 여행 간단 페이징 DTO로 변환
     * @param travels
     * @param pageable
     * @param totalCount
     * @return
     */
    public static TravelResponse.SimplePageResultDTO toSimplePageResultDTO(List<Travel> travels, Pageable pageable, Integer totalCount) {
        List<TravelResponse.SimpleResultDTO> travelDTOList = travels.stream()
                .map(TravelConverter::toSimpleResultDTO)
                .toList();
        PageImpl<TravelResponse.SimpleResultDTO> page = new PageImpl<>(travelDTOList, pageable, totalCount);

        return TravelResponse.SimplePageResultDTO.builder()
                .travels(travelDTOList)
                .listSize(travelDTOList.size())
                .isFirstPage(page.isFirst())
                .isLastPage(page.isLast())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }

    /**
     * 여행 간단 페이지 리스트로 변환
     * @param travels
     * @return
     */
    public static TravelResponse.SimpleResultListDTO toSimpleResultListDTO(List<Travel> travels) {
        List<TravelResponse.SimpleResultDTO> list = travels.stream().map(TravelConverter::toSimpleResultDTO).toList();

        return TravelResponse.SimpleResultListDTO.builder()
                .travels(list)
                .build();
    }

}
