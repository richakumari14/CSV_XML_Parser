/**
 * 
 */
package com.practice.demo.controller.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Richa
 * 
 * Controller advice class to manage exceptions at single point
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(InvalidInputFileException.class)
	public ResponseEntity<ErrorResponse> handleUploadException(Exception ex , WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorType(ex.getMessage());
		errorResponse.setTimestamp(LocalDateTime.now().toString());
		return new ResponseEntity(errorResponse, HttpStatus.NOT_ACCEPTABLE);
		
	}

}
