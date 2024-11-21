package com.ssafy.gilbut.domain.plan.model.dto.response;

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

    @Schema(description = "관광지 ID", example = "1")
    private Long attractionId;

    @Schema(description = "여행 ID", example = "1")
    private Long travelId;

    @Schema(description = "코스 ID", example = "COURSE_001")
    private String courseId;

    @Schema(description = "순서", example = "1.0")
    private Double order;
}