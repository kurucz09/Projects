package com.stupariu.DB;



public class ConsultDoctor {
	private String id;
	private String doctorID;
	private String consultationID;
	public ConsultDoctor(){
		
	}
	public ConsultDoctor(String id, String doctorID, String consultationID) {
		super();
		this.id = id;
		this.doctorID = doctorID;
		this.consultationID = consultationID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}
	public String getConsultationID() {
		return consultationID;
	}
	public void setConsultationID(String consultationID) {
		this.consultationID = consultationID;
	}
	
	

}
