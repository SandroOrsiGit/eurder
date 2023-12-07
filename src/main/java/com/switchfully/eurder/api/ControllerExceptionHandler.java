package com.switchfully.eurder.api;

import com.switchfully.eurder.exceptions.IdNotFoundException;
import com.switchfully.eurder.exceptions.StockTooLowException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(IdNotFoundException.class)
	protected void idNotFoundException(IdNotFoundException ex, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

	@ExceptionHandler(StockTooLowException.class)
	protected void stockTooLowException(StockTooLowException ex, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}
}
