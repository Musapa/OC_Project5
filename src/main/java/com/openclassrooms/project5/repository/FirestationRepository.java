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

	// http://localhost:8080/fire?address=<address>
	public Firestation findFirestationByAddress(String address) {
		for (Firestation firestation : firestations) {
			if (firestation.getAddress().equals(address)) {
				return firestation;
			}
		}
		throw new ApiException("Cannot find firestation.");
	}

	// http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	public List<Firestation> findFirestationByNumber(String station) {

		List<Firestation> result = new ArrayList<>();

		for (Firestation firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				result.add(firestation);
			}
		}
		return result;
	}

	// http://localhost:8080/phoneAlert?firestation=<firestation_number>
	public List<String> findPhoneByStation(String station) {

		List<String> result = new ArrayList<>();

		for (Firestation firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				for (Person person : firestation.getPersons()) {
					result.add(person.getPhone());
				}
			}
		}
		return result;
	}

	// http://localhost:8080/firestation?stationNumber=<station_number>
	public List<Firestation> findByStation(String station) {
		
		List<Firestation> result = new ArrayList<>();
		
		for (Firestation firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				result.add(firestation);
			}
		}
		return result;
	}

}