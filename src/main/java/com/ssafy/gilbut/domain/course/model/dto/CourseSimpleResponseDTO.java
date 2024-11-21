package com.ssafy.gilbut.domain.course.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "코스 간단 정보 응답 DTO")
public class CourseSimpleResponseDTO {
    @Schema(description = "코스 이름", example = "해파랑길 5코스")
    private String name;

    @Schema(description = "코스 길이(km)", example = "18")
    private Integer dist;

    @Schema(description = "소요 시간 (분)", example = "360")
    private Integer turnaround;

    @Schema(description = "난이도", example = "2")
    private Integer level;
}