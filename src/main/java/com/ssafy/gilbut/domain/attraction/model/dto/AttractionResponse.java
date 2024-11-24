package com.ssafy.gilbut.domain.attraction.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class AttractionResponse {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResultDTO {
        private Long id;
        private String contentType;

        private String area;
        private String gunGu;

        private String title;
        private String firstImage1;
        private String firstImage2;
        private Double latitude;
        private Double longitude;
        private String addr1;
        private String addr2;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailResultDTO {
        private Long id;

        /* 컨텐츠 타입 */
        private Long contentTypeId;
        private String contentType;

        /* 시군구 타입 */
        private Long areaCode;
        private String area;

        private Long siGunGuCode;
        private String gunGu;

        /* 기타 정보 */
        private String title;
        private String firstImage1;
        private String firstImage2;
        private Integer mapLevel;
        private Double latitude;
        private Double longitude;
        private String tel;
        private String addr1;
        private String addr2;
        private String homepage;
        private String overview;
    }
    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "DetailPageResultDTO : 관광지 목록 & 페이지 정보", description = "관광지 목록과 페이지 정보를 나타낸다.")
    public static class DetailPageResultDTO {
        @Schema(description = "관광지 목록")
        private List<AttractionResponse.DetailResultDTO> attractions;

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
}
