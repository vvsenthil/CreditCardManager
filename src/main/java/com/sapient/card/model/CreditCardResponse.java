package com.sapient.card.model;

public class CreditCardResponse {

	private String name;
	private String number;
	private int limit;
	private int balance;
	
	public CreditCardResponse() {		 
	}
	
	public CreditCardResponse(String name, String number, int limit, int balance) {
		this.name = name;
		this.number = number;
		this.limit = limit;
		this.balance = balance; 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}
