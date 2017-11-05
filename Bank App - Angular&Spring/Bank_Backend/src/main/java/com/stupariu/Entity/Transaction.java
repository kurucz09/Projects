package com.stupariu.Entity;

public class Transaction {
	private Account fromAccount;
	private Account toAccount;
	private String sum;
	public Transaction(){
		
	}
	public Transaction(Account fromAccount, Account toAccount, String sum) {
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.sum = sum;
	}
	public Account getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}
	public Account getToAccount() {
		return toAccount;
	}
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	
	
}
