package com.ssafy.gilbut.domain.board.controller;

import com.ssafy.gilbut.advice.ApiResponse;
import com.ssafy.gilbut.domain.board.model.dto.BoardRequest;
import com.ssafy.gilbut.domain.board.model.dto.BoardResponse;
import com.ssafy.gilbut.domain.board.model.dto.BoardResponse.DetailResultDTO;
import com.ssafy.gilbut.domain.board.model.dto.BoardResponse.SimpleResultDTO;
import com.ssafy.gilbut.domain.board.service.BoardService;
import com.ssafy.gilbut.util.SizeConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

	private final BoardService boardService;

	@GetMapping
	public ResponseEntity<?> listArticle(
		@PageableDefault(size = SizeConstant.LIST_SIZE) Pageable page
	) {
		log.info("listArticle");
		BoardResponse.SimplePageResultDTO boardListDto = boardService.listArticle(page);

		return ResponseEntity.ok().body(ApiResponse.onSuccess(boardListDto));

	}

	@GetMapping("/{boardId}")
	public ResponseEntity<?> getArticle(
			@PathVariable("boardId") Long boardId
	) {
		log.info("getArticle - 호출 : " + boardId);
		boardService.updateHit(boardId);
		DetailResultDTO article = boardService.getArticle(boardId);
		return ResponseEntity.ok().body(ApiResponse.onSuccess(article));
	}

	@GetMapping("/modify/{boardId}")
	public ResponseEntity<?> getModifyArticle(
			@RequestHeader("Authorization") String accessToken,
			@PathVariable("boardId") Long boardId
	) {

		log.info("getModifyArticle - 호출 : " + boardId);
		BoardResponse.DetailResultDTO result = boardService.getModifyArticle(accessToken, boardId);

		return ResponseEntity.ok().body(ApiResponse.onSuccess(result));
	}

	@PostMapping
	public ResponseEntity<?> writeArticle(
			@RequestHeader("Authorization") String accessToken,
			@RequestBody BoardRequest.CreateDTO boardDto
	) {
		log.info("writeArticle boardDto - {}", boardDto);
		SimpleResultDTO simpleResultDTO = boardService.writeArticle(accessToken, boardDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(simpleResultDTO));

	}

	@PutMapping("/modify/{boardId}")
	public ResponseEntity<?> modifyArticle(
			@RequestHeader("Authorization") String accessToken,
			@PathVariable("boardId") Long boardId,
			@RequestBody BoardRequest.UpdateDTO boardDto) {
		log.info("modifyArticle - 호출 {}", boardDto);

		boardService.modifyArticle(accessToken, boardId, boardDto);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{boardId}")
	public ResponseEntity<?> deleteArticle(
			@RequestHeader("Authorization") String accessToken,
			@PathVariable("boardId") Long boardId
	) {
		log.info("deleteArticle - 호출");
		boardService.deleteArticle(accessToken, boardId);
		return ResponseEntity.ok().build();
	}


}