package com.ssafy.gilbut.domain.attraction.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionSimpleDTO {
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
