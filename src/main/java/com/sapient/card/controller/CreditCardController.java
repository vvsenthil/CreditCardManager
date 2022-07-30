package com.sapient.card.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.sapient.card.exception.CreditCardException;
import com.sapient.card.model.CreditCardRequest;
import com.sapient.card.model.CreditCardResponse;
import com.sapient.card.service.CreditCardService;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class CreditCardController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CreditCardService cardService;

	/*
	 * To test the application  
	 */
	@GetMapping(value = "/creditCard/test")
	public String testApplication() {
		logger.debug("Called CreditCardController.testApplication ");
		return "Hello Application started Successfully !!!";
	}
	
	/*
	 * To insert a card details into Database   
	 */
	@PostMapping(value = "/creditCard/add", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> add(@RequestBody CreditCardRequest cardDetails) throws CreditCardException {
		logger.debug("Called CreditCardController.add() with the card Details ");
		List<CreditCardResponse> creditCardDetails = new ArrayList<>();
		if (cardDetails.checkLuhn10()) {
			creditCardDetails = cardService.addCreditCardDetails(cardDetails);
		} else {
			logger.error("Entered Card Number is invalid !");
			throw new CreditCardException("Card Number is invalid!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(creditCardDetails, HttpStatus.OK);
	}
	
	/*
	 * To retrieve all available credit Card Details 
	 */
	@GetMapping("/creditCard/getAll")
	public ResponseEntity<?> getAll() {
		logger.debug("Called CreditCardController.getAll() ");
		List<CreditCardResponse> creditCardDetails = cardService.getCreditCardDetails();
		return new ResponseEntity<Object>(creditCardDetails, HttpStatus.OK);
	}

}
