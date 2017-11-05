package com.stupariu.entity;

import com.stupariu.enums.AccountType;

public class Account {
	private String Username;
	private String Password;
	private String Name;
	private AccountType accountType;
	public Account(){
		
	}
	public Account(String username, String password, String name, AccountType accountType) {
		super();
		Username = username;
		Password = password;
		Name = name;
		this.accountType = accountType;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	
	
}
