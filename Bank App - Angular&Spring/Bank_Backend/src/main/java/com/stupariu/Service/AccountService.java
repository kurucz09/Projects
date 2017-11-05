package com.stupariu.Service;

import java.text.DecimalFormat;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stupariu.DAO.AccountDAO;
import com.stupariu.Entity.Account;
import com.stupariu.Entity.SendedSum;
import com.stupariu.Entity.Transaction;
import com.stupariu.enums.Currency;

@Service
public class AccountService {

	@Autowired
	@Qualifier("accountSQL")
	private AccountDAO accountDAO;

	public Collection<Account> getAllAccounts() {
		return this.accountDAO.getAllAccounts();
	}

	public Collection<Account> getAccountsOfClient(String cnp) {
		return this.accountDAO.getAccountsOfClient(cnp);
	}

	public Account getAccountByAccountNumber(String accountNumber) {
		return this.accountDAO.getAccountByAccountNumber(accountNumber);
	}

	public void updateAccount(Account account) {
		this.accountDAO.updateAccount(account);
	}

	public void deleteAccount(String accountNumber) {
		this.accountDAO.deleteAccount(accountNumber);
	}

	public void insertAccount(Account account) {
		this.accountDAO.insertAccount(account);
	}

	public void modifyCredit(SendedSum ss) {
		Account newAccount = ss.getAccount();
		String sendedSum = ss.getSum();

		float toAdd = Float.parseFloat(sendedSum);
		float haved = Float.parseFloat(ss.getAccount().getCredit());
		float finalAdd = toAdd + haved;
		String finalStringAdd = String.valueOf(roundTwoDecimals(finalAdd));
		newAccount.setCredit(finalStringAdd);
		this.accountDAO.updateAccount(newAccount);
	}

	public void makeTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		Account fromAccount = transaction.getFromAccount();
		Account fromNewAccount = fromAccount;
		Currency fromCur = fromAccount.getCurrency();
		String fromCreditString = fromAccount.getCredit();
		Float fromCredit = Float.parseFloat(fromCreditString);

		Account toAccount = transaction.getToAccount();
		Account toNewAccount = toAccount;
		Currency toCur = toAccount.getCurrency();
		String toCreditString = toAccount.getCredit();
		Float toCredit = Float.parseFloat(toCreditString);

		String sum = transaction.getSum();
		float toTransact = Float.parseFloat(sum);
		float toTransactTo = toTransact;

		
		if (fromCur.equals(Currency.EUR) && toCur.equals(Currency.RON)) {
			toTransactTo*=4.56;
		} else if (fromCur.equals(Currency.RON) && toCur.equals(Currency.EUR)) {
			toTransactTo/=4.56;
		} else if (fromCur.equals(Currency.USD) && toCur.equals(Currency.RON)) {
			toTransactTo*=4.27;
		} else if (fromCur.equals(Currency.RON) && toCur.equals(Currency.USD)) {
			toTransactTo/=4.27;
		} else if (fromCur.equals(Currency.EUR) && toCur.equals(Currency.USD)) {
			toTransactTo*=1.07;
		} else if (fromCur.equals(Currency.USD) && toCur.equals(Currency.EUR)) {
			toTransactTo/=1.07;
		}
		

		float finalTransactFrom = fromCredit - toTransact;
		String finalStringTransactFrom = String.valueOf(roundTwoDecimals(finalTransactFrom));
		System.out.println("FROM AFTER"+finalStringTransactFrom);
		fromNewAccount.setCredit(finalStringTransactFrom);
		this.accountDAO.updateAccount(fromNewAccount);
		
		float finalTransactTo = toCredit + toTransactTo;
		String finalStringTransactTo = String.valueOf(roundTwoDecimals(finalTransactTo));
		System.out.println("TO AFTER"+finalStringTransactTo);
		toNewAccount.setCredit(finalStringTransactTo);
		this.accountDAO.updateAccount(toNewAccount);
	}

	float roundTwoDecimals(float d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Float.valueOf(twoDForm.format(d));
	}
}
