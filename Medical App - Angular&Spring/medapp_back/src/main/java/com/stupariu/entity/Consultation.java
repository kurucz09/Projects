package com.stupariu.entity;

public class Consultation {

	private String id;
	private Patient patient;
	private String startDate;
	private String endDate;
	private String doctorUsername;
	private String details;
	private String checked;
	private String alert;

	public Consultation() {

	}

	public Consultation(String id, Patient patient, String startDate, String endDate, String doctorUsername,
			String details, String checked, String alert) {
		super();
		this.id = id;
		this.patient = patient;
		this.startDate = startDate;
		this.endDate = endDate;
		this.doctorUsername = doctorUsername;
		this.checked = checked;
		this.alert = alert;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDoctorUsername() {
		return doctorUsername;
	}

	public void setDoctorUsername(String doctorUsername) {
		this.doctorUsername = doctorUsername;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
}
