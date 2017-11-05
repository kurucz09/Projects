package com.stupariu.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.stupariu.Entity.Account;
import com.stupariu.enums.Currency;

@Repository("accountSQL")
public class MySQLAccountDAO implements AccountDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static class AccountRowMapper implements RowMapper<Account> {

		@Override
		public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
			Account account = new Account();
			account.setClientCNP(rs.getString("cnp"));
			account.setAccountNumber(rs.getString("account"));
			account.setCurrency(Currency.valueOf(rs.getString("currency")));
			account.setCredit(rs.getString("credit"));
			account.setDateCreated(rs.getString("datecreated"));
			return account;
		}

	}

	@Override
	public Collection<Account> getAllAccounts() {
		final String sqlStatement = "SELECT * FROM accounts";
		List<Account> accounts = jdbcTemplate.query(sqlStatement, new AccountRowMapper());
		return accounts;
	}

	@Override
	public Collection<Account> getAccountsOfClient(String cnp) {
		final String sqlStatement = "SELECT * FROM accounts WHERE cnp=?";
		List<Account> accounts = jdbcTemplate.query(sqlStatement, new AccountRowMapper(), cnp);
		return accounts;
	}

	@Override
	public Account getAccountByAccountNumber(String accountNumber) {
		try{
			final String sqlStatement = "SELECT * FROM accounts WHERE account=?";
			Account account = jdbcTemplate.queryForObject(sqlStatement, new AccountRowMapper(), accountNumber);
			return account;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		
	}

	@Override
	public void updateAccount(Account account) {
		final String sqlStatement = "UPDATE accounts SET cnp=?, credit=?, currency=?, datecreated=? where account = ? ";
		final String cnp = account.getClientCNP();
		final String credit = account.getCredit();
		final String currency = String.valueOf(account.getCurrency());
		final String dateCreated = account.getDateCreated();
		final String accountNumber = account.getAccountNumber();
		jdbcTemplate.update(sqlStatement, new Object[] { cnp, credit,currency, dateCreated,accountNumber });

	}

	@Override
	public void deleteAccount(String accountNumber) {
		final String sqlStatement = "DELETE FROM accounts where account=?";
		jdbcTemplate.update(sqlStatement, accountNumber);
	}

	@Override
	public void insertAccount(Account account) {
		final String sqlStatement = "INSERT INTO accounts (cnp,account,currency,credit,datecreated) VALUES (? ,? ,? ,?,?)";
		final String cnp = account.getClientCNP();
		final String accountNumber = account.getAccountNumber();
		final String credit = account.getCredit();
		final String currency = String.valueOf(account.getCurrency());
		final String dateCreated = account.getDateCreated();
		jdbcTemplate.update(sqlStatement, new Object[] { cnp, accountNumber, currency, credit, dateCreated });
	}

}
