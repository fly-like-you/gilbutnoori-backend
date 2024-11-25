package com.ssafy.gilbut.domain.course.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

public class CourseResponse {

    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(description = "코스 간단 정보 응답 DTO")
    public static class SimpleResultDTO {
        @Schema(description = "둘렛길 아이디", requiredMode = Schema.RequiredMode.REQUIRED, example = "T_CRS_MNG0000004190")
        private String id;

        @Schema(description = "코스 이름", example = "해파랑길 5코스")
        private String name;

        @Schema(description = "코스 정보 요약", example = "진하해변을 출발해 덕하역까지 구간 - 해파랑길 울산 구간이 시작되는 코스...")
        private String summary;

        @Schema(description = "둘렛길 관할 구역", example = "울산 울주군")
        private String sigun;

        @Schema(description = "길 이름", example = "해파랑길")
        private RouteResponse.SimpleResultDTO route;

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
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "CourseResponse SimpleResultDTO : 코스 목록 & 페이지 정보", description = "코스 목록과 현재 페이지와 전체 페이지 정보를 나타낸다.")
    public static class SimplePageResultDTO {
        @Schema(description = "글목록")
        private List<CourseResponse.SimpleResultDTO> courses;

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
    @Schema(title = "Course 목록", description = "코스 정보 리스트")
    public static class SimpleResultListDTO {
        @Schema(description = "Course 목록")
        private List<CourseResponse.SimpleResultDTO> courses;

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

        @Schema(description = "길 이름", example = "해파랑길")
        private RouteResponse.SimpleResultDTO route;

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

    }
}
