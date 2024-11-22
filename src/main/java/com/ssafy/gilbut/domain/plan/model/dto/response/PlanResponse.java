package com.ssafy.gilbut.domain.plan.model.dto.response;

import com.ssafy.gilbut.domain.attraction.model.dto.AttractionResponse;
import com.ssafy.gilbut.domain.course.model.dto.CourseResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class PlanResponse {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "계획 응답 DTO")
    public static class DetailResultDTO {

        @Schema(description = "계획 ID", example = "1")
        private Long id;

        @Schema(description = "순서", example = "1.0")
        private Double sequence;

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
    @Schema(description = "계획 페이징 DTO")
    public static class DetailResultPageDTO {
        private List<DetailResultDTO> planResult;
        private Integer listSize;
        private boolean isFirstPage;
        private boolean isLastPage;
        private Integer totalPages;
        private Long totalElements;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "계획 리스트 DTO")
    public static class DetailResultListDTO {
        private List<DetailResultDTO> planResult;
    }

}
