package com.openclassrooms.project5.domain;

import com.jsoniter.annotation.JsonWrapper;
import com.jsoniter.annotation.JsonProperty;

public class Person {

	private String firstName;
	private String lastName;
	private String adress;
	private String city;
	private int zip;
	private int phone;
	private String email;

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
	public void setAdress(@JsonProperty("adress") String adress) {
		this.adress = adress;
	}

	public String getAdress() {
		return this.adress;
	}

	@JsonWrapper
	public void setCity(@JsonProperty("city") String city) {
		this.city = city;
	}

	public String getCity() {
		return this.city;
	}

	@JsonWrapper
	public void setZip(@JsonProperty("zip") int zip) {
		this.zip = zip;
	}

	public int getZip() {
		return this.zip;
	}

	@JsonWrapper
	public void setPhone(@JsonProperty("phone") int phone) {
		this.phone = phone;
	}

	public int getPhone() {
		return this.phone;
	}

	@JsonWrapper
	public void setEmail(@JsonProperty("email") String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}
}
