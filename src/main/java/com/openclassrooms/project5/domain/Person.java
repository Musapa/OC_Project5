package com.openclassrooms.project5.domain;

import com.jsoniter.annotation.JsonProperty;
import com.openclassrooms.project5.json.PersonData;

public class Person {

	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;
	
	private MedicalRecord medicalRecord;
	private Firestation firestation;
	
	public Person() {

	}
	
	public Person (PersonData personData, MedicalRecord medicalRecord, Firestation firestation) {
		this.firstName = personData.getFirstName();
		this.lastName = personData.getLastName();
		this.address = personData.getAddress();
		this.city = personData.getCity();
		this.zip = personData.getZip();
		this.phone = personData.getPhone();
		this.email = personData.getEmail();
		this.medicalRecord = medicalRecord;
		this.firestation = firestation;
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
    
   	public Firestation getFirestation() {
   		return firestation;
   	}
    public void setFirestation(Firestation firestation) {
        this.firestation = firestation;
    }
	
}
