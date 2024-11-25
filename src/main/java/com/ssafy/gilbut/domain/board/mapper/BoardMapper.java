package com.ssafy.gilbut.domain.board.mapper;

import com.ssafy.gilbut.domain.board.model.dto.BoardRequest;
import com.ssafy.gilbut.domain.board.model.dto.FileInfoDto;
import com.ssafy.gilbut.domain.board.model.entity.Board;
import com.ssafy.gilbut.domain.board.model.entity.FileInfo;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Mapper
public interface BoardMapper {

    List<Board> listArticle(@Param("pageable") Pageable pageable, @Param("sort") Sort sort);

    Optional<Board> getArticle(@Param("boardId") Long boardId);

    void updateHit(@Param("boardId") Long boardId);

    void writeArticle(@Param("userId") Long userId, @Param("dto") BoardRequest.CreateDTO boardDto);

    void modifyArticle(@Param("boardId") Long boardId, @Param("dto") BoardRequest.UpdateDTO boardDto);

    void deleteFile(@Param("boardId") Long boardId);


    int getTotalArticleCount(@Param("pageable") Pageable pageable);

    void registerFiles(@Param("dto") List<BoardRequest.FileCreateDTO> boardDto);
    void registerFile(@Param("dto") BoardRequest.FileCreateDTO fileCreateDTO);

    void deleteArticle(@Param("boardId") Long boardId);

    FileInfo findFileByFileId(@Param("fileId") Long fileId);
}
