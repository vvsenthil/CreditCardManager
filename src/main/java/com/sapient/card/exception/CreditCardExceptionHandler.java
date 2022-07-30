package com.sapient.card.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CreditCardExceptionHandler {
	@ExceptionHandler(value = { CreditCardException.class })
//	@ResponseBody
	//@ResponseStatus
	public ResponseEntity<Object> handleCardException(CreditCardException e) {
		System.out.println("e.getMessage()---------------------------->"+e.getMessage());
		CreditCardException creditCardException = new CreditCardException(e.getMessage(), e.getHttpStatus());
		return new ResponseEntity<>(creditCardException, e.getHttpStatus());
	}

}
