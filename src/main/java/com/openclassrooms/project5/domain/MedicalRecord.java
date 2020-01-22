package com.openclassrooms.project5.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jsoniter.annotation.JsonIgnore;
import com.jsoniter.annotation.JsonProperty;

public class MedicalRecord {

	private String firstName;
	private String lastName;
	private Date birthdate;
	private List<String> medications;
	private List<String> allergies;

	public MedicalRecord() {
		this("", "", new Date(), new ArrayList<String>(), new ArrayList<String>());
	}

	public MedicalRecord(String firstName, String lastName, Date birthdate, List<String> medications, List<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
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

	@JsonIgnore
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public void setBirthdate(@JsonProperty("birthdate") String birthdate) throws ParseException {
		this.birthdate = new SimpleDateFormat("MM/dd/yyyy").parse(birthdate);
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	public Date getBirthdate() {
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
