package com.ssafy.gilbut.domain.board.service;


import static com.ssafy.gilbut.domain.board.model.dto.BoardRequest.UpdateDTO;
import static com.ssafy.gilbut.domain.board.model.dto.BoardResponse.DetailResultDTO;

import com.ssafy.gilbut.domain.board.model.dto.BoardRequest.CreateDTO;
import com.ssafy.gilbut.domain.board.model.dto.BoardResponse.SimplePageResultDTO;
import org.springframework.data.domain.Pageable;

public interface BoardService {

	SimplePageResultDTO listArticle(Pageable pageable);
	DetailResultDTO getArticle(Integer boardId);
	DetailResultDTO getModifyArticle(String accessToken, Integer boardId);
	void writeArticle(String accessToken, CreateDTO boardDto);
	void modifyArticle(String accessToken, Integer boardId, UpdateDTO boardDto);
	void updateHit(Integer boardId);

	void deleteArticle(String accessToken, Integer boardId);
}
