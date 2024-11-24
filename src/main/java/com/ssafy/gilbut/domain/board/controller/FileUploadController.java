package com.ssafy.gilbut.domain.board.controller;

import com.ssafy.gilbut.advice.ApiResponse;
import com.ssafy.gilbut.domain.board.model.dto.FileInfoDto;
import com.ssafy.gilbut.domain.board.model.entity.FileInfo;
import com.ssafy.gilbut.domain.board.service.FileService;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// Controller 클래스
@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileUploadController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("boardId") Long boardId) throws IOException {

        fileService.saveFiles(files, boardId);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(null));


    }

}