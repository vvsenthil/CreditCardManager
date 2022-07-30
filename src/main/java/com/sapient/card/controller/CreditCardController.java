package com.sapient.card.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.card.exception.CreditCardException;
import com.sapient.card.model.CreditCardRequest;
import com.sapient.card.model.CreditCardResponse;
import com.sapient.card.service.CreditCardService;

@RestController
public class CreditCardController {

	@Autowired
	private CreditCardService cardService;

	@GetMapping(value = "/creditCard/test")
	public String testApplication() {
		return "Hello Application started Successfully !!!";
	}

	@PostMapping(value = "/creditCard/add", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> add(@RequestBody CreditCardRequest cardDetails) throws CreditCardException {
		List<CreditCardResponse> creditCardDetails = new ArrayList<>();
		if (cardDetails.checkLuhn10()) {
			creditCardDetails = cardService.addCreditCardDetails(cardDetails);
		} else {
			throw new CreditCardException("Card Number is invalid!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(creditCardDetails, HttpStatus.OK);
	}

	@GetMapping("/creditCard/getAll")
	public ResponseEntity<?> getAll() {
		List<CreditCardResponse> creditCardDetails = cardService.getCreditCardDetails();
		return new ResponseEntity<Object>(creditCardDetails, HttpStatus.OK);
	}

}
