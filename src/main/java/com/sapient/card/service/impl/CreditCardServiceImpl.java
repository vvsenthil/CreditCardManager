package com.sapient.card.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Autowired
	private CreditCardRepository cardRepository;

	@Override
	public List<CreditCardResponse> addCreditCardDetails(CreditCardRequest cardDetails) throws CreditCardException {
		Optional<CreditCard> cardDetail = Optional.ofNullable(cardRepository.findByNumber(cardDetails.getNumber()));
		if (cardDetail.isPresent()) {
			int newBalance = cardDetail.get().getBalance() + cardDetails.getBalance();
			if (cardDetail.get().getLimit() >= newBalance) {
				cardRepository.updateCreditCardUsingQueryAnnotation(newBalance, cardDetails.getNumber());
			} else {
				throw new CreditCardException("You are Trying to use your credit more than your allowed credit Limit",
						HttpStatus.BAD_REQUEST);
			}

		} else {
			cardRepository
					.save(new CreditCard(cardDetails.getNumber(), cardDetails.getName(), cardDetails.getLimit(), 0));
		}
		List<CreditCardResponse> creditCardDetails = getCreditCardDetails();
		return creditCardDetails;
	}

	@Override
	public List<CreditCardResponse> getCreditCardDetails() {
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
}
