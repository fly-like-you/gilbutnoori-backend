package com.ssafy.gilbut.domain.travel.controller;

import com.ssafy.gilbut.advice.ApiResponse;
import com.ssafy.gilbut.domain.travel.model.dto.TravelRequest;
import com.ssafy.gilbut.domain.travel.model.dto.TravelResponse;
import com.ssafy.gilbut.domain.travel.model.dto.TravelResponse.DetailResultDTO;
import com.ssafy.gilbut.domain.travel.service.TravelService;
import com.ssafy.gilbut.util.SizeConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/travels")
@RequiredArgsConstructor
public class TravelController implements TravelControllerDocs {

    private final TravelService travelService;

    /**
     * @param accessToken
     * @return
     */
    @Override
    @GetMapping
    public ResponseEntity<?> getTravelListByUserId(
            @RequestHeader("Authorization") String accessToken,
            @PageableDefault(size = SizeConstant.LIST_SIZE) Pageable page
    ) {
        TravelResponse.SimplePageResultDTO travelList = travelService.travelList(accessToken, page);
        log.info("travel list: {}", travelList);


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.onSuccess(travelList));
    }

    /**
     * 토큰 필요없음
     * @param travelId
     * @return
     */
    @Override
    @GetMapping("/{travelId}")
    public ResponseEntity<?> getTravelById(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable("travelId") Long travelId
    ) {
        DetailResultDTO travel = travelService.getTravelById(accessToken, travelId);
        log.info("travel: {}", travel);

        return ResponseEntity.ok(ApiResponse.onSuccess(travel));
    }

    /**
     * @param accessToken
     * @param createDTO
     * @return
     */
    @Override
    @PostMapping
    public ResponseEntity<?> createTravel(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody TravelRequest.CreateDTO createDTO
    ) {
        DetailResultDTO result = travelService.travelCreate(accessToken, createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(result));
    }

    /**
     * @param travelId
     * @return
     */
    @Override
    @DeleteMapping("/{travelId}")
    public ResponseEntity<?> deleteTravelById(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable("travelId") Long travelId
    ) {
        travelService.deleteTravelById(accessToken, travelId);

        return ResponseEntity.noContent().build();
    }

    /**
     * @param accessToken
     * @param travelId
     * @param updateDTO
     * @return
     */
    @Override
    @PutMapping("/{travelId}")
    public ResponseEntity<?> updateTravelById(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable("travelId") Long travelId,
            @RequestBody TravelRequest.UpdateDTO updateDTO
    ) {
        DetailResultDTO travel = travelService.updateTravel(accessToken, travelId, updateDTO);
        log.info("updated travel: {}", travel);

        return ResponseEntity.ok(ResponseEntity.noContent().build());
    }
}
