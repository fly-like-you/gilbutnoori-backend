package com.ssafy.gilbut.domain.travel.controller;

import com.ssafy.gilbut.advice.ApiResponse;
import com.ssafy.gilbut.domain.travel.model.dto.request.TravelCreateRequestDTO;
import com.ssafy.gilbut.domain.travel.model.dto.request.TravelUpdateRequestDTO;
import com.ssafy.gilbut.domain.travel.model.dto.response.TravelDetailResponseDTO;
import com.ssafy.gilbut.domain.travel.model.dto.response.TravelResponseDTO;
import com.ssafy.gilbut.domain.travel.service.TravelService;
import com.ssafy.gilbut.util.SizeConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> travelList(
            @RequestHeader("Authorization") String accessToken,
            @PageableDefault(size = SizeConstant.LIST_SIZE) Pageable page
    ) {
        Page<TravelResponseDTO> travelList = travelService.travelList(accessToken, page);
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
    public ResponseEntity<?> getTravelById(@PathVariable("travelId") Integer travelId) {
        TravelDetailResponseDTO travel = travelService.getTravelById(travelId);
        log.info("travel: {}", travel);

        return ResponseEntity.ok(ApiResponse.onSuccess(travel));
    }

    /**
     * @param accessToken
     * @param travelCreateRequestDTO
     * @return
     */
    @Override
    @PostMapping
    public ResponseEntity<?> travelCreate(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody TravelCreateRequestDTO travelCreateRequestDTO
    ) {
        travelService.travelCreate(accessToken, travelCreateRequestDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param travelId
     * @return
     */
    @Override
    @DeleteMapping("/{travelId}")
    public ResponseEntity<?> deleteTravelById(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable("travelId") Integer travelId
    ) {
        travelService.deleteTravelById(accessToken, travelId);

        return ResponseEntity.noContent().build();
    }

    /**
     * @param accessToken
     * @param travelId
     * @param travelDTO
     * @return
     */
    @Override
    @PutMapping("/{travelId}")
    public ResponseEntity<?> updateTravelById(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable("travelId") Integer travelId,
            @RequestBody TravelUpdateRequestDTO travelDTO
    ) {
        TravelDetailResponseDTO travel = travelService.updateTravel(accessToken, travelId, travelDTO);
        log.info("updated travel: {}", travel);

        return ResponseEntity.ok(ResponseEntity.noContent().build());
    }
}
