package com.stupariu.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stupariu.DB.ConsultDatabase;
import com.stupariu.dao.DoctorDAO;
import com.stupariu.entity.Account;
import com.stupariu.entity.Consultation;
import com.stupariu.entity.Patient;

@Service
public class DoctorService {

	@Autowired
	@Qualifier("doctorSQL")
	private DoctorDAO doctorDAO;

	public Collection<Account> getAllDoctors() {
		return this.doctorDAO.getAllDoctors();
	}

	public Collection<Patient> getAllPatients() {
		return this.doctorDAO.getAllPatients();
	}

	public void insertPatient(Patient patient) {
		this.doctorDAO.insertPatient(patient);
	}

	public void updatePatient(Patient patient) {
		this.doctorDAO.updatePatient(patient);
	}

	public void deletePatient(String pnc) {
		this.doctorDAO.deletePatient(pnc);

	}

	public void addConsultation(Consultation consultation) {
		this.doctorDAO.addConsultation(consultation);
	}

	public Collection<Consultation> getConsultationOfDoctor(String name) {
		return this.doctorDAO.getConsultationOfDoctor(name);
	}

	public void updateConsultation(ConsultDatabase consultation) {
		this.doctorDAO.updateConsultation(consultation);
	}

	public void deleteConsultation(String id) {
		this.doctorDAO.deleteConsultation(id);
	}

	public Collection<Consultation> getAllConsultsOfDoctorPatient(String doctor, String patient) {
		Collection<Consultation> list = this.getConsultationOfDoctor(doctor);
		Collection<Consultation> returnList = new ArrayList<Consultation>();
		if (patient.contentEquals("emptySel23")) {
			return list;
		} else {
			for (Consultation c : list) {
				if (c.getPatient().getPNC().contentEquals(patient)) {
					returnList.add(c);
				}
			}
			return returnList;
		}
	}

}
