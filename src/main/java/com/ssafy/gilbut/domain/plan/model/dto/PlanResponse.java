package com.ssafy.gilbut.domain.plan.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.gilbut.domain.attraction.model.dto.AttractionResponse;
import com.ssafy.gilbut.domain.course.model.dto.CourseResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "계획 응답 관련")
public class PlanResponse {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "PlanResponseDetailDTO", description = "계획 응답 DTO")
    public static class DetailResultDTO {

        @Schema(description = "계획 ID", example = "1")
        private Long id;

        @Schema(description = "순서", example = "1.0")
        private Double sequence;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Schema(description = "여행 ID", example = "1")
        private Long travelId;

        @Schema(description = "관광지 DTO", example = "")
        private AttractionResponse.DetailResultDTO attraction;

        @Schema(description = "코스 DTO", example = "COURSE_001")
        private CourseResponse.DetailResultDTO course;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "PlanDetailResultPageDTO", description = "계획 페이징 결과 DTO")
    public static class DetailResultPageDTO {

        @Schema(description = "계획 상세 정보 리스트",
                example = "[{\"planId\": 1, \"title\": \"제주도 여행\"}, {\"planId\": 2, \"title\": \"부산 여행\"}]")
        private List<DetailResultDTO> planResult;

        @Schema(description = "페이지당 항목 수",
                example = "10")
        private Integer listSize;

        @Schema(description = "첫 페이지 여부",
                example = "true")
        private boolean isFirstPage;

        @Schema(description = "마지막 페이지 여부",
                example = "false")
        private boolean isLastPage;

        @Schema(description = "전체 페이지 수",
                example = "5")
        private Integer totalPages;

        @Schema(description = "전체 항목 수",
                example = "42")
        private Long totalElements;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "PlanDetailResultListDTO", description = "계획 리스트 결과 DTO")
    public static class DetailResultListDTO {

        @Schema(description = "계획 상세 정보 리스트",
                example = "[{\"planId\": 1, \"title\": \"제주도 여행\"}, {\"planId\": 2, \"title\": \"부산 여행\"}]")
        private List<DetailResultDTO> planResult;
    }

}
