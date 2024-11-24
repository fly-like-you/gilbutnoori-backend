package com.ssafy.gilbut.domain.travel.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TravelRequest {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "여행 생성 요청 DTO")
    public static class CreateDTO {
        @Schema(description = "여행 ID (자동 생성)")
        private Long id;

        @Schema(description = "여행 제목", example = "제주도 여행")
        private String title;

        @Schema(description = "여행 내용", example = "제주도의 아름다운 해변을 따라 걷는 여행")
        private String content;

        @Schema(description = "코스 ID", example = "1")
        private String courseId;

        @Schema(description = "여행 시작 날짜", example = "2024-11-21")
        private LocalDate startDate;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "여행 업데이트 요청 DTO")
    public static class UpdateDTO {
        private String courseId;
        private String title;
        private String content;
        private LocalDate startDate;
    }
}
