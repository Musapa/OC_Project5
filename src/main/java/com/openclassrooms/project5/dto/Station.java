package com.openclassrooms.project5.dto;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.project5.domain.Person;

public class Station {

	private String stationNumber; // the value passed in
	private List<Person> persons;
	private int numberOfAdults;
	private int numberOfChildren;
	
	public Station(String stationNumber, List<Person> persons, int numberOfAdults, int numberOfChildren) {
		this.stationNumber = stationNumber;
		this.persons = persons;
		this.numberOfAdults = numberOfAdults;
		this.numberOfChildren = numberOfChildren;
	}
	
	public Station() {
		this(" ", new ArrayList<>(), 0, 0);
	}


	public String getStationNumber() {
		return stationNumber;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public int getNumberOfAdults() {
		return numberOfAdults;
	}

	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setStationNumber(String stationNumber) {
		this.stationNumber = stationNumber;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}

	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}
	
	
	
}
