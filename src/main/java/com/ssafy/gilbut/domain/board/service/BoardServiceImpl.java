package com.ssafy.gilbut.domain.board.service;

import com.ssafy.gilbut.advice.status.ErrorStatus;
import com.ssafy.gilbut.domain.board.converter.BoardConverter;
import com.ssafy.gilbut.domain.board.mapper.BoardMapper;
import com.ssafy.gilbut.domain.board.model.dto.BoardRequest;
import com.ssafy.gilbut.domain.board.model.dto.BoardRequest.CreateDTO;
import com.ssafy.gilbut.domain.board.model.dto.BoardRequest.UpdateDTO;
import com.ssafy.gilbut.domain.board.model.dto.BoardResponse;
import com.ssafy.gilbut.domain.board.model.dto.BoardResponse.DetailResultDTO;
import com.ssafy.gilbut.domain.board.model.entity.Board;
import com.ssafy.gilbut.exception.handler.GeneralExceptionHandler;
import com.ssafy.gilbut.util.JWTUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;
	private final JWTUtil jwtUtil;

	@Override
	public BoardResponse.SimplePageResultDTO listArticle(Pageable pageable) {

		List<Board> list = boardMapper.listArticle(pageable);
		int totalCount = boardMapper.getTotalArticleCount(pageable);

		return BoardConverter.toSimplePageResultDTO(list, pageable, totalCount);
	}

	@Override
	public DetailResultDTO getArticle(Long boardId) {
		log.info("getArticle -> {}", boardId);

		Board article = getSafeBoardArticleByBoardId(boardId);
		log.debug("article: {}", article);

		return BoardConverter.toDetailResultDTO(article);
	}

	@Override
	public DetailResultDTO getModifyArticle(String accessToken, Long boardId) {
		validateBoardOwnerMatch(accessToken, boardId);

		return getArticle(boardId);
	}

	@Override
	public void updateHit(Long boardId) {
		boardMapper.updateHit(boardId);
	}


	@Override
	@Transactional
	public void writeArticle(String accessToken, CreateDTO boardDto) {
		Long userId = jwtUtil.getUserId(accessToken);
		boardMapper.writeArticle(userId, boardDto);

		List<BoardRequest.FileCreateDTO> files = boardDto.getFileInfos();
		if (files != null && !files.isEmpty()) {
			boardMapper.registerFiles(files);
		}

		log.info("게시글 작성 성공!");
	}

	@Override
	public void modifyArticle(String accessToken, Long boardId, UpdateDTO boardDto){
		validateBoardOwnerMatch(accessToken, boardId);

		boardMapper.modifyArticle(boardId, boardDto);
		log.info("게시글 수정 성공!");
	}


	@Override
	public void deleteArticle(String accessToken, Long boardId) {
		validateBoardOwnerMatch(accessToken, boardId);
		boardMapper.deleteArticle(boardId);

		log.info("게시글 삭제 성공!");
	}



	private Board getSafeBoardArticleByBoardId(Long boardId) {
		return boardMapper.getArticle(boardId).orElseThrow(
				() -> new GeneralExceptionHandler(ErrorStatus.BOARD_NOT_FOUND)
		);
	}

	private void validateBoardOwnerMatch(String accessToken, Long boardId) {
		Long userId = jwtUtil.getUserId(accessToken);
		Board board = getSafeBoardArticleByBoardId(boardId);

		if (!userId.equals(board.getUser().getId())) throw new GeneralExceptionHandler(ErrorStatus.BOARD_OWNER_NOT_MATCHED);
	}

}
