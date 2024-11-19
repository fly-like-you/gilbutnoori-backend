package com.ssafy.gilbut.advice;

import com.ssafy.gilbut.advice.code.BaseErrorCode;
import com.ssafy.gilbut.advice.status.ErrorStatus;
import com.ssafy.gilbut.exception.handler.TempHandler;
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

	@ExceptionHandler(TempHandler.class)
	public ResponseEntity<?> handleTempHandlerException(TempHandler t, WebRequest request) {
//		log.info(t.getMessage());
		return handleExceptionInternalConstraint(t, t.getCode(), HttpHeaders.EMPTY, request);
	}
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> handleAllException(Exception e, WebRequest request) {
//		log.error("", e);
//
//		return handleExceptionInternalConstraint(e, ErrorStatus._INTERNAL_SERVER_ERROR, HttpHeaders.EMPTY, request);
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
	private ResponseEntity<Object> handleExceptionInternalConstraint(Exception e, BaseErrorCode baseErrorCode,
																	 HttpHeaders headers, WebRequest request) {
		return super.handleExceptionInternal(
				e,
				baseErrorCode.getReason(),
				headers,
				baseErrorCode.getReason().getHttpStatus(),
				request
		);
	}
}
