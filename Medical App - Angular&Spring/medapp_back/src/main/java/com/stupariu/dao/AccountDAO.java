package com.stupariu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.stupariu.entity.Account;
import com.stupariu.enums.AccountType;

@Repository("accountSQL")
public class AccountDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static class AccountRowMapper implements RowMapper<Account> {

		public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
			Account account = new Account();
			account.setUsername(rs.getString("Username"));
			account.setPassword(rs.getString("Password"));
			account.setAccountType(AccountType.valueOf(rs.getString("Type")));
			account.setName(rs.getString("Name"));
			return account;
		}

	}
	
	public Collection<Account> getAllAccounts() {
		final String sqlStatement = "SELECT * FROM users";
		List<Account> accounts = jdbcTemplate.query(sqlStatement, new AccountRowMapper());
		return accounts;
	}

	public void updateAccount(Account account) {
		final String sqlStatement = "UPDATE users SET Name=?, Type=?, Password=? where Username = ? ";
		final String Name = account.getName();
		final String Username = account.getUsername();
		final String Type = String.valueOf(account.getAccountType());
		final String Password = account.getPassword();
		jdbcTemplate.update(sqlStatement, new Object[] { Name, Type,Password, Username });

	}

	public void deleteAccount(String username) {
		final String sqlStatement = "DELETE FROM users where Username=?";
		jdbcTemplate.update(sqlStatement, username);
	}

	public void insertAccount(Account account) {
		final String sqlStatement = "INSERT INTO users (Name, Type,Password, Username) VALUES (?,? ,?,?)";
		final String Name = account.getName();
		final String Username = account.getUsername();
		final String Type = String.valueOf(account.getAccountType());
		final String Password = account.getPassword();
		jdbcTemplate.update(sqlStatement, new Object[] { Name, Type,Password, Username });
	}

	

}
