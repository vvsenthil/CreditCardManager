package com.sapient.card.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sapient.card.entity.CreditCard;
import com.sapient.card.exception.CreditCardException;
import com.sapient.card.model.CreditCardRequest;
import com.sapient.card.model.CreditCardResponse;
import com.sapient.card.repository.CreditCardRepository;
import com.sapient.card.service.CreditCardService;

@Service
public class CreditCardServiceImpl implements CreditCardService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CreditCardRepository cardRepository;

	/*
	 * To Add Credit Card details into the database  
	 */
	@Override
	public List<CreditCardResponse> addCreditCardDetails(CreditCardRequest cardDetails) throws CreditCardException {
		logger.debug("Called CreditCardServiceImpl.addCreditCardDetails ");
		Optional<CreditCard> cardDetail = Optional.ofNullable(cardRepository.findByNumber(cardDetails.getNumber()));
		if (cardDetail.isPresent()) {
			logger.error("Entered Card Number is Already present in the Database !");
			throw new CreditCardException("A Credit Card with that number already exists!",
					HttpStatus.CONFLICT);
			//the below code is to add balance 
			
			/*int newBalance = cardDetail.get().getBalance() + cardDetails.getBalance();
			if (cardDetail.get().getLimit() >= newBalance) {
				cardRepository.updateCreditCardUsingQueryAnnotation(newBalance, cardDetails.getNumber());
			} else {
				throw new CreditCardException("You are Trying to use your credit more than your allowed credit Limit",
						HttpStatus.BAD_REQUEST);
			}
			 */
		} else {
			cardRepository
					.save(new CreditCard(cardDetails.getNumber(), cardDetails.getName(), cardDetails.getLimit(), 0));
		}
		List<CreditCardResponse> creditCardDetails = getCreditCardDetails();
		return creditCardDetails;
	}

	/*
	 * To Retrieve all available Card details from database  
	 */
	@Override
	public List<CreditCardResponse> getCreditCardDetails() {
		logger.debug("Called CreditCardServiceImpl.getCreditCardDetails() ");
		List<CreditCard> cardData = cardRepository.findAll();
		List<CreditCardResponse> finalDetails = new ArrayList<>();
		cardData.stream().forEach(x -> {
			CreditCardResponse cardDetail = new CreditCardResponse();
			cardDetail.setBalance(x.getBalance());
			cardDetail.setName(x.getName());
			cardDetail.setLimit(x.getLimit());
			cardDetail.setNumber(x.getNumber());
			finalDetails.add(cardDetail);
		});
		return finalDetails;
	}

	/*
	 * To delete Card details from the database  
	 */
	@Override
	public void removeCards() {
		logger.debug("Called CreditCardServiceImpl.removeCards() ");
		cardRepository.deleteAll();
	}
}
