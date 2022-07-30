package com.sapient.card.model;

public class CreditCardRequest {

	private String name;
	private String number;
	private int limit;
	
	public CreditCardRequest() {		 
	}
	
	public CreditCardRequest(String name, String number, int limit) {
		this.name = name;
		this.number = number;
		this.limit = limit;
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
	
	public boolean checkLuhn10() {
		number = number.replaceAll("[ -]", "");
	    int sum = 0;
	    for (int i=0; i<number.length(); i++){
	    int digit = (int) number.charAt(i) - '0';
	      if (i % 2 == 0) {
	        digit *= 2;
	        if (digit > 9)
	          digit -= 9;
	      }
	      sum += digit;
	    }
	    return (sum % 10) == 0;
	}
}
