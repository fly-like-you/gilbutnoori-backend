package com.ssafy.gilbut.domain.attraction.model.entity;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Attraction {
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
