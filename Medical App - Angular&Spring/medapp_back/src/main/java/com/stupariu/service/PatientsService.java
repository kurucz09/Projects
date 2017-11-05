package com.stupariu.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stupariu.dao.PatientsDAO;
import com.stupariu.entity.Patient;

@Service
public class PatientsService {

	@Autowired
	@Qualifier("patientSQL")
	private PatientsDAO patientsDAO;

	public Collection<Patient> getAllPatients() {
		return this.patientsDAO.getAllPatients();
	}

	public Patient getPatientByID(String patientID) {
		return this.patientsDAO.getPatientByID(patientID);
	}

	public void updatePatient(Patient patient) {
		this.patientsDAO.updatePatient(patient);
	}

	public void deletePatient(String patientID) {
		this.patientsDAO.deletePatient(patientID);
	}

	public void insertPatient(Patient patient) {
		this.patientsDAO.insertPatient(patient);
	}
	
}
