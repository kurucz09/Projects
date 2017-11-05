package com.stupariu.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stupariu.Entity.Account;
import com.stupariu.Entity.SendedSum;
import com.stupariu.Entity.Transaction;
import com.stupariu.Service.AccountService;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<Account> getAllAccounts() {
		return this.accountService.getAllAccounts();
	}

	@RequestMapping(value = "/client/{cnp}",method = RequestMethod.GET)
	@ResponseBody
	public Collection<Account> getAccountsOfClient(@PathVariable("cnp") String cnp) {
		return this.accountService.getAccountsOfClient(cnp);
	}

	@RequestMapping(value = "/account/{accountNumber}",method = RequestMethod.GET)
	@ResponseBody
	public Account getAccountByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
		return this.accountService.getAccountByAccountNumber(accountNumber);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void updateAccount(@RequestBody Account account) {
		this.accountService.updateAccount(account);
	}

	@RequestMapping(value = "/{accountNumber}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteAccount(@PathVariable("accountNumber") String accountNumber) {
		this.accountService.deleteAccount(accountNumber);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void insertAccount(@RequestBody Account account) {
		this.accountService.insertAccount(account);
	}
	
	@RequestMapping(value = "/addMoney",method = RequestMethod.PUT)
	@ResponseBody
	public void modifyCredit(@RequestBody SendedSum sendedSum) {
		this.accountService.modifyCredit(sendedSum);
	}
	
	@RequestMapping(value = "/transaction",method = RequestMethod.PUT)
	@ResponseBody
	public void makeTransaction(@RequestBody Transaction transaction) {
		this.accountService.makeTransaction(transaction);
	}

}
