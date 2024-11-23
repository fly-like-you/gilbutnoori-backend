package com.ssafy.gilbut.domain.comment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class CommentRequest {

    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateDTO {
        private Integer id;
        private Integer boardId;
        private String content;
    }

    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateDTO {
        private String content;

    }

}
