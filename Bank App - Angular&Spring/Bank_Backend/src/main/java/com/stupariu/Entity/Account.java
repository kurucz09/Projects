package com.stupariu.Entity;

import com.stupariu.enums.Currency;

public class Account {
	private String clientCNP;
	private String accountNumber;
	private Currency currency;
	private String credit;
	private String dateCreated;
	
	public Account(){
		
	}

	public Account(String clientCNP, String accountNumber, Currency currency, String credit, String dateCreated) {
		this.clientCNP = clientCNP;
		this.accountNumber = accountNumber;
		this.currency = currency;
		this.credit = credit;
		this.dateCreated = dateCreated;
	}

	public String getClientCNP() {
		return clientCNP;
	}

	public void setClientCNP(String clientCNP) {
		this.clientCNP = clientCNP;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
	
}
