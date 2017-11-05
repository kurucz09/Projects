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

import com.stupariu.Entity.Log;

@Repository("logSQL")
public class MySQLLogDAO implements LogDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static class LogRowMapper implements RowMapper<Log> {

		@Override
		public Log mapRow(ResultSet rs, int rowNum) throws SQLException {
			Log log = new Log();
			log.setId(rs.getString("id"));
			log.setUsername(rs.getString("username"));
			log.setActivity(rs.getString("activity"));
			log.setDateCreated(rs.getString("dateCreated"));
			return log;
		}

	}
	
	@Override
	public Collection<Log> getLogsOfUser(String username) {
		final String sqlStatement = "SELECT * FROM log WHERE username=?";
		try {
			List<Log> logs = jdbcTemplate.query(sqlStatement, new LogRowMapper(), username);
			return logs;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void insertLog(Log log) {
		final String sqlStatement = "INSERT INTO log (username,activity,dateCreated) VALUES (? ,? ,?)";
		final String username = log.getUsername();
		final String activity = log.getActivity();
		final String dateCreated = log.getDateCreated();
		jdbcTemplate.update(sqlStatement, new Object[] { username, activity,dateCreated });

	}

	@Override
	public Collection<Log> getLogsOfUserInterval(String username, String startDate, String endDate) {
		final String sqlStatement = "SELECT * FROM log WHERE username=? and (dateCreated BETWEEN ? AND ? )";
		try {
			List<Log> logs = jdbcTemplate.query(sqlStatement, new LogRowMapper(), username, startDate,endDate);
			return logs;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
