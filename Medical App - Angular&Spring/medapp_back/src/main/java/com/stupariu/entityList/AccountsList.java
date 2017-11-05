package com.stupariu.entityList;

import java.util.ArrayList;

import com.stupariu.entity.Account;

public class AccountsList {
	private ArrayList<Account> accountsList = new ArrayList<Account>();

	public AccountsList(){
		
	}
	
	public ArrayList<Account> getAccountsList() {
		return accountsList;
	}

	public void setAccountsList(ArrayList<Account> accountsList) {
		this.accountsList = accountsList;
	}
}
