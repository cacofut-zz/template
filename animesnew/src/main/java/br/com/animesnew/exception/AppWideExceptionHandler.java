package br.com.animesnew.exception;

import java.nio.file.AccessDeniedException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppWideExceptionHandler {

	@ExceptionHandler(DuplicateSpittleException.class)
	public String handleNotFound1() {
		return "error/duplicate";
	}


	@ExceptionHandler(Exception.class)
	public String handleNotFound() {
		return "error/erro";
	}

}
