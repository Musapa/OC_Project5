package com.openclassrooms.project5.domain;

import java.util.Date;

import com.jsoniter.annotation.JsonProperty;

public class Person {

	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;

	private MedicalRecord medicalRecord;


	public Person() {
		this("", "", "", "", "", "", "", new MedicalRecord());
	}

	public Person(String firstName, String lastName, String address, String city, String zip, String phone,String email, MedicalRecord medicalRecord) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAddress(address);
		this.setCity(city);
		this.setZip(zip);
		this.setPhone(phone);
		this.setEmail(email);
		this.setMedicalRecord(medicalRecord);
	}

	public void setFirstName(@JsonProperty("firstName") String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(@JsonProperty("lastName") String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setAddress(@JsonProperty("address") String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}

	public void setCity(@JsonProperty("city") String city) {
		this.city = city;
	}

	public String getCity() {
		return this.city;
	}

	public void setZip(@JsonProperty("zip") String zip) {
		this.zip = zip;
	}

	public String getZip() {
		return this.zip;
	}

	public void setPhone(@JsonProperty("phone") String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setEmail(@JsonProperty("email") String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public boolean isChild() {
		long now = new Date().getTime();

		return (now - (365 * 18 * 24 * 60 * 60 * 1000L) < getMedicalRecord().getBirthdate().getTime());

	}

}
