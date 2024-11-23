package com.ssafy.gilbut.domain.board.model.entity;

import com.ssafy.gilbut.domain.comment.model.entity.Comment;
import com.ssafy.gilbut.domain.travel.model.entity.Travel;
import com.ssafy.gilbut.domain.user.model.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Schema(description = "글번호")
    private int id;

    @Schema(description = "작성자")
    private User user;

    @Schema(description = "글제목")
    private String title;

    @Schema(description = "선택한 여행 정보")
    private Travel travel;

    @Schema(description = "댓글 리스트")
    private List<Comment> comments = new ArrayList<>();

    @Schema(description = "글내용")
    private String content;

    @Schema(description = "조회수")
    private int hit;

    @Schema(description = "작성일")
    private LocalDate created_at;

    @Schema(description = "수정일")
    private LocalDate updated_at;

    @Schema(description = "업로드 파일정보")
    private List<FileInfo> fileInfos = new ArrayList<>();


}