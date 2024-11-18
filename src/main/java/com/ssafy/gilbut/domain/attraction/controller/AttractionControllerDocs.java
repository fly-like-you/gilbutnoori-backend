package com.ssafy.gilbut.domain.attraction.controller;

import com.ssafy.gilbut.util.SizeConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "관광지 정보 컨트롤러",description = "관광지 정보 상세보기")
public interface AttractionControllerDocs {
    @Operation(
            summary =
                    "코스 근처 관광지 정보 가져오기",
            description =
                    "코스 근처에 있는 관광지 정보를 가져옵니다. "
                            + "필터를 통해 다양한 관광지 정보를 얻을 수 있습니다."
    )
    ResponseEntity<?> travelingPointList(
            @Parameter(description = "코스 ID", required = true) String courseId,
            @Parameter(description = "컨텐츠 타입 ID") @RequestParam Integer contentTypeId,
            @PageableDefault(size = SizeConstant.LIST_SIZE) Pageable page
    );
}
