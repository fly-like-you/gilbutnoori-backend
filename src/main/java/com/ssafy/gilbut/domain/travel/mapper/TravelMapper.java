package com.ssafy.gilbut.domain.travel.mapper;

import com.ssafy.gilbut.domain.travel.model.dto.request.TravelCreateRequestDTO;
import com.ssafy.gilbut.domain.travel.model.dto.request.TravelUpdateRequestDTO;
import com.ssafy.gilbut.domain.travel.model.dto.response.TravelDetailResponseDTO;
import com.ssafy.gilbut.domain.travel.model.dto.response.TravelResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TravelMapper {
    List<TravelResponseDTO> findTravelListByUserId(@Param("userId") Integer userId);

    Optional<TravelDetailResponseDTO> findTravelByTravelId(@Param("travelId") Integer travelId);

    int countTotalTravelByUserId(@Param("userId") Integer userId);

    void deleteTravelByTravelId(@Param("travelId") Integer travelId);

    void updateTravel(@Param("userId") Integer userId, @Param("travelId") Integer travelId, @Param("travel") TravelUpdateRequestDTO travelDTO);

    int travelCreate(@Param("userId") Integer userId, @Param("travel") TravelCreateRequestDTO travelCreateRequestDTO);
}
