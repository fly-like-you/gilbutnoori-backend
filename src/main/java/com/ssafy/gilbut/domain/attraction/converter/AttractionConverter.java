package com.ssafy.gilbut.domain.attraction.converter;

import com.ssafy.gilbut.domain.attraction.model.dto.AttractionResponse;
import com.ssafy.gilbut.domain.attraction.model.dto.AttractionResponse.DetailResultDTO;
import com.ssafy.gilbut.domain.attraction.model.entity.Attraction;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class AttractionConverter {
    public static AttractionResponse.DetailResultDTO toDetailResultDTO(Attraction attraction) {

        return AttractionResponse.DetailResultDTO.builder()
                .id(attraction.getId())

                .contentTypeId(attraction.getContentTypeId())
                .contentType(attraction.getContentType())

                .areaCode(attraction.getAreaCode())
                .area(attraction.getArea())

                .siGunGuCode(attraction.getSiGunGuCode())
                .gunGu(attraction.getGunGu())

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

                .area(attraction.getArea())
                .gunGu(attraction.getGunGu())

                .title(attraction.getTitle())
                .firstImage1(attraction.getFirstImage1())
                .firstImage2(attraction.getFirstImage2())
                .latitude(attraction.getLatitude())
                .longitude(attraction.getLongitude())
                .addr1(attraction.getAddr1())
                .addr2(attraction.getAddr2())

                .build();
    }

    public static AttractionResponse.DetailPageResultDTO toDetailPageResultDTO(
            List<Attraction> attractions, Pageable pageable, Integer totalCount
    ) {
        List<AttractionResponse.DetailResultDTO> attractionDTOs = attractions.stream().map(AttractionConverter::toDetailResultDTO).toList();
        PageImpl<DetailResultDTO> page = new PageImpl<>(attractionDTOs, pageable, totalCount);

        return AttractionResponse.DetailPageResultDTO.builder()
                .attractions(attractionDTOs)
                .listSize(attractionDTOs.size())
                .isFirstPage(page.isFirst())
                .isLastPage(page.isLast())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }
}
