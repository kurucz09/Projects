package com.stupariu.entityList;

import java.util.ArrayList;

import com.stupariu.entity.Consultation;

public class ConsultationList {
	
	private ArrayList<Consultation> consultationList = new ArrayList<Consultation>();

	public ConsultationList(){
		
	}
	public ConsultationList(ArrayList<Consultation> consultationList) {
		super();
		this.consultationList = consultationList;
	}

	public ArrayList<Consultation> getConsultationList() {
		return consultationList;
	}

	public void setConsultationList(ArrayList<Consultation> consultationList) {
		this.consultationList = consultationList;
	}
	
	
	
	
}
