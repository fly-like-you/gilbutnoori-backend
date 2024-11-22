package com.ssafy.gilbut.domain.attraction.converter;

import com.ssafy.gilbut.domain.attraction.model.dto.AttractionResponse;
import com.ssafy.gilbut.domain.attraction.model.entity.Attraction;

public class AttractionConverter {
    public static AttractionResponse.DetailResultDTO toDetailResultDTO(Attraction attraction) {

        return AttractionResponse.DetailResultDTO.builder()
                .id(attraction.getId())
                .contentTypeId(attraction.getContentTypeId())
                .siGunGuCode(attraction.getSiGunGuCode())
                .title(attraction.getTitle())
                .firstImage1(attraction.getFirstImage1())
                .firstImage2(attraction.getFirstImage2())
                .mapLevel(attraction.getMapLevel())
                .latitude(attraction.getLatitude())
                .longitude(attraction.getLongitude())
                .tel(attraction.getTel())
                .addr1(attraction.getAddr1())
                .addr2(attraction.getAddr2())
                .homepage(attraction.getHomepage())
                .overview(attraction.getOverview())
                .build();
    }

    public static AttractionResponse.SimpleResultDTO toSimpleResultDTO(Attraction attraction) {

        return AttractionResponse.SimpleResultDTO.builder()
                .id(attraction.getId())
                .contentType(attraction.getContentTypeId().toString())
                .siGunGu(attraction.getSiGunGuCode().toString())
                .title(attraction.getTitle())
                .firstImage1(attraction.getFirstImage1())
                .firstImage2(attraction.getFirstImage2())
                .latitude(attraction.getLatitude())
                .longitude(attraction.getLongitude())
                .addr1(attraction.getAddr1())
                .addr2(attraction.getAddr2())
                .build();
    }
}
