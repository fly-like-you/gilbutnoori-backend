package com.ssafy.gilbut.domain.comment.model.entity;

import com.ssafy.gilbut.domain.board.model.entity.Board;
import com.ssafy.gilbut.domain.user.model.entity.User;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer id;
    private User user;
    private Board board;
    private String content;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
