package com.ssafy.gilbut.domain.attraction.service;

import com.ssafy.gilbut.domain.attraction.model.dto.AttractionResponse;
import org.springframework.data.domain.Pageable;

public interface AttractionService {
    AttractionResponse.DetailPageResultDTO searchTravelingPointByCourseId(String courseId, Integer contentTypeId, Pageable pageable);
}
