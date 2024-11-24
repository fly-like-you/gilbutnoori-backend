package com.ssafy.gilbut.domain.board.model.dto;

import com.ssafy.gilbut.domain.comment.model.dto.CommentResponse;
import com.ssafy.gilbut.domain.travel.model.dto.TravelResponse;
import com.ssafy.gilbut.domain.travel.model.entity.Travel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Schema(title = "게시글 응답 관련", description = "게시글의 상세 정보를 나타낸다.")
public class BoardResponse {

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "DetailResultDTO : 게시글정보", description = "게시글의 상세 정보를 나타낸다.")
    public static class DetailResultDTO {

        @Schema(description = "글번호")
        private Long id;

        @Schema(description = "작성자 아이디")
        private String loginId;

        @Schema(description = "작성자 이름")
        private String nickname;

        @Schema(description = "글제목")
        private String title;

        @Schema(description = "선택한 여행 정보")
        private TravelResponse.DetailResultDTO travel;

        @Schema(description = "댓글 리스트")
        private CommentResponse.DetailListResultDTO comments;

        @Schema(description = "글내용")
        private String content;

        @Schema(description = "조회수")
        private int hit;

        @Schema(description = "작성일")
        private LocalDate created_at;

        @Schema(description = "업로드 파일정보")
        private List<FileInfoDto> fileInfos;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "SimpleResultDTO : 게시글정보", description = "게시글의 간단한 정보를 나타낸다")
    public static class SimpleResultDTO {
        @Schema(description = "글번호")
        private Long id;

        @Schema(description = "글제목")
        private String title;

        @Schema(description = "작성자 아이디")
        private String loginId;

        @Schema(description = "작성자 이름")
        private String nickname;

        @Schema(description = "조회수")
        private int hit;

        private List<FileInfoDto> fileInfo;

        @Schema(description = "작성일")
        private LocalDate created_at;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "BoardListDto : 게시글 목록 & 페이지 정보", description = "게시글 목록과 현재 페이지와 전체 페이지 정보를 나타낸다.")
    public static class SimplePageResultDTO {
        @Schema(description = "글목록")
        private List<SimpleResultDTO> articles;

        @Schema(description = "페이지당 항목 수",
                example = "10")
        private Integer listSize;

        @Schema(description = "첫 페이지 여부",
                example = "true")
        private boolean isFirstPage;

        @Schema(description = "마지막 페이지 여부",
                example = "false")
        private boolean isLastPage;

        @Schema(description = "전체 페이지 수",
                example = "5")
        private Integer totalPages;

        @Schema(description = "전체 항목 수",
                example = "42")
        private Long totalElements;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "BoardListDto : 게시글 목록", description = "게시글 목록과 현재 페이지와 전체 페이지 정보를 나타낸다.")
    public static class SimpleListResultDTO {
        @Schema(description = "글목록")
        private List<SimpleResultDTO> articles;

    }
}
