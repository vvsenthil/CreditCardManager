package com.sapient.card.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CreditCardExceptionHandler {
	@ExceptionHandler(value = { CreditCardException.class })
	public ResponseEntity<Object> handleCardException(CreditCardException e) {
		CreditCardException creditCardException = new CreditCardException(e.getMessage(), e.getHttpStatus());
		return new ResponseEntity<>(creditCardException, e.getHttpStatus());
	}

}
