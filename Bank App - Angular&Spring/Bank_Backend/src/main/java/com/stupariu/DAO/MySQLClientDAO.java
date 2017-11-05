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

import com.stupariu.Entity.Client;

@Repository("clientSQL")
public class MySQLClientDAO implements ClientDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static class ClientRowMapper implements RowMapper<Client> {

		@Override
		public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
			Client client = new Client();
			client.setIdClient(rs.getString("IdClient"));
			client.setName(rs.getString("Name"));
			client.setCNP(rs.getString("CNP"));
			client.setAddress(rs.getString("Address"));
			client.setPhone(rs.getString("Phone"));
			return client;
		}

	}

	@Override
	public Collection<Client> getAllClients() {
		final String sqlStatement = "SELECT * FROM clients";
		List<Client> clients = jdbcTemplate.query(sqlStatement, new ClientRowMapper());
		return clients;
	}

	@Override
	public Client getClientById(String CNP) {
		final String sqlStatement = "SELECT * FROM clients WHERE CNP=?";
		try {
			Client client = jdbcTemplate.queryForObject(sqlStatement, new ClientRowMapper(), CNP);
			return client;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		
	}

	@Override
	public void updateClient(Client client) {
		final String sqlStatement = "UPDATE clients SET IdClient=?, Name=?, Address=?, Phone=? where CNP = ? ";
		final String IdClient = client.getIdClient();
		final String name = client.getName();
		final String cnp = client.getCNP();
		final String address = client.getAddress();
		final String phone = client.getPhone();
		jdbcTemplate.update(sqlStatement, new Object[] { IdClient, name, address, phone, cnp });
	}

	@Override
	public void deleteClient(String id) {
		final String sqlStatement = "DELETE FROM clients where CNP=?";
		jdbcTemplate.update(sqlStatement, id);
	}

	@Override
	public void insertClient(Client client) {
		final String sqlStatement = "INSERT INTO clients (IdClient,Name,CNP,Address,Phone) VALUES (? ,? ,? ,?,?)";
		final String IdClient = client.getIdClient();
		final String name = client.getName();
		final String cnp = client.getCNP();
		final String address = client.getAddress();
		final String phone = client.getPhone();
		jdbcTemplate.update(sqlStatement, new Object[] { IdClient, name, cnp, address, phone });

	}

}
