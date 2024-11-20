package com.ssafy.gilbut.advice;

import com.ssafy.gilbut.advice.code.ErrorReasonDTO;
import com.ssafy.gilbut.exception.handler.TempHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(TempHandler.class)
	public ResponseEntity<?> handleTempHandlerException(TempHandler t) {
		ErrorReasonDTO reason = t.getCode().getReason();
		log.error(reason.toString());

		return ResponseEntity.status(t.getCode().getReasonHttpStatus().getHttpStatus())
				.body(ApiResponse.onFailure(reason.getCode(), reason.getMessage(), null));

	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleAllException(Exception e, WebRequest request) {
		log.error("", e);

//		return handleExceptionInternalConstraint(e, ErrorStatus._INTERNAL_SERVER_ERROR, HttpHeaders.EMPTY, request);
		return null;
	}

}
