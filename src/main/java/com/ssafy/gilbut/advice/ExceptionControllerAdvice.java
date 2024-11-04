package com.ssafy.gilbut.advice;

import com.ssafy.gilbut.advice.status.ErrorStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleAllException(Exception e, WebRequest request) {
		log.error("핸들러 호출");
		String errorMessage = e.getMessage();
		log.error(errorMessage);
		return handleExceptionInternalConstraint(e, ErrorStatus._INTERNAL_SERVER_ERROR, HttpHeaders.EMPTY,request);
	}

//	@ExceptionHandler(NoHandlerFoundException.class)
//	@ResponseStatus(value = HttpStatus.NOT_FOUND)
//	public ResponseEntity<?> handle404(NoHandlerFoundException ex, Model model) {
//		log.error("404 발생 : {}", "404 page not found");
//		model.addAttribute("msg", "해당 페이지를 찾을 수 없습니다!!!");
//		return null;
//	}


	private ResponseEntity<Object> handleExceptionInternalConstraint(Exception e, ErrorStatus errorCommonStatus,
																	 HttpHeaders headers, WebRequest request) {
		ApiResponse<Object> body = ApiResponse.onFailure(errorCommonStatus.getCode(), errorCommonStatus.getMessage(), null);
		return super.handleExceptionInternal(
				e,
				body,
				headers,
				errorCommonStatus.getHttpStatus(),
				request
		);
	}

}
