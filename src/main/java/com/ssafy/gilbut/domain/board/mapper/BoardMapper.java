package com.ssafy.gilbut.domain.board.mapper;

import com.ssafy.gilbut.domain.board.model.dto.BoardRequest;
import com.ssafy.gilbut.domain.board.model.dto.FileInfoDto;
import com.ssafy.gilbut.domain.board.model.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<Board> listArticle(@Param("pageable") Pageable pageable);

    List<FileInfoDto> fileInfoList(@Param("boardId") int boardId);

    Board getArticle(@Param("boardId") int boardId);

    int getTotalArticleCount(@Param("pageable") Pageable pageable);

    void registerFile(@Param("dto") List<BoardRequest.FileCreateDTO> boardDto);

    void writeArticle(@Param("dto") BoardRequest.CreateDTO boardDto);

    void updateHit(@Param("boardId") int boardId);

    void modifyArticle(@Param("dto") BoardRequest.UpdateDTO boardDto);

    void deleteFile(@Param("boardId") int boardId);

    void deleteArticle(@Param("boardId") int boardId);

}
