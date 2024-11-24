package com.ssafy.gilbut.domain.travel.service;

import com.ssafy.gilbut.domain.travel.model.dto.TravelRequest;
import com.ssafy.gilbut.domain.travel.model.dto.TravelResponse;
import org.springframework.data.domain.Pageable;

public interface TravelService {
    TravelResponse.SimplePageResultDTO  travelList(String accessToken, Pageable page);

    TravelResponse.DetailResultDTO getTravelById(String accessToken, Long travelId);

    TravelResponse.DetailResultDTO travelCreate(String accessToken, TravelRequest.CreateDTO createDTO);

    TravelResponse.DetailResultDTO updateTravel(String accessToken, Long travelId, TravelRequest.UpdateDTO updateDTO);

    void deleteTravelById(String accessToken, Long travelId);
}
