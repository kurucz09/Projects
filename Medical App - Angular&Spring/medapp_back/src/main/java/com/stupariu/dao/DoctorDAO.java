package com.stupariu.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.stupariu.DB.ConsultDatabase;
import com.stupariu.DB.ConsultDoctor;
import com.stupariu.entity.Account;
import com.stupariu.entity.Consultation;
import com.stupariu.entity.Patient;

@Repository("doctorSQL")
public class DoctorDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static class AccountRowMapper implements RowMapper<Account> {

		public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
			Account account = new Account();
			account.setUsername(rs.getString("Username"));
			account.setPassword("Try_again");
			account.setAccountType(null);
			account.setName(rs.getString("Name"));
			return account;
		}

	}
	
	private static class PatientRowMapper implements RowMapper<Patient> {

		public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
			Patient patient = new Patient();
			patient.setIDCard(rs.getString("IDCard"));
			patient.setPNC(rs.getString("PNC"));
			patient.setAddress(rs.getString("Address"));
			patient.setName(rs.getString("Name"));
			patient.setDateOfBirth(rs.getDate("DateOfBirth"));
			return patient;
		}

	}
	
	private static class ConsultRowMapper implements RowMapper<ConsultDatabase> {

		public ConsultDatabase mapRow(ResultSet rs, int rowNum) throws SQLException {
			ConsultDatabase consultDatabase = new ConsultDatabase();
			consultDatabase.setId(rs.getString("id"));
			consultDatabase.setEndDate(rs.getString("endDate"));
			consultDatabase.setStartDate(rs.getString("startDate"));
			consultDatabase.setPatient_id(rs.getString("patient_id"));
			consultDatabase.setDetails(rs.getString("details"));
			consultDatabase.setAlert(rs.getString("alert"));
			consultDatabase.setChecked(rs.getString("checked"));
			return consultDatabase;
		}

	}
	
	private static class ConsultDoctorRowMapper implements RowMapper<ConsultDoctor> {

		public ConsultDoctor mapRow(ResultSet rs, int rowNum) throws SQLException {
			ConsultDoctor consultDoctor = new ConsultDoctor();
			consultDoctor.setId(rs.getString("id"));
			consultDoctor.setDoctorID(rs.getString("doctorID"));
			consultDoctor.setConsultationID(rs.getString("consultationID"));
			return consultDoctor;
		}

	}
	
	
	
	
	public Collection<Account> getAllDoctors() {
		final String sqlStatement = "SELECT * FROM users WHERE Type=?";
		String type = "Doctor";
		List<Account> accounts = jdbcTemplate.query(sqlStatement, new AccountRowMapper(),type);
		return accounts;
	}

	public Collection<Patient> getAllPatients() {
		final String sqlStatement = "SELECT * FROM patients";
		List<Patient> patients = jdbcTemplate.query(sqlStatement, new PatientRowMapper());
		return patients;
	}
	
	public Collection<ConsultDatabase> getAllConsults() {
		final String sqlStatement = "SELECT * FROM consultation";
		List<ConsultDatabase> consultDatabase = jdbcTemplate.query(sqlStatement, new ConsultRowMapper());
		return consultDatabase;
	}
	
	public Collection<ConsultDoctor> getAllConsultDoctor() {
		final String sqlStatement = "SELECT * FROM doctor_consultation";
		List<ConsultDoctor> consultDoctor = jdbcTemplate.query(sqlStatement, new ConsultDoctorRowMapper());
		return consultDoctor;
	}

	public void insertPatient(Patient patient) {
		final String sqlStatement = "INSERT INTO patients (PNC, IDCard,Name, DateOfBirth, Address) VALUES (?,? ,?,?,?)";
		final String pnc = patient.getPNC();
		final String idclient = patient.getIDCard();
		final String name = patient.getName();
		final Date date = patient.getDateOfBirth();
		final String address = patient.getAddress();
		jdbcTemplate.update(sqlStatement, new Object[] { pnc, idclient,name, date, address });
	}

	public void updatePatient(Patient patient) {
		final String sqlStatement = "UPDATE patients SET IDCard=?, Name=?, DateOfBirth=?,Address=? where PNC = ? ";
		final String pnc = patient.getPNC();
		final String idclient = patient.getIDCard();
		final String name = patient.getName();
		final Date date = patient.getDateOfBirth();
		final String address = patient.getAddress();
		jdbcTemplate.update(sqlStatement, new Object[] {  idclient,name, date, address,pnc });

	}

	public void deletePatient(String pnc) {
		final String sqlStatement = "DELETE FROM patients where PNC=?";
		jdbcTemplate.update(sqlStatement, pnc);
	}

	
	public Collection<Consultation> getConsultationOfDoctor(String doctor_username) {
		
		List<Consultation> returnList = new ArrayList<Consultation>();
	
		List<ConsultDatabase> consultDatabase = (List<ConsultDatabase>) this.getAllConsults();
		List<ConsultDoctor> consultDoctor = (List<ConsultDoctor>) this.getAllConsultDoctor();
	
		for(ConsultDatabase cd : consultDatabase){
				for(ConsultDoctor cs : consultDoctor){
					if(cd.getId().contentEquals(cs.getConsultationID()) && cs.getDoctorID().contentEquals(doctor_username)){
						
						Patient patient = new Patient();
						Collection<Patient> patientsList = this.getAllPatients();
						for(Patient p : patientsList){
							if(p.getPNC().contentEquals(cd.getPatient_id())){
								patient.setPNC(p.getPNC());
								patient.setAddress(p.getAddress());
								patient.setDateOfBirth(p.getDateOfBirth());
								patient.setIDCard(p.getIDCard());
								patient.setName(p.getName());
							}
						}
						
						Consultation consultation = new Consultation();
						consultation.setDoctorUsername(doctor_username);
						consultation.setId(cd.getId());
						consultation.setStartDate(cd.getStartDate());
						consultation.setEndDate(cd.getEndDate());
						consultation.setPatient(patient);
						consultation.setDetails(cd.getDetails());
						consultation.setAlert(cd.getAlert());
						consultation.setChecked(cd.getChecked());
						returnList.add(consultation);
					}
				}
		}
	
		return returnList;
	}
	
	public void addConsultation(Consultation consultation) {
		final String sqlStatement = "INSERT INTO consultation ( startDate,endDate, patient_id,details, checked,alert) VALUES (? ,?,?,?,?,?)";
		final String startDate = consultation.getStartDate();
		final String endDate = consultation.getEndDate();
		final String patient_id = consultation.getPatient().getPNC();
		final String doctor_name = consultation.getDoctorUsername();
		final String details = consultation.getDetails();
		final String checked = consultation.getChecked();
		final String alert = consultation.getAlert();
		jdbcTemplate.update(sqlStatement, new Object[] {  startDate,endDate, patient_id, details,checked,alert});
	
				
		final String sqlStatement2 = "INSERT INTO doctor_consultation ( doctorID,consultationID) VALUES (? ,? )";
		String consultationID="";	
		Collection<ConsultDatabase> listConsults = this.getAllConsults();
		for(ConsultDatabase cd : listConsults){
			if(cd.getPatient_id().contentEquals(patient_id) &&
					cd.getEndDate().contentEquals(endDate) &&
					cd.getStartDate().contentEquals(startDate)){
				consultationID = cd.getId();	
			}
		}
		
		jdbcTemplate.update(sqlStatement2, new Object[] {  doctor_name,consultationID});

		
	}

	public void updateConsultation(ConsultDatabase consultation) {
		final String sqlStatement = "UPDATE consultation SET startDate=?, endDate=?, patient_id=?,details=?, checked=?,alert=? where id = ? ";
		final String id = consultation.getId();
		final String startDate= consultation.getStartDate();
		final String endDate = consultation.getEndDate();
		final String patient_id = consultation.getPatient_id();
		final String details = consultation.getDetails();
		final String checked = consultation.getChecked();
		final String alert = consultation.getAlert();
		jdbcTemplate.update(sqlStatement, new Object[] {  startDate,endDate, patient_id, details,checked, alert,id});
	}

	public void deleteConsultation(String id) {
		final String sqlStatement = "DELETE FROM consultation where id=?";
		jdbcTemplate.update(sqlStatement, id);
		final String sqlStatement2 = "DELETE FROM doctor_consultation where consultationID=?";
		jdbcTemplate.update(sqlStatement2, id);
	}

}
