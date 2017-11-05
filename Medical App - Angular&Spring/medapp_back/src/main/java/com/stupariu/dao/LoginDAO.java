package com.stupariu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.stupariu.entity.Account;
import com.stupariu.enums.AccountType;

@Repository("loginSQL")
public class LoginDAO {

	
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
	
	
	
	public Account checkAccount(String email, String password) {
		final String sqlStatement = "SELECT * FROM users WHERE Username=? AND Password=?";
		try{
			Account account = jdbcTemplate.queryForObject(sqlStatement, new AccountRowMapper(), email, password);
			return account;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} 
		
		
		
	}

}
