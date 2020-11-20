package com.practice.demo.controller.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Exception class for uploading csv file
 * @author GL63
 *
 */

@Getter
@Setter
public class InvalidInputFileException extends Exception {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private final String message;

	/**
	 * Default constructor.
	 * 
	 * @param message exception message
	 */
	public InvalidInputFileException(final String message) {
		super(message);
		this.message = message;
	}
			
	

}
