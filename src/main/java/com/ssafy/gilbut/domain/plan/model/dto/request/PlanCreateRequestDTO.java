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
@Schema(description = "계획 생성 요청 DTO")
public class PlanCreateRequestDTO {

    private Integer id;

    @Schema(description = "관광지 ID", example = "1")
    private Integer attractionId;

    @Schema(description = "여행 ID", example = "1")
    private Integer travelId;

    @Schema(description = "코스 ID", example = "COURSE_001")
    private String courseId;

    @Schema(description = "순서", example = "1.0")
    private Double order;

}

