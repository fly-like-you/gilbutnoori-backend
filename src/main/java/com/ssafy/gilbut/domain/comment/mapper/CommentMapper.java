package com.ssafy.gilbut.domain.comment.mapper;

import com.ssafy.gilbut.domain.comment.model.dto.CommentRequest.CreateDTO;
import com.ssafy.gilbut.domain.comment.model.dto.CommentRequest.UpdateDTO;
import com.ssafy.gilbut.domain.comment.model.entity.Comment;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {
    List<Comment> listComment();

    void createComment(@Param("userId") Integer userId, @Param("dto") CreateDTO createDTO);

    void updateComment(@Param("commentId") Integer commentId, @Param("dto") UpdateDTO updateDTO);

    void deleteComment(@Param("commentId") Integer commentId);

    Optional<Comment> findCommentByCommentId(@Param("commentId") Integer commentId);
}
