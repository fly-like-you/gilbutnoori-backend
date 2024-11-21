package com.ssafy.gilbut.advice;

import com.ssafy.gilbut.advice.code.ErrorReasonDTO;
import com.ssafy.gilbut.advice.status.ErrorStatus;
import com.ssafy.gilbut.exception.handler.GeneralExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(GeneralExceptionHandler.class)
	public ResponseEntity<?> handleTempHandlerException(GeneralExceptionHandler t) {
		ErrorReasonDTO reason = t.getCode().getReason();
		log.error(reason.toString());

		return ResponseEntity.status(t.getCode().getReasonHttpStatus().getHttpStatus())
				.body(ApiResponse.onFailure(reason.getCode(), reason.getMessage(), null));
	}

	@ExceptionHandler(SQLException.class)
	public ResponseEntity<?> handleSQLException(SQLException e) {
		log.error("SQLException Handler: {}", e.getMessage());

		ErrorReasonDTO reason = switch (e.getErrorCode()) {
			// Duplicate Key Violations
			case 1062 -> {
				log.error("중복된 데이터 입력 시도: {}", e.getMessage());
				yield ErrorStatus.DATABASE_DUPLICATE_RESOURCE.getReason();
			}

			// Default case for unhandled SQL errors
			default -> {
				log.error("처리되지 않은 SQL 예외 발생. Error Code: {}, Message: {}",
						e.getErrorCode(), e.getMessage());
				yield ErrorStatus.DATABASE_INTERNAL_SERVER_ERROR.getReason();
			}
		};

		return ResponseEntity
				.status(reason.getHttpStatus())
				.body(ApiResponse.onFailure(
						reason.getCode(),
						reason.getMessage(),
						Map.of("sqlErrorCode", String.valueOf(e.getErrorCode()))
				));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleAllException(Exception e, WebRequest request) {
		log.error("Exception Handler: {}", e.getMessage());
		e.printStackTrace();
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ApiResponse.onFailure(
						ErrorStatus._INTERNAL_SERVER_ERROR.getCode(),
						e.getMessage(),
						null
				));

	}

}
