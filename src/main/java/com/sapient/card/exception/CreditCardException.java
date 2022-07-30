package com.sapient.card.exception;

import org.springframework.http.HttpStatus;

public class CreditCardException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	private HttpStatus httpStatus;

	public CreditCardException() {
		super();
	}
	
	public CreditCardException(String msg, HttpStatus status) {
		super(msg,null,false,false);
		this.message = msg;
		this.httpStatus = status;
	}

	public String getMessage() {
		return message;
	}


	public HttpStatus getHttpStatus() {
		return httpStatus;
	}


}
