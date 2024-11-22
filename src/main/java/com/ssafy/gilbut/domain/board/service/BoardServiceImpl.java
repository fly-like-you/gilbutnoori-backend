package com.ssafy.gilbut.domain.board.service;

import com.ssafy.gilbut.domain.board.converter.BoardConverter;
import com.ssafy.gilbut.domain.board.mapper.BoardMapper;
import com.ssafy.gilbut.domain.board.model.dto.BoardRequest;
import com.ssafy.gilbut.domain.board.model.dto.BoardResponse;
import com.ssafy.gilbut.domain.board.model.entity.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;

	@Override
	public BoardResponse.SimplePageResultDTO listArticle(Pageable pageable) {

		List<Board> list = boardMapper.listArticle(pageable);
		int totalCount = boardMapper.getTotalArticleCount(pageable);

		return BoardConverter.toSimplePageResultDTO(list, pageable, totalCount);
	}

	@Override
	public BoardResponse.DetailResultDTO getArticle(Integer boardId) {
		log.info("getArticle -> {}", boardId);

		Board article = boardMapper.getArticle(boardId);
		log.debug("article: {}", article);

		return BoardConverter.toDetailResultDTO(article);
	}

	@Override
	public void updateHit(Integer boardId) {
		boardMapper.updateHit(boardId);
	}


	@Override
	@Transactional
	public void writeArticle(BoardRequest.CreateDTO boardDto) {
		boardMapper.writeArticle(boardDto);
		List<BoardRequest.FileCreateDTO> files = boardDto.getFileInfos();
		if (files != null && !files.isEmpty()) {
			boardMapper.registerFile(files);
		}
	}

	@Override
	public void modifyArticle(BoardRequest.UpdateDTO boardDto){
		boardMapper.modifyArticle(boardDto);
	}

	@Override
	public void deleteArticle(Integer boardId) {
		boardMapper.deleteArticle(boardId);
	}

}
