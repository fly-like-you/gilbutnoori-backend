package com.ssafy.gilbut.domain.travel.service;

import com.ssafy.gilbut.advice.status.ErrorStatus;
import com.ssafy.gilbut.domain.travel.converter.TravelConverter;
import com.ssafy.gilbut.domain.travel.mapper.TravelMapper;
import com.ssafy.gilbut.domain.travel.model.dto.TravelRequest;
import com.ssafy.gilbut.domain.travel.model.dto.TravelResponse;
import com.ssafy.gilbut.domain.travel.model.entity.Travel;
import com.ssafy.gilbut.exception.handler.GeneralExceptionHandler;
import com.ssafy.gilbut.util.JWTUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TravelServiceImpl implements TravelService {

    private final TravelMapper travelMapper;
    private final JWTUtil jwtUtil;

    /**
     * @param accessToken
     * @param page
     * @return
     */
    @Override
    public TravelResponse.SimplePageResultDTO travelList(String accessToken, Pageable page) {
        log.trace("offset = {}, pageSize = {}", page.getOffset(), page.getPageSize());

        Long userId = jwtUtil.getUserId(accessToken);
        List<Travel> dtoList = travelMapper.findTravelListByUserId(userId);

        int totalCount = travelMapper.countTotalTravelByUserId(userId);

        return TravelConverter.toSimplePageResultDTO(dtoList, page, totalCount);
    }

    /**
     * @param travelId
     * @return
     */
    @Override
    public TravelResponse.DetailResultDTO getTravelById(String accessToken, Long travelId) {
        // 유저와 여행 기록 검사
        Long userId = jwtUtil.getUserId(accessToken);
        validateTravelAndUserMatching(travelId, userId);

        Travel travel = getSafeTravelByTravelId(travelId);
        log.info("travel = {}", travel);

        return TravelConverter.toDetailResultDTO(travel);
    }

    /**
     * @param accessToken
     * @param createDTO
     * @return
     */
    @Override
    public TravelResponse.DetailResultDTO travelCreate(String accessToken, TravelRequest.CreateDTO createDTO) {
        Long userId = jwtUtil.getUserId(accessToken);
        travelMapper.travelCreate(userId, createDTO);

        Travel travel = getSafeTravelByTravelId(createDTO.getId());
        log.info("travel = {}", travel);
        return TravelConverter.toDetailResultDTO(travel);

    }

    /**
     * @param accessToken
     * @param travelId
     * @param updateDTO
     * @return
     */
    @Override
    public TravelResponse.DetailResultDTO updateTravel(String accessToken, Long travelId, TravelRequest.UpdateDTO updateDTO) {
        log.info("travel -> {}", updateDTO);

        // 유저와 여행 기록 검사
        Long userId = jwtUtil.getUserId(accessToken);
        validateTravelAndUserMatching(travelId, userId);

        travelMapper.updateTravel(userId, travelId, updateDTO);

        Travel travel = getSafeTravelByTravelId(travelId);
        log.info("travel = {}", travel);

        return TravelConverter.toDetailResultDTO(travel);

    }

    /**
     * @param accessToken
     * @param travelId
     */
    @Override
    public void deleteTravelById(String accessToken, Long travelId) {
        // 유저와 여행 기록 검사
        Long userId = jwtUtil.getUserId(accessToken);
        validateTravelAndUserMatching(travelId, userId);

        log.info("delete travel id -> {}", travelId);
        travelMapper.deleteTravelByTravelId(travelId);
    }


    private Travel getSafeTravelByTravelId(Long travelId) {
        return travelMapper.findTravelByTravelId(travelId).orElseThrow(
                () -> new GeneralExceptionHandler(ErrorStatus.TRAVEL_NOT_FOUND)
        );
    }

    private void validateTravelAndUserMatching(Long travelId, Long userId) {
        Travel travel = getSafeTravelByTravelId(travelId);
        Long travelUserId = travel.getUser().getId();

        if (!travelUserId.equals(userId)) throw new GeneralExceptionHandler(ErrorStatus.TRAVEL_OWNER_NOT_MATCHED);
    }


}
