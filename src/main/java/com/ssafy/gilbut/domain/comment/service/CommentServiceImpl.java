package com.ssafy.gilbut.domain.comment.service;

import com.ssafy.gilbut.advice.status.ErrorStatus;
import com.ssafy.gilbut.domain.comment.converter.CommentConverter;
import com.ssafy.gilbut.domain.comment.mapper.CommentMapper;
import com.ssafy.gilbut.domain.comment.model.dto.CommentRequest.CreateDTO;
import com.ssafy.gilbut.domain.comment.model.dto.CommentRequest.UpdateDTO;
import com.ssafy.gilbut.domain.comment.model.dto.CommentResponse.DetailListResultDTO;
import com.ssafy.gilbut.domain.comment.model.dto.CommentResponse.DetailResultDTO;
import com.ssafy.gilbut.domain.comment.model.entity.Comment;
import com.ssafy.gilbut.exception.handler.GeneralExceptionHandler;
import com.ssafy.gilbut.util.JWTUtil;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final JWTUtil jwtUtil;
    private final CommentMapper commentMapper;

    @Override
    public DetailListResultDTO listComment() {
        List<Comment> commentList = commentMapper.listComment();
        log.info("댓글 사이즈 -> {}", commentList.size());

        return CommentConverter.toDetailListResultDTO(commentList);
    }

    @Override
    public DetailResultDTO createComment(String accessToken, CreateDTO createDTO) {
        Integer userId = jwtUtil.getUserId(accessToken);
        log.info("userId -> {}, createDTO -> {}", userId, createDTO);

        commentMapper.createComment(userId, createDTO);
        Comment comment = getSafeCommentByCommentId(createDTO.getId());
        log.info("생성된 댓글 -> {}", comment);

        return CommentConverter.toDetailResultDTO(comment);
    }

    @Override
    public DetailResultDTO updateComment(String accessToken, Integer commentId, UpdateDTO updateDTO) {
        validateCommentUserMatch(accessToken, commentId);
        log.info("commentId -> {}, updateDTO -> {}", commentId, updateDTO);

        log.info("입력 받은 값 -> {}", updateDTO);

        commentMapper.updateComment(commentId, updateDTO);
        Comment comment = getSafeCommentByCommentId(commentId);
        log.info("수정된 댓글 -> {}", comment);

        return CommentConverter.toDetailResultDTO(comment);
    }

    private void validateCommentUserMatch(String accessToken, Integer commentId) {
        Long userId = Long.valueOf(jwtUtil.getUserId(accessToken));
        log.info("접속한 유저 id -> {}, commentId -> {}", userId, commentId);

        Comment comment = getSafeCommentByCommentId(commentId);
        Long commentUserId = comment.getUser().getId();
        log.info("댓글 유저 id -> {}", commentUserId);

        if (!userId.equals(commentUserId)) throw new GeneralExceptionHandler(ErrorStatus.COMMENT_OWNER_NOT_MATCHED);
    }

    @Override
    public void deleteComment(String accessToken, Integer commentId) {
        validateCommentUserMatch(accessToken, commentId);
        log.info("commentId -> {}", commentId);

        commentMapper.deleteComment(commentId);

        log.info("정상적으로 삭제되었습니다 -> {}", commentId);

    }

    private Comment getSafeCommentByCommentId(Integer commentId) {
        Optional<Comment> comment = commentMapper.findCommentByCommentId(commentId);

        return comment.orElseThrow(() -> new GeneralExceptionHandler(ErrorStatus._INTERNAL_SERVER_ERROR));
    }
}
