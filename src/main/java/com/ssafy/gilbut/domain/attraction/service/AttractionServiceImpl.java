package com.ssafy.gilbut.domain.attraction.service;

import com.ssafy.gilbut.domain.attraction.mapper.AttractionMapper;
import com.ssafy.gilbut.domain.attraction.model.dto.AttractionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService{

    private final AttractionMapper attractionMapper;

    @Override
    public Page<AttractionDTO> searchTravelingPointByCourseId(String courseId, Pageable page) {
        log.trace("offset = {}, pageSize = {}", page.getOffset(), page.getPageSize());

        List<AttractionDTO> contents = attractionMapper.searchAttractionByCourseId(courseId, page);
        int count = attractionMapper.countAttractionByCourseId(courseId);
        return new PageImpl<>(contents,page,count);
    }
}
