package com.ssafy.gilbut.course.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteDTO {

    @Schema(description = "길 아이디", requiredMode = RequiredMode.REQUIRED, example = "T_ROUTE_MNG0000000043")
    @Size(max = 30)
    @NotNull
    private String id;

    @Schema(description = "길 이름", example = "해파랑길")
    @Size(max = 30)
    @NotNull
    private String name;

    @Schema(description = "길 정보 요약", example = "부산에서부터 강원도 고성에 이르는 동해안을 따라 걷는 길")
    private String summary;

    @Schema(description = "상세 정보", example = "‘해파랑길’은 부산 오륙도 해맞이공원에서 강원 고성 통일전망대까지 이르는동해안의 해변길, 숲길, 마을길 등을 잇는 750km의 장거리 걷기여행길로")
    private String detail;

    @Schema(description = "자전거 도로 구분", example = "DNWW")
    @Size(max = 4)
    private String roadOrBike;

    @Future
    @Schema(description = "길 생성된 시간", example = "2017-01-20 04:20:00")
    private LocalDate createdAt;

    @Schema(description = "길 수정 시간", example = "2017-01-20 04:20:00")
    private LocalDate updatedAt;
}
