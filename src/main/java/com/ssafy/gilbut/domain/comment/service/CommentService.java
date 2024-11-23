package com.ssafy.gilbut.domain.comment.service;

import static com.ssafy.gilbut.domain.comment.model.dto.CommentRequest.*;
import static com.ssafy.gilbut.domain.comment.model.dto.CommentResponse.*;

import com.ssafy.gilbut.domain.comment.model.dto.CommentRequest.UpdateDTO;

public interface CommentService {
    DetailResultDTO createComment(CreateDTO createDTO);
    DetailResultDTO updateComment(UpdateDTO updateDTO);
    void deleteComment(Integer commentId);
}
