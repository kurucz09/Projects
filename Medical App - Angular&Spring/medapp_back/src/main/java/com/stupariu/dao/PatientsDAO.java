package com.stupariu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.stupariu.entity.Patient;

@Repository("patientSQL")
public class PatientsDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static class PatientRowMapper implements RowMapper<Patient> {

		public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
			Patient patient = new Patient();
			patient.setPNC(rs.getString("PNC"));
			patient.setIDCard(rs.getString("IDCard"));
			patient.setName(rs.getString("Name"));
			patient.setDateOfBirth(rs.getDate("DateOfBirth"));
			patient.setAddress(rs.getString("Address"));
			return patient;
		}

	}
	
	public Collection<Patient> getAllPatients() {
		final String sqlStatement = "SELECT * FROM patients";
		List<Patient> patients = jdbcTemplate.query(sqlStatement, new PatientRowMapper());
		return patients;
	}

	public Patient getPatientByID(String patientID) {
		try{
			final String sqlStatement = "SELECT * FROM patients WHERE IDCard=?";
			Patient patient = jdbcTemplate.queryForObject(sqlStatement, new PatientRowMapper(), patientID);
			return patient;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void updatePatient(Patient patient) {
		final String sqlStatement = "UPDATE patients SET PNC=?, Name=?, DateOfBirth=?, Address=? where IDCard = ? ";
		final String PNC = patient.getPNC();
		final String Name = patient.getName();
		final String DateOfBirth = patient.getDateOfBirth().toString();
		final String Address = patient.getAddress();
		final String IDCard = patient.getIDCard();
		jdbcTemplate.update(sqlStatement, new Object[] { PNC, Name,DateOfBirth, Address,IDCard });

	}

	public void deletePatient(String patientID) {
		final String sqlStatement = "DELETE FROM patients where IDCard=?";
		jdbcTemplate.update(sqlStatement, patientID);
	}

	public void insertPatient(Patient patient) {
		final String sqlStatement = "INSERT INTO patients (PNC, Name, DateOfBirth, Address, IDCard) VALUES (?,?,?,?,?) ";
		final String PNC = patient.getPNC();
		final String Name = patient.getName();
		final String DateOfBirth = patient.getDateOfBirth().toString();
		final String Address = patient.getAddress();
		final String IDCard = patient.getIDCard();
		jdbcTemplate.update(sqlStatement, new Object[] { PNC, Name,DateOfBirth, Address,IDCard });

	}
	
}
