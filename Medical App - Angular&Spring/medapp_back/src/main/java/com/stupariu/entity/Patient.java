package com.stupariu.entity;

import java.sql.Date;

public class Patient {
	private String PNC;
	private String IDCard;
	private String Name;
	private Date DateOfBirth;
	private String Address;
	
	public Patient(){
		
	}
	
	public Patient(String pNC, String iDCard, String name, Date dateOfBirth, String address) {
		super();
		PNC = pNC;
		IDCard = iDCard;
		Name = name;
		DateOfBirth = dateOfBirth;
		Address = address;
	}

	public String getPNC() {
		return PNC;
	}

	public void setPNC(String pNC) {
		PNC = pNC;
	}

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Date getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
	
	

}
