package com.ssafy.gilbut.domain.attraction.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AttractionResponse {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleResultDTO {
        private Long id;
        private String contentType;
        private String siGunGu;
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
        private Long contentTypeId;
        private Long siGunGuCode;
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
}
