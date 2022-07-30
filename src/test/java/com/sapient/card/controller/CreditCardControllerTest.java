package com.sapient.card.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sapient.card.model.CreditCardRequest;
import com.sapient.card.model.CreditCardResponse;
import com.sapient.card.service.CreditCardService;

@SpringBootTest
@RunWith(SpringRunner.class)
class CreditCardControllerTest {

	@Autowired
	private CreditCardService service; 
	
	private CreditCardRequest creditCardDetailOne; 
	private CreditCardRequest creditCardDetailTwo;
	
	
	@BeforeEach
	void setUp() throws Exception {
		creditCardDetailOne = new CreditCardRequest("Allice","1111222233334444",300,0);
		creditCardDetailTwo = new CreditCardRequest("Bruce","4444333322221111",500,0);
	}
	
	@Test
	void testCreateCreditTransactionForUser_1() {
		List<CreditCardResponse> creditCardDetails = service.addCreditCardDetails(creditCardDetailOne);
		assertNotNull(creditCardDetails);
		
		assertEquals("Allice",creditCardDetails.get(0).getName());
		assertEquals("1111222233334444",creditCardDetails.get(0).getNumber());
		assertEquals(creditCardDetails.get(0).getLimit(),300);
		assertEquals(creditCardDetails.get(0).getBalance(), 0);
	}

	@Test
	void testCreateCreditTransactionForUser_2() {
		List<CreditCardResponse> creditCardDetails = service.addCreditCardDetails(creditCardDetailTwo);
		assertNotNull(creditCardDetails);
		assertEquals("Bruce",creditCardDetails.get(1).getName());
		assertEquals("4444333322221111",creditCardDetails.get(1).getNumber());		
		assertEquals(creditCardDetails.get(1).getLimit(),500);
		assertEquals(creditCardDetails.get(1).getBalance(), 0);
	}
	
	@Test
	void testGetAllTransactions() {
		List<CreditCardResponse> creditCardDetails = service.addCreditCardDetails(creditCardDetailTwo);
		assertNotNull(creditCardDetails);
		assertEquals(2, creditCardDetails.size());
	}

	@Test
	public void testLuhn10Algorithm() {
		String cardNumber = "4716-4359-1733-0099";
		CreditCardRequest request = new CreditCardRequest("Allice", cardNumber, 100,0);
		assertTrue(request.checkLuhn10() );
		
		String wrongCardNumber = "1111-2222-1111-2222";
		CreditCardRequest request1 = new CreditCardRequest("Allice", wrongCardNumber, 100,0);
		assertFalse(request1.checkLuhn10() );
	}
}
