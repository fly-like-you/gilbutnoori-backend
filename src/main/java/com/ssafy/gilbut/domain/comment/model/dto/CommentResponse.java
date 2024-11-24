package com.ssafy.gilbut.domain.comment.model.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class CommentResponse {
    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailResultDTO {
        private Integer id;
        private String nickname;
        private String content;
        private LocalDate createdAt;
        private LocalDate updatedAt;
    }

    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailListResultDTO {
        private List<DetailResultDTO> comments;
    }
}
