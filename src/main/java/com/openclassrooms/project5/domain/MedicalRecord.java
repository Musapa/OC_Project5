package com.openclassrooms.project5.domain;

import java.time.LocalDate;

import com.jsoniter.annotation.JsonProperty;
import com.jsoniter.annotation.JsonWrapper;

public class MedicalRecord {
	
	private String firstName;
	private String lastName;
	private LocalDate birthdate;
	private String medications;
	private String allergies;

	@JsonWrapper
	public void setFirstName(@JsonProperty("firstName") String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	@JsonWrapper
	public void setLastName(@JsonProperty("lastName") String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	@JsonWrapper
	public void setBirthdate(@JsonProperty("birthdate") LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public LocalDate getBirthdate() {
		return this.birthdate;
	}

	@JsonWrapper
	public void setMedications(@JsonProperty("medications") String medications) {
		this.medications = medications;
	}
	
	public String getMedications() {
		return this.medications;
	}
	
	@JsonWrapper
	public void setAllergies(@JsonProperty("allergies") String allergies) {
		this.allergies = allergies;
	}
	
	public String getAllergies() {
		return this.allergies;
	}
}
