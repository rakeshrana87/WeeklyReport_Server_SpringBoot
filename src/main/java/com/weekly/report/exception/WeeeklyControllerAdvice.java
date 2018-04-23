package com.weekly.report.exception;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

import com.weekly.report.pojo.WeeklyError;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class WeeeklyControllerAdvice {
	
	@ExceptionHandler(SAXException.class)
	public ResponseEntity<WeeklyError> notFoundException(final SAXException e) {
		return error(e, HttpStatus.NOT_FOUND, e.getMessage());
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<WeeklyError> assertionException(final IOException io) {
		return error(io, HttpStatus.NOT_FOUND, io.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<WeeklyError> assertionException(final Exception ex) {
		return error(ex, HttpStatus.NOT_FOUND, ex.getMessage());
	}
	
	private ResponseEntity<WeeklyError> error(final Exception exception, final HttpStatus httpStatus,
			final String logRef) {
		final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
		return new ResponseEntity<WeeklyError>(new WeeklyError(logRef, HttpStatus.INTERNAL_SERVER_ERROR.toString(),message), httpStatus);
	}
}
