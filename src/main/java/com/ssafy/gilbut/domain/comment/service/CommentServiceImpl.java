package com.ssafy.gilbut.domain.comment.service;

import com.ssafy.gilbut.domain.comment.model.dto.CommentRequest.CreateDTO;
import com.ssafy.gilbut.domain.comment.model.dto.CommentRequest.UpdateDTO;
import com.ssafy.gilbut.domain.comment.model.dto.CommentResponse.DetailResultDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Override
    public DetailResultDTO createComment(CreateDTO createDTO) {
        return null;
    }

    @Override
    public DetailResultDTO updateComment(UpdateDTO updateDTO) {
        return null;
    }

    @Override
    public void deleteComment(Integer commentId) {

    }
}
