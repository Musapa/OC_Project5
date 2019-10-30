package com.openclassrooms.project5.json;

import com.jsoniter.annotation.JsonProperty;
import com.openclassrooms.project5.json.PersonData;

public class PersonData {

	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;
	

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
	
}
