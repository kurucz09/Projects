package com.stupariu.Entity;

public class Bill {
	private int billId;
	private String sender;
	private String ammount;
	private String receiverAccount;
	private String dateCreated;
	private String state;
	
	public Bill(){
		
	}

	public Bill(int billId, String senderAccount, String ammound, String receiverAccount, String dateCreated,
			String state) {
		this.billId = billId;
		this.sender = senderAccount;
		this.ammount = ammound;
		this.receiverAccount = receiverAccount;
		this.dateCreated = dateCreated;
		this.state = state;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int i) {
		this.billId = i;
	}

	public String getSenderAccount() {
		return sender;
	}

	public void setSenderAccount(String senderAccount) {
		this.sender = senderAccount;
	}

	public String getAmmound() {
		return ammount;
	}

	public void setAmmound(String ammound) {
		this.ammount = ammound;
	}

	public String getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(String receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
}
