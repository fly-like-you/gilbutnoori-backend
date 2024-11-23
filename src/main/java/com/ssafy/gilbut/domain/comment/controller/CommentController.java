package com.ssafy.gilbut.domain.comment.controller;

import com.ssafy.gilbut.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<?> commentList() {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> writeComment() {
        return null;
    }

    @PutMapping
    public ResponseEntity<?> updateComment() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteComment() {
        return null;
    }

}
