package com.openclassrooms.project5.domain;

import java.util.List;

import com.jsoniter.annotation.JsonProperty;

public class MedicalRecord {

	private String firstName;
	private String lastName;
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;

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

	public void setBirthdate(@JsonProperty("birthdate") String birthdate) {
		this.birthdate = birthdate;
	}

	public String getBirthdate() {
		return this.birthdate;
	}

	public void setMedications(@JsonProperty("medications") List<String> medications) {
		this.medications = medications;
	}

	public List<String> getMedications() {
		return this.medications;
	}

	public void setAllergies(@JsonProperty("allergies") List<String> allergies) {
		this.allergies = allergies;
	}

	public List<String> getAllergies() {
		return this.allergies;
	}
}
