package com.ssafy.gilbut.domain.attraction.service;

import com.ssafy.gilbut.domain.attraction.model.dto.AttractionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AttractionService {
    Page<AttractionDTO> searchTravelingPointByCourseId(String courseId, Integer contentTypeId, Pageable pageable);
}
