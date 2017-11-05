package com.stupariu.entityList;

import java.util.ArrayList;

import com.stupariu.entity.Patient;

public class PatientsList {
	private ArrayList<Patient> patientsList = new ArrayList<Patient>();

	public PatientsList(){
		
	}
	
	public ArrayList<Patient> getPatientsList() {
		return patientsList;
	}

	public void setPatientsList(ArrayList<Patient> patientsList) {
		this.patientsList = patientsList;
	}
	
}
