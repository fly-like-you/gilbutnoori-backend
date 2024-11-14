package com.ssafy.gilbut.domain.travel.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Travel {

    @Schema(description = "여행 아이디", requiredMode = RequiredMode.REQUIRED, example = "1")
    @NotNull
    private Long id;

    @Schema(description = "둘렛길 아이디", requiredMode = RequiredMode.REQUIRED, example = "1")
    @NotNull
    private String courseId;

    @Schema(description = "사용자 아이디", requiredMode = RequiredMode.REQUIRED, example = "1")
    @NotNull
    private Long userId;

    @Schema(description = "여행 시작 날자", requiredMode = RequiredMode.REQUIRED, example = "2024-10-01")
    @NotNull
    private LocalDate startDate;

    @Schema(description = "여행 종료 날자", requiredMode = RequiredMode.REQUIRED, example = "2024-10-01")
    @NotNull
    private LocalDate endDate;

    @Schema(description = "관심구역 아이디", requiredMode = RequiredMode.REQUIRED, example = "1")
    private Long wishListId;

    @Schema(description = "자전거 도로 구분", example = "DNWW")
    @Size(max = 4)
    @NotNull
    private String roadOrBike;
}
