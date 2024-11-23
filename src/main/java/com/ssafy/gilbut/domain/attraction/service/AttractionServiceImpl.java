package com.ssafy.gilbut.domain.attraction.service;

import com.ssafy.gilbut.domain.attraction.converter.AttractionConverter;
import com.ssafy.gilbut.domain.attraction.mapper.AttractionMapper;
import com.ssafy.gilbut.domain.attraction.model.dto.AttractionResponse;
import com.ssafy.gilbut.domain.attraction.model.entity.Attraction;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService{

    private final AttractionMapper attractionMapper;

    @Override
    public AttractionResponse.DetailPageResultDTO searchTravelingPointByCourseId(String courseId, Integer contentTypeId, Pageable page) {
        log.trace("offset = {}, pageSize = {}", page.getOffset(), page.getPageSize());

        List<Attraction> contents = attractionMapper.searchAttractionByCourseId(courseId, contentTypeId, page);
        int totalCount = attractionMapper.countAttractionByCourseId(courseId, contentTypeId);

        return AttractionConverter.toDetailPageResultDTO(contents, page, totalCount);
    }
}
