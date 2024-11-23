package com.ssafy.gilbut.domain.board.service;


import static com.ssafy.gilbut.domain.board.model.dto.BoardRequest.UpdateDTO;
import static com.ssafy.gilbut.domain.board.model.dto.BoardResponse.DetailResultDTO;

import com.ssafy.gilbut.domain.board.model.dto.BoardRequest.CreateDTO;
import com.ssafy.gilbut.domain.board.model.dto.BoardResponse.SimplePageResultDTO;
import org.springframework.data.domain.Pageable;

public interface BoardService {

	SimplePageResultDTO listArticle(Pageable pageable);
	DetailResultDTO getArticle(Long boardId);
	DetailResultDTO getModifyArticle(String accessToken, Long boardId);
	void writeArticle(String accessToken, CreateDTO boardDto);
	void modifyArticle(String accessToken, Long boardId, UpdateDTO boardDto);
	void updateHit(Long boardId);

	void deleteArticle(String accessToken, Long boardId);
}
