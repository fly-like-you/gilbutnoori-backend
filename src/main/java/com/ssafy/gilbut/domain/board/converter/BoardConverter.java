package com.ssafy.gilbut.domain.board.converter;

import com.ssafy.gilbut.domain.board.model.dto.BoardResponse;
import com.ssafy.gilbut.domain.board.model.entity.Board;
import com.ssafy.gilbut.domain.comment.converter.CommentConverter;
import com.ssafy.gilbut.domain.comment.model.dto.CommentResponse.DetailListResultDTO;
import java.util.List;
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
        return BoardResponse.SimpleResultDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .loginId(board.getUser().getLoginId())
                .nickname(board.getUser().getNickname())
                .hit(board.getHit())
                .created_at(board.getCreated_at())
                .build();
    }

    public static BoardResponse.DetailResultDTO toDetailResultDTO(Board board) {
        DetailListResultDTO comments = DetailListResultDTO.builder()
                .comments(board.getComments()
                        .stream()
                        .map((CommentConverter::toDetailResultDTO))
                        .toList()
                )
                .build();

        return BoardResponse.DetailResultDTO.builder()
                .id(board.getId())
                .loginId(board.getUser().getLoginId())
                .nickname(board.getUser().getNickname())
                .title(board.getTitle())
                .comments(comments)
                .travel(null)
                .content(board.getContent())
                .hit(board.getHit())
                .created_at(board.getCreated_at())
                .fileInfos(null)
                .build();
    }
}
