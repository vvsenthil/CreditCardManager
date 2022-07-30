package com.sapient.card.service;

import java.util.List;

import com.sapient.card.exception.CreditCardException;
import com.sapient.card.model.CreditCardRequest;
import com.sapient.card.model.CreditCardResponse;


public interface CreditCardService {
	
	public List<CreditCardResponse> addCreditCardDetails(CreditCardRequest cardDetails) throws CreditCardException;
	public List<CreditCardResponse> getCreditCardDetails();
}
