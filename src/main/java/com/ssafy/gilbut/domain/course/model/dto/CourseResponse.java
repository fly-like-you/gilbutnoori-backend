package com.ssafy.gilbut.domain.course.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

public class CourseResponse {
    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(description = "코스 간단 정보 응답 DTO")
    public static class SimpleResultDTO {
        @Schema(description = "코스 이름", example = "해파랑길 5코스")
        private String name;

        @Schema(description = "코스 길이(km)", example = "18")
        private Integer dist;

        @Schema(description = "소요 시간 (분)", example = "360")
        private Integer turnaround;

        @Schema(description = "난이도", example = "2")
        private Integer level;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailResultDTO {
        @Schema(description = "둘렛길 아이디", requiredMode = Schema.RequiredMode.REQUIRED, example = "T_CRS_MNG0000004190")
        private String id;

        @Schema(description = "코스 이름", example = "해파랑길 5코스")
        private String name;

        @Schema(description = "코스 길이(km)", example = "18")
        private Integer dist;

        @Schema(description = "소요 시간 (분)", example = "360")
        private Integer turnaround;

        @Schema(description = "난이도", example = "2")
        private Integer level;

        @Schema(description = "순환형, 비순환형", example = "비순환형")
        private String cycle;

        @Schema(description = "코스 정보 요약", example = "진하해변을 출발해 덕하역까지 구간 - 해파랑길 울산 구간이 시작되는 코스...")
        private String summary;

        @Schema(description = "상세 정보", example = "간절곶 북쪽 진하해변을 출발해 회야강을 따라 외고산 옹기마을까지 간 후 덕하역까지 걷는 코스다.")
        private String detail;

        @Schema(description = "여행 포인트", example = "수심이 얕으며 백사장이 넓고 바닷물이 맑은 진하해변,  옹기마을")
        private String travelPoint;

        @Schema(description = "여행 정보 (교통)", example = "울산시외고속버스터미널에서 도보 500m 이동 후 보람병원입구정류장(고리스포츠문화센터 방면) 에서 715번 버스 이용")
        private String travelerInfo;

        @Schema(description = "둘렛길 관할 구역", example = "울산 울주군")
        private String sigun;

        @Schema(description = "자전거 도로 구분", example = "DNWW")
        private String roadOrBike;

        @Schema(description = "둘렛길 생성된 시간", example = "2017-01-20 04:20:00")
        private LocalDate createdAt;

        @Schema(description = "둘렛길 수정 시간", example = "2017-01-20 04:20:00")
        private LocalDate updatedAt;

        @Schema(description = "길", example = "T_THEME_MNG0000011235")
        private RouteResponse.DetailResultDTO route;
    }
}
