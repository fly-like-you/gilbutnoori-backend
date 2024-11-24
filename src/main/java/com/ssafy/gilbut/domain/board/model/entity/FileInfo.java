package com.ssafy.gilbut.domain.board.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "FileInfo : 업로드 파일정보", description = "게시글에 업로드한 파일의 정보를 나타낸다.")
public class FileInfo {
    private Long id;
    @Schema(description = "저장폴더")
    private String saveFolder;
    private Long boardId;
    @Schema(description = "원본 파일이름")
    private String originalFile;
    @Schema(description = "저장 파일이름")
    private String saveFile;
}
