package com.ssafy.gilbut.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception ex, Model model) {
		log.error("Exception 발생 : {}", ex.getMessage());
		ex.printStackTrace();
		model.addAttribute("msg", "처리중 에러 발생!!!");
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<?> handle404(NoHandlerFoundException ex, Model model) {
		log.error("404 발생 : {}", "404 page not found");
		model.addAttribute("msg", "해당 페이지를 찾을 수 없습니다!!!");
		return null;
	}

}
