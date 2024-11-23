package com.ssafy.gilbut.domain.comment.controller;

import static com.ssafy.gilbut.domain.comment.model.dto.CommentRequest.CreateDTO;

import com.ssafy.gilbut.advice.ApiResponse;
import com.ssafy.gilbut.domain.comment.model.dto.CommentRequest.UpdateDTO;
import com.ssafy.gilbut.domain.comment.model.dto.CommentResponse.DetailResultDTO;
import com.ssafy.gilbut.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<?> commentList() {
        log.info("댓글 리스트 출력");
        return ResponseEntity.ok(ApiResponse.onSuccess(commentService.listComment()));
    }

    @PostMapping
    public ResponseEntity<?> writeComment(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody CreateDTO createDTO
    ) {
        log.info("댓글 작성");
        DetailResultDTO comment = commentService.createComment(accessToken, createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(comment));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable("commentId") Integer commentId,
            @RequestBody UpdateDTO updateDTO
    ) {
        log.info("댓글 수정");
        DetailResultDTO result = commentService.updateComment(accessToken, commentId, updateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(result));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable("commentId") Integer commentId
    ) {
        log.info("댓글 삭제");

        commentService.deleteComment(accessToken, commentId);
        return ResponseEntity.noContent().build();
    }

}
