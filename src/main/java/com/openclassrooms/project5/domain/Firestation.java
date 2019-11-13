package com.openclassrooms.project5.domain;

import java.util.ArrayList;
import java.util.List;

import com.jsoniter.annotation.JsonProperty;

public class Firestation {

	private String address;
	private String station;
	private List<Person> persons;

	public Firestation() {
		this("", "");
	}

	public Firestation(String address, String station) {
		this.setAddress(address);
		this.setStation(station);
		this.setPersons(new ArrayList<Person>());
	}

	public void setAddress(@JsonProperty("address") String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}

	public void setStation(@JsonProperty("station") String station) {
		this.station = station;
	}

	public String getStation() {
		return this.station;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
}
