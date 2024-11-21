package com.ssafy.gilbut.domain.travel.model.dto.response;

import com.ssafy.gilbut.domain.course.model.dto.CourseSimpleResponseDTO;
import com.ssafy.gilbut.domain.user.model.dto.response.UserResponseDTO;
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
@Schema(description = "여행 상세 정보 응답 DTO")
public class TravelDetailResponseDTO {
    @Schema(description = "여행 ID", example = "1")
    private Integer id;

    @Schema(description = "여행 제목", example = "제주도 여행")
    private String title;

    @Schema(description = "여행 내용", example = "제주도의 아름다운 해변을 따라 걷는 여행")
    private String content;

    @Schema(description = "코스 정보")
    private CourseSimpleResponseDTO course;

    @Schema(description = "사용자 정보")
    private UserResponseDTO user;

    @Schema(description = "여행 시작 날짜", example = "2024-11-21")
    private LocalDate startDate;
}