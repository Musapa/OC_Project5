package com.openclassrooms.project5.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.project5.domain.Firestation;
import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.exception.ApiException;

@Repository
public class FirestationRepository {

	private List<Firestation> firestations = new ArrayList<>();

	public List<Firestation> getFirestation() {
		return firestations;
	}
	public void add(Firestation firestation) {
		firestations.add(firestation);
	}

	public Firestation findFirestationByAddress(String address) {
		for(Firestation firestation : firestations) {
			if (firestation.getAddress().equals(address)) {
				return firestation;
			}
		}
		throw new ApiException("Cannot find firestation.");
	}
	
	public List<Firestation> findFirestationByNumber(String station) {
		
		/*
		String stationNumber;
		List<Person> persons;
		int numberOfAdult;
		int numberOfChildren;
		*/
		
		List<Firestation> result = new ArrayList<>();
		
		for(Firestation firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				result.add(firestation);
			}	
		}
		return result;
	}
	
	public List<String> findPhoneByStation(String station) {
		
		List<String> result = new ArrayList<>();
		
		for(Firestation firestation : firestations) {
			if (firestation.getStation().equals(station)) {			
				for(Person person : firestation.getPersons()) {
					result.add(person.getPhone());
				}
			}
		}
		return result;
		
	}
}