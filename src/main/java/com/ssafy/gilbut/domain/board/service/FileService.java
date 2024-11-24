package com.ssafy.gilbut.domain.board.service;

import com.ssafy.gilbut.domain.board.mapper.BoardMapper;
import com.ssafy.gilbut.domain.board.model.dto.BoardRequest.FileCreateDTO;
import com.ssafy.gilbut.domain.board.model.entity.FileInfo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {
    private final BoardMapper boardMapper;
    private final String uploadPath = "upload/";

    // 다중 파일 저장 메소드
    public void saveFiles(List<MultipartFile> files, Long boardId) throws IOException {
        for (MultipartFile file : files) {
            if (file.isEmpty()) throw new IllegalArgumentException("파일이 비어있습니다.");
            saveFile(file, boardId);
        }
    }

    // 단일 파일 저장 메소드
    public void saveFile(MultipartFile file, Long boardId) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어있습니다.");
        }

        // 파일 확장자 확인
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();

        // 허용된 파일 확장자 검사 (필요한 확장자 추가 가능)
        if (!Arrays.asList(".jpg", ".jpeg", ".png")
                .contains(fileExtension)) {
            throw new IllegalArgumentException("지원하지 않는 파일 형식입니다.");
        }

        // 현재 날짜로 폴더 생성
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String saveFolder = uploadPath + File.separator + today;

        File folder = new File(saveFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // UUID를 사용하여 파일명 생성
        String saveFilename = UUID.randomUUID().toString() + fileExtension;

        // 파일 크기 제한 체크 (예: 10MB)
        long maxSize = 10 * 1024 * 1024;
        if (file.getSize() > maxSize) {
            throw new IllegalArgumentException("파일 크기가 제한을 초과했습니다.");
        }

        // 파일 저장
        File saveFile = new File(folder, saveFilename);
        Files.copy(file.getInputStream(), saveFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        // DB에 파일 정보 저장
        FileCreateDTO fileInfo = FileCreateDTO.builder()
                .boardId(boardId)
                .saveFolder(today)
                .originalFile(originalFilename)
                .saveFile(saveFilename)
                .build();

        boardMapper.registerFile(fileInfo);

    }

}