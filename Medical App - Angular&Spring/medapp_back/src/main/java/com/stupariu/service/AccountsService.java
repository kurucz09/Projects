package com.stupariu.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stupariu.dao.AccountDAO;
import com.stupariu.entity.Account;

@Service
public class AccountsService {
	
	@Autowired
	@Qualifier("accountSQL")
	private AccountDAO accountDAO;

	public Collection<Account> getAllAccounts() {
		return this.accountDAO.getAllAccounts();
	}

	public void updateAccount(Account account) {
		this.accountDAO.updateAccount(account);
	}

	public void deleteAccount(String username) {
		this.accountDAO.deleteAccount(username);
	}

	public void insertAccount(Account account) {
		this.accountDAO.insertAccount(account);
	}


}
