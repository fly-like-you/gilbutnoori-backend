package com.ssafy.gilbut.domain.travel.model.dto.response;

import com.ssafy.gilbut.domain.course.model.dto.CourseSimpleResponseDTO;
import com.ssafy.gilbut.domain.plan.model.dto.response.PlanResponseDTO;
import com.ssafy.gilbut.domain.user.model.dto.response.UserResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "여행 목록 응답 DTO")
public class TravelResponseDTO {
    @Schema(description = "여행 ID", example = "1")
    private Integer id;

    @Schema(description = "여행 제목", example = "제주도 여행")
    private String title;

    @Schema(description = "코스 정보")
    private CourseSimpleResponseDTO course;

    @Schema(description = "여행 계획 정보")
    private List<PlanResponseDTO> plans = new ArrayList<>();

    @Schema(description = "사용자 정보")
    private UserResponseDTO user;

    @Schema(description = "여행 시작 날짜", example = "2024-11-21")
    private LocalDate startDate;
}