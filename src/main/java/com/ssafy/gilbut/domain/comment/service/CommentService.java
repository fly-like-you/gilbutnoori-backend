package com.ssafy.gilbut.domain.comment.service;

import static com.ssafy.gilbut.domain.comment.model.dto.CommentRequest.CreateDTO;
import static com.ssafy.gilbut.domain.comment.model.dto.CommentResponse.DetailListResultDTO;
import static com.ssafy.gilbut.domain.comment.model.dto.CommentResponse.DetailResultDTO;

import com.ssafy.gilbut.domain.comment.model.dto.CommentRequest.UpdateDTO;

public interface CommentService {
    DetailListResultDTO listComment();
    DetailResultDTO createComment(String accessToken, CreateDTO createDTO);
    DetailResultDTO updateComment(String accessToken, Integer commentId, UpdateDTO updateDTO);
    void deleteComment(String accessToken, Integer commentId);
}
