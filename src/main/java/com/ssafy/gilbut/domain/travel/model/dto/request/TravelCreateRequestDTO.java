package com.ssafy.gilbut.domain.travel.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "여행 생성 요청 DTO")
public class TravelCreateRequestDTO {
    @Schema(description = "여행 ID (자동 생성)", hidden = true)
    private Integer id;

    @Schema(description = "여행 제목", example = "제주도 여행", required = true)
    private String title;

    @Schema(description = "여행 내용", example = "제주도의 아름다운 해변을 따라 걷는 여행")
    private String content;

    @Schema(description = "코스 ID", example = "1", required = true)
    private String courseId;

    @Schema(description = "여행 시작 날짜", example = "2024-11-21", required = true)
    private LocalDate startDate;
}