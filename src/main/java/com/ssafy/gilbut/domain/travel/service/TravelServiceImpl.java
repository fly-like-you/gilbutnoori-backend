package com.ssafy.gilbut.domain.travel.service;

import com.ssafy.gilbut.advice.status.ErrorStatus;
import com.ssafy.gilbut.domain.travel.mapper.TravelMapper;
import com.ssafy.gilbut.domain.travel.model.dto.request.TravelCreateRequestDTO;
import com.ssafy.gilbut.domain.travel.model.dto.request.TravelUpdateRequestDTO;
import com.ssafy.gilbut.domain.travel.model.dto.response.TravelDetailResponseDTO;
import com.ssafy.gilbut.domain.travel.model.dto.response.TravelResponseDTO;
import com.ssafy.gilbut.exception.handler.GeneralExceptionHandler;
import com.ssafy.gilbut.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Page<TravelResponseDTO> travelList(String accessToken, Pageable page) {
        log.trace("offset = {}, pageSize = {}", page.getOffset(), page.getPageSize());

        Integer userId = jwtUtil.getUserId(accessToken);
        List<TravelResponseDTO> dtoList = travelMapper.findTravelListByUserId(userId);

        int totalCount = travelMapper.countTotalTravelByUserId(userId);

        return new PageImpl<>(dtoList, page, totalCount);
    }

    /**
     * @param travelId
     * @return
     */
    @Override
    public TravelDetailResponseDTO getTravelById(Integer travelId) {
        TravelDetailResponseDTO travel = getSafeTravelByTravelId(travelId);
        log.info("travel = {}", travel);

        return travel;
    }

    /**
     * @param accessToken
     * @param travelCreateRequestDTO
     * @return
     */
    @Override
    public TravelDetailResponseDTO travelCreate(String accessToken, TravelCreateRequestDTO travelCreateRequestDTO) {
        Integer userId = jwtUtil.getUserId(accessToken);
        travelMapper.travelCreate(userId, travelCreateRequestDTO);

        TravelDetailResponseDTO travel = getSafeTravelByTravelId(travelCreateRequestDTO.getId());
        log.info("travel = {}", travel);
        return travel;
    }

    /**
     * @param accessToken
     * @param travelId
     * @param travelDTO
     * @return
     */
    @Override
    public TravelDetailResponseDTO updateTravel(String accessToken, Integer travelId, TravelUpdateRequestDTO travelDTO) {
        log.info("travel -> {}", travelDTO);

        Integer userId = jwtUtil.getUserId(accessToken);
        travelMapper.updateTravel(userId, travelId, travelDTO);

        return getSafeTravelByTravelId(travelId);
    }

    /**
     * @param accessToken
     * @param travelId
     */
    @Override
    public void deleteTravelById(String accessToken, Integer travelId) {
        log.info("delete travel id -> {}", travelId);
        travelMapper.deleteTravelByTravelId(travelId);
    }


    private TravelDetailResponseDTO getSafeTravelByTravelId(Integer travelId) {
        return travelMapper.findTravelByTravelId(travelId).orElseThrow(
                () -> new GeneralExceptionHandler(ErrorStatus.TRAVEL_NOT_FOUND)
        );
    }


}
