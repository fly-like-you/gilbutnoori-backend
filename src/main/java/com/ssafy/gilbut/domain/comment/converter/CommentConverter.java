package com.ssafy.gilbut.domain.comment.converter;

import com.ssafy.gilbut.domain.comment.model.dto.CommentResponse;
import com.ssafy.gilbut.domain.comment.model.dto.CommentResponse.DetailListResultDTO;
import com.ssafy.gilbut.domain.comment.model.dto.CommentResponse.DetailResultDTO;
import com.ssafy.gilbut.domain.comment.model.entity.Comment;
import java.util.List;

public class CommentConverter {
    public static DetailListResultDTO toDetailListResultDTO(List<Comment> commentList) {

        List<CommentResponse.DetailResultDTO> comments = commentList.stream()
                .map(CommentConverter::toDetailResultDTO).toList();

        return CommentResponse.DetailListResultDTO.builder()
                .comments(comments)
                .build();
    }

    public static DetailResultDTO toDetailResultDTO(Comment comment) {
        return CommentResponse.DetailResultDTO.builder()
                .id(comment.getId())
                .nickname(comment.getUser().getNickname())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }

}
