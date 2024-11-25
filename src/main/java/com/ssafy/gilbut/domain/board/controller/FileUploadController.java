package com.ssafy.gilbut.domain.board.controller;

import com.ssafy.gilbut.advice.ApiResponse;
import com.ssafy.gilbut.domain.board.model.dto.FileInfoDto;
import com.ssafy.gilbut.domain.board.model.entity.FileInfo;
import com.ssafy.gilbut.domain.board.service.FileService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
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

    @PostMapping("/uploads")
    public ResponseEntity<?> uploadFiles(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("boardId") Long boardId) throws IOException {

        fileService.saveFiles(files, boardId);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(null));
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(
            @RequestParam("file") MultipartFile file,
            @RequestParam("boardId") Long boardId) throws IOException {

        FileInfoDto fileInfoDto = fileService.saveFile(file, boardId);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(fileInfoDto));
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<?> getFileInfo(@PathVariable("fileId") Long fileId) {
        try {
            // 파일 정보를 DB에서 가져옴
            FileInfo fileInfo = fileService.getFileInfoById(fileId);
            if (fileInfo == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("파일을 찾을 수 없습니다.");
            }

            // 파일 경로 생성
            Path filePath = Paths.get("upload", fileInfo.getSaveFolder(), fileInfo.getSaveFile());
            File file = filePath.toFile();

            // 파일이 존재하지 않을 경우 처리
            if (!file.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("파일이 서버에 존재하지 않습니다.");
            }

            // 파일 다운로드를 위한 응답 구성
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileInfo.getOriginalFile() + "\"");
            headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(filePath));

            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 읽기 중 오류가 발생했습니다.");
        }
    }
}
