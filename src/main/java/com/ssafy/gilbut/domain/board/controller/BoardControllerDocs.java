package com.ssafy.gilbut.domain.board.controller;

import com.ssafy.gilbut.domain.board.model.dto.BoardRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@Tag(name = "게시판 컨트롤러", description = "게시판에 글을 등록, 수정, 삭제, 목록, 상세보기등 전반적인 처리를 하는 클래스.")
public interface BoardControllerDocs {

    /**
     *
     * @param boardDto
     * @return
     */
    @Operation(summary = "게시판 글작성", description = "새로운 게시글 정보를 입력한다.")
    ResponseEntity<?> writeArticle(
            @Parameter(description = "작성글 정보.", required = true) BoardRequest.CreateDTO boardDto);

    /**
     *
     * @param page
     * @return
     */
    @Operation(summary = "게시판 글목록", description = "모든 게시글의 정보를 반환한다.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "게시글목록 OK!!"),
            @ApiResponse(responseCode = "404", description = "페이지없어!!"),
            @ApiResponse(responseCode = "500", description = "서버에러!!") })
    ResponseEntity<?> listArticle(Pageable page);

    /**
     *
     * @param boardId
     * @return
     * @throws Exception
     */
    @Operation(summary = "게시판 글보기", description = "글번호에 해당하는 게시글의 정보를 반환한다.")
    ResponseEntity<?> getArticle(
            @Parameter(name = "boardId", description = "얻어올 글의 글번호.", required = true) int boardId
    );


    @Operation(summary = "수정 할 글 얻기", description = "글번호에 해당하는 게시글의 정보를 반환한다.")
    ResponseEntity<?> getModifyArticle(
            @Parameter(name = "articleno", description = "얻어올 글의 글번호.", required = true) int articleno)
            throws Exception;

    @Operation(summary = "게시판 글수정", description = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.")
    ResponseEntity<?> modifyArticle(
            @Parameter(description = "수정할 글정보.", required = true) BoardRequest.UpdateDTO boardDto);

    @Operation(summary = "게시판 글삭제", description = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.")
    ResponseEntity<?> deleteArticle(
            @Parameter(
                    name = "articleno",
                    description = "살제할 글의 글번호.", required = true
            ) int boardId
    );


}
