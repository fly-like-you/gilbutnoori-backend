package com.ssafy.gilbut.domain.board.converter;

import com.ssafy.gilbut.domain.attraction.converter.AttractionConverter;
import com.ssafy.gilbut.domain.board.model.dto.BoardResponse;
import com.ssafy.gilbut.domain.board.model.dto.FileInfoDto;
import com.ssafy.gilbut.domain.board.model.entity.Board;
import com.ssafy.gilbut.domain.board.model.entity.FileInfo;
import com.ssafy.gilbut.domain.comment.converter.CommentConverter;
import com.ssafy.gilbut.domain.comment.model.dto.CommentResponse.DetailListResultDTO;
import com.ssafy.gilbut.domain.travel.converter.TravelConverter;
import com.ssafy.gilbut.domain.travel.model.dto.TravelResponse;
import com.ssafy.gilbut.domain.travel.model.dto.TravelResponse.DetailResultDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class BoardConverter {

    public static BoardResponse.SimplePageResultDTO toSimplePageResultDTO (List<Board> articles, Pageable pageable, Integer totalCount) {
        List<BoardResponse.SimpleResultDTO> articleDTOList = articles.stream().map(BoardConverter::toSimpleResultDTO).toList();
        PageImpl<BoardResponse.SimpleResultDTO> page = new PageImpl<>(articleDTOList, pageable, totalCount);

        return BoardResponse.SimplePageResultDTO.builder()
                .articles(articleDTOList)
                .listSize(articles.size())
                .isFirstPage(page.isFirst())
                .isLastPage(page.isLast())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }

    public static BoardResponse.SimpleResultDTO toSimpleResultDTO(Board board) {
        List<FileInfoDto> files = board.getFileInfos().stream().map(FileInfoConverter::toFileInfoDto).toList();

        return BoardResponse.SimpleResultDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .loginId(board.getUser().getLoginId())
                .nickname(board.getUser().getNickname())
                .hit(board.getHit())
                .fileInfo(files)
                .created_at(board.getCreated_at())
                .build();
    }

    public static BoardResponse.DetailResultDTO toDetailResultDTO(Board board) {
        DetailListResultDTO commentDTOs = Optional.ofNullable(board.getComments())
                .map(CommentConverter::toDetailListResultDTO)
                .orElse(null);

        List<FileInfoDto> fileDTOs = board.getFileInfos()
                .stream()
                .map(FileInfoConverter::toFileInfoDto)
                .toList();

        DetailResultDTO travelDTO = Optional.ofNullable(board.getTravel())
                .map(TravelConverter::toDetailResultDTO)
                .orElse(null);

        return BoardResponse.DetailResultDTO.builder()
                .id(board.getId())
                .loginId(board.getUser().getLoginId())
                .nickname(board.getUser().getNickname())
                .title(board.getTitle())
                .comments(commentDTOs)
                .travel(travelDTO)
                .content(board.getContent())
                .hit(board.getHit())
                .created_at(board.getCreated_at())
                .fileInfos(fileDTOs)
                .build();
    }
}
