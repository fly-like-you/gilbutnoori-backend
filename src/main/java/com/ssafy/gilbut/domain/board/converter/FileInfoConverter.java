package com.ssafy.gilbut.domain.board.converter;

import com.ssafy.gilbut.domain.board.model.dto.FileInfoDto;
import com.ssafy.gilbut.domain.board.model.entity.FileInfo;
import java.util.List;

public class FileInfoConverter {

    public static FileInfoDto toFileInfoDto(FileInfo fileInfo) {

        return FileInfoDto.builder()
                .id(fileInfo.getId())
                .saveFile(fileInfo.getSaveFile())
                .originalFile(fileInfo.getOriginalFile())
                .saveFolder(fileInfo.getSaveFolder())
                .build();
    }
}
