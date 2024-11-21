package com.ssafy.gilbut.domain.plan.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "계획 수정 요청 DTO")
public class PlanUpdateRequestDTO {

    @Schema(description = "관광지 ID", example = "1")
    private Long attractionId;

    @Schema(description = "코스 ID", example = "COURSE_001")
    private String courseId;

    @Schema(description = "순서", example = "1.0")
    private Double order;
}
