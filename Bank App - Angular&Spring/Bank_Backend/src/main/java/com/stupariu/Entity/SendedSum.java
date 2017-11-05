package com.stupariu.Entity;

public class SendedSum {
	Account account;
	String sum;

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public SendedSum(){
		
	}
	
	public SendedSum(String sum, Account account) {
		this.sum = sum;
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
}
