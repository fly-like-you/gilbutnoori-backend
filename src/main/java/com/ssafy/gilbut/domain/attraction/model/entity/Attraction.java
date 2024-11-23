package com.ssafy.gilbut.domain.attraction.model.entity;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Attraction {
    private Long id;
    private Long contentId;

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
