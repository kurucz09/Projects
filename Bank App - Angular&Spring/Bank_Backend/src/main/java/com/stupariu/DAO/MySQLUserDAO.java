package com.stupariu.DAO;
	
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.stupariu.Entity.User;
import com.stupariu.enums.LogginState;

@Repository("userSQL")
public class MySQLUserDAO implements UserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static class UserRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setUsername(rs.getString("username"));
			user.setUserType(LogginState.valueOf(rs.getString("type")));
			return user;
		}

	}

	@Override
	public Collection<com.stupariu.Entity.User> getAllUsers() {
		final String sqlStatement = "SELECT * FROM users";
		List<User> users = jdbcTemplate.query(sqlStatement, new UserRowMapper());
		return users;
	}

	@Override
	public com.stupariu.Entity.User getUserById(int id) {
		final String sqlStatement = "SELECT * FROM users WHERE id=?";
		User user = jdbcTemplate.queryForObject(sqlStatement, new UserRowMapper(), id);
		return user;
	}

	@Override
	public com.stupariu.Entity.User getUserByUsername(String username) {
		final String sqlStatement = "SELECT * FROM users WHERE username=?";
		User user = jdbcTemplate.queryForObject(sqlStatement, new UserRowMapper(), username);
		return user;
	}

	@Override
	public void updateUser(User user) {
		final String sqlStatement = "UPDATE users SET name=?, username=?, password=?, email=?, type=? where id = ? ";
		final int id = user.getId();
		final String name = user.getName();
		final String username = user.getUsername();
		final String password = user.getPassword();
		final String email = user.getEmail();
		final String type = user.getLogginState().toString();
		jdbcTemplate.update(sqlStatement, new Object[] { name, username, password, email, type, id });
	}

	@Override
	public void deleteUser(String id) {
		final String sqlStatement = "DELETE FROM users where username=?";
		jdbcTemplate.update(sqlStatement, id);
	}

	@Override
	public void insertUser(com.stupariu.Entity.User user) {
		final String sqlStatement = "INSERT INTO users (name,username,password,email,type) VALUES (? ,? ,? ,?,?)";
		final String name = user.getName();
		final String username = user.getUsername();
		final String password = user.getPassword();
		final String email = user.getEmail();
		final String type = user.getLogginState().toString();
		jdbcTemplate.update(sqlStatement, new Object[] { name, username, password, email, type });
	}

	@Override
	public User checkUsernameAndPassword(String username, String password) throws EmptyResultDataAccessException {
		System.out.println("CONNECTED");
		final String sqlStatement = "SELECT * FROM users WHERE username=? and password=?";
		User user = null;
		try {
			user = jdbcTemplate.queryForObject(sqlStatement, new UserRowMapper(), username, password);
		} catch (IncorrectResultSizeDataAccessException se) {
			se.printStackTrace();
		}
		return user;
	}
}
