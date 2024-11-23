package com.ssafy.gilbut.domain.travel.model.dto;

import com.ssafy.gilbut.domain.course.model.dto.CourseResponse;
import com.ssafy.gilbut.domain.plan.model.dto.response.PlanResponse.DetailResultListDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "여행 요청 관련")
public class TravelResponse {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "여행 목록 응답 DTO")
    public static class SimpleResultDTO {
        @Schema(description = "여행 ID", example = "1")
        private Long id;

        @Schema(description = "여행 제목", example = "제주도 여행")
        private String title;

        @Schema(description = "코스 정보")
        private CourseResponse.SimpleResultDTO course;

        @Schema(description = "여행 계획 정보")
        private DetailResultListDTO plans;

        @Schema(description = "여행 시작 날짜", example = "2024-11-21")
        private LocalDate startDate;
    }
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "여행 상세 정보 응답 DTO")
    public static class DetailResultDTO {
        @Schema(description = "여행 ID", example = "1")
        private Long id;

        @Schema(description = "여행 제목", example = "제주도 여행")
        private String title;

        @Schema(description = "여행 내용", example = "제주도의 아름다운 해변을 따라 걷는 여행")
        private String content;

        @Schema(description = "코스 정보")
        private CourseResponse.SimpleResultDTO course;

        @Schema(description = "여행 계획 정보")
        private DetailResultListDTO plans;

        @Schema(description = "여행 시작 날짜", example = "2024-11-21")
        private LocalDate startDate;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "SimpleResultDTO : 여행 목록 & 페이지 정보", description = "여행 목록과 현재 페이지와 전체 페이지 정보를 나타낸다.")
    public static class SimplePageResultDTO {
        @Schema(description = "글목록")
        private List<SimpleResultDTO> travels;

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
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "Travel 여행 목록", description = "게시글 목록과 현재 페이지와 전체 페이지 정보를 나타낸다.")
    public static class SimpleResultListDTO {
        @Schema(description = "여행 목록")
        private List<SimpleResultDTO> travels;

    }
}
