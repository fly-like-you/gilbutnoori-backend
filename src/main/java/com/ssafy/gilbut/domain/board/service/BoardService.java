package com.ssafy.gilbut.domain.board.service;


import com.ssafy.gilbut.domain.board.model.dto.BoardRequest.CreateDTO;
import com.ssafy.gilbut.domain.board.model.dto.BoardResponse.SimplePageResultDTO;
import org.springframework.data.domain.Pageable;

import static com.ssafy.gilbut.domain.board.model.dto.BoardRequest.UpdateDTO;
import static com.ssafy.gilbut.domain.board.model.dto.BoardResponse.DetailResultDTO;

public interface BoardService {

	SimplePageResultDTO listArticle(Pageable pageable);
	DetailResultDTO getArticle(Integer boardId);

	void writeArticle(CreateDTO boardDto);
	void modifyArticle(UpdateDTO boardDto);
	void updateHit(Integer boardId);
	void deleteArticle(Integer boardId);
	
}
