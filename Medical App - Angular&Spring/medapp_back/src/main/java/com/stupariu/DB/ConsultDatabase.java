package com.stupariu.DB;

public class ConsultDatabase {
	private String id;
	private String startDate;
	private String endDate;
	private String patient_id;
	private String details;
	private String checked;
	private String alert;

	public ConsultDatabase() {

	}

	public ConsultDatabase(String id, String startDate, String endDate, String patient_id, String details,
			String checked, String alert) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.patient_id = patient_id;
		this.details = details;
		this.checked = checked;
		this.alert = alert;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
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
