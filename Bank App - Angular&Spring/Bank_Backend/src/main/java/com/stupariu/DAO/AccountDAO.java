package com.stupariu.DAO;

import java.util.Collection;

import com.stupariu.Entity.Account;

public interface AccountDAO {

	Collection<Account> getAllAccounts();
	
	Collection<Account> getAccountsOfClient(String cnp);
	
	Account getAccountByAccountNumber(String accountNumber);
	
	void updateAccount(Account account);
	
	void deleteAccount(String accountNumber);
	
	void insertAccount(Account account);
	
}
