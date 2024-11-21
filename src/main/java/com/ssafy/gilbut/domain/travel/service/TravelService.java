package com.ssafy.gilbut.domain.travel.service;

import com.ssafy.gilbut.domain.travel.model.dto.request.TravelCreateRequestDTO;
import com.ssafy.gilbut.domain.travel.model.dto.request.TravelUpdateRequestDTO;
import com.ssafy.gilbut.domain.travel.model.dto.response.TravelDetailResponseDTO;
import com.ssafy.gilbut.domain.travel.model.dto.response.TravelResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TravelService {
    Page<TravelResponseDTO> travelList(String accessToken, Pageable page);

    TravelDetailResponseDTO getTravelById(Integer travelId);

    TravelDetailResponseDTO travelCreate(String accessToken, TravelCreateRequestDTO travelCreateRequestDTO);

    TravelDetailResponseDTO updateTravel(String accessToken, Integer travelId, TravelUpdateRequestDTO travelDTO);

    void deleteTravelById(String accessToken, Integer travelId);
}
