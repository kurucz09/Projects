package com.stupariu.Entity;

public class Client {
	private String IdClient;
	private String name;
	private String CNP;
	private String address;
	private String phone;

	public Client() {

	}

	public Client(String idClient, String name, String cNP, String address, String phone) {
		this.IdClient = idClient;
		this.name = name;
		this.CNP = cNP;
		this.address = address;
		this.phone = phone;
	}

	public String getIdClient() {
		return IdClient;
	}

	public void setIdClient(String idClient) {
		IdClient = idClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCNP() {
		return CNP;
	}

	public void setCNP(String cNP) {
		CNP = cNP;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
