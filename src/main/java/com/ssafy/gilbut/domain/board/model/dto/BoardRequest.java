package com.ssafy.gilbut.domain.board.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Schema(title = "게시글 요청 관련", description = "게시글 요청에 필요한 정보를 나타낸다.")
public class BoardRequest {

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "", description = "")
    public static class CreateDTO {
        private Integer id;
        private String title;
        private Integer travelId;
        private String content;
        List<FileCreateDTO> fileInfos = new ArrayList<>();
    }


    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "", description = "")
    public static class UpdateDTO {
        private String title;
        private Integer travelId;
        private String content;
        List<FileCreateDTO> fileInfos = new ArrayList<>();
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "", description = "")
    public static class FileCreateDTO {
        @Schema(description = "저장폴더")
        private String saveFolder;
        private Long boardId;

        @Schema(description = "원본 파일이름")
        private String originalFile;
        @Schema(description = "저장 파일이름")
        private String saveFile;
    }
}
