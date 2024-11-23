package com.ssafy.gilbut.domain.travel.mapper;

import com.ssafy.gilbut.domain.travel.model.dto.TravelRequest;
import com.ssafy.gilbut.domain.travel.model.entity.Travel;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TravelMapper {
    List<Travel> findTravelListByUserId(@Param("userId") Long userId);

    Optional<Travel> findTravelByTravelId(@Param("travelId") Long travelId);

    int countTotalTravelByUserId(@Param("userId") Long userId);

    void deleteTravelByTravelId(@Param("travelId") Long travelId);

    void updateTravel(@Param("userId") Long userId, @Param("travelId") Long travelId, @Param("dto") TravelRequest.UpdateDTO createDTO);

    void travelCreate(@Param("userId") Long userId, @Param("dto") TravelRequest.CreateDTO createDTO);
}
