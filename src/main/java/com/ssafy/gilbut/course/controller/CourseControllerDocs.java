package com.ssafy.gilbut.course.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="코스 정보 컨트롤러", description = "코스 정보 상세보기, 리스트 보기 등의 처리를 하는 클래스")
public interface CourseControllerDocs {
    @Operation(summary = "코스 검색", description = "필터 별로 다양한 코스를 검색할 수 있는 기능")
    ResponseEntity<?> courseSearch(
            @Parameter(description = "필터링 용 맵 데이터") Map<String, String> map
    );

    @Operation(summary = "코스 전체 얻어오기", description = "코스 목록 데이터를 가져옵니다. 코스 데이터는 페이지네이션되어 제공됩니다.")
    ResponseEntity<?> courseList(
            @Parameter(description = "페이지 네이션 데이터") Map<String, String> map
    );

    @Operation(summary = "코스 상세 정보 얻어오기", description = "코스의 상세 정보 (자연 경관 설명, 오시는 길 등)이 제공 됩니다.")
    ResponseEntity<?> courseDetail(
            @Parameter(required = true, description = "조회할 코스 ID") int courseId, Map<String, String> map);

    @Operation(summary = "코스 GPX 가져오기", description = "코스의 GPX 데이터를 가져옵니다. XML방식으로 제공됩니다.")
    ResponseEntity<?> courseMapData(
            @Parameter(required = true, description = "조회할 코스 ID") int courseId
    );

    @Operation(
            summary =
                    "코스 근처 관광지 정보 가져오기",
            description =
                    "코스 근처에 있는 관광지 정보를 가져옵니다. "
                  + "필터를 통해 다양한 관광지 정보를 얻을 수 있습니다."
    )
    ResponseEntity<?> travelingPointList(int courseId);

}
