package com.ssafy.gilbut.domain.plan.model.dto.response;

import com.ssafy.gilbut.domain.attraction.model.dto.AttractionResponse;
import com.ssafy.gilbut.domain.course.model.dto.CourseSimpleResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "계획 응답 DTO")
public class PlanResponseDTO {

    @Schema(description = "계획 ID", example = "1")
    private Long id;

    @Schema(description = "관광지 DTO", example = "")
    private AttractionResponse.SimpleResultDTO attraction;

    @Schema(description = "여행 ID", example = "1")
    private Long travelId;

    @Schema(description = "코스 DTO", example = "COURSE_001")
    private CourseSimpleResponseDTO course;

    @Schema(description = "순서", example = "1.0")
    private Double order;
}