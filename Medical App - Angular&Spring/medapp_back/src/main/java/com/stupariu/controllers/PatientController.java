package com.stupariu.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stupariu.entity.Patient;
import com.stupariu.service.PatientsService;

@RestController
@RequestMapping("/patients")
@CrossOrigin
public class PatientController {

	@Autowired
	private PatientsService patientsService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<Patient> getAllPatients() {
		return this.patientsService.getAllPatients();
	}
	
	@RequestMapping(value = "/{patientID}",method = RequestMethod.GET)
	@ResponseBody
	public Patient getPatientByID(@PathVariable("patientID") String patientID) {
		return this.patientsService.getPatientByID(patientID);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void updatePatient(@RequestBody Patient patient) {
		this.patientsService.updatePatient(patient);
	}

	@RequestMapping(value = "/{patientID}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deletePatient(@PathVariable("patientID") String patientID) {
		this.patientsService.deletePatient(patientID);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void insertPatient(@RequestBody Patient patient) {
		this.patientsService.insertPatient(patient);
	}
	
}
