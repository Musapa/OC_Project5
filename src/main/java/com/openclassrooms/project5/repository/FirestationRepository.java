package com.openclassrooms.project5.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.project5.domain.Firestation;
import com.openclassrooms.project5.domain.Person;

@Repository
public class FirestationRepository {

	private List<Firestation> firestations = new ArrayList<>();

	public List<Firestation> getFirestation() {
		return firestations;
	}

	public void add(Firestation firestation) {
		firestations.add(firestation);
	}

	// ---------- URLs ----------

	// http://localhost:8080/fire?address=<address>
	public Firestation getFirestationByAddress(String address) {
		for (Firestation firestation : firestations) {
			if (firestation.getAddress().equals(address)) {
				return firestation;
			}
		}
		return null;
	}

	// http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	public List<Firestation> getFirestationHouseholdsByStationNumbers(String station) {

		List<Firestation> result = new ArrayList<>();

		for (Firestation firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				result.add(firestation);
			}
		}
		return result;
	}

	// http://localhost:8080/phoneAlert?firestation=<firestation_number>
	public List<String> getPhoneNumbersByStationNumber(String station) {

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
	public List<Firestation> getListOfPeopleByStationNumber(String station) {

		List<Firestation> result = new ArrayList<>();

		for (Firestation firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				result.add(firestation);
			}
		}
		return result;
	}
	
	// ---------- END OF URLs ----------

	
	// ---------- ENDPOINTS ----------
	
	public Firestation createFirestation(Firestation firestation) {
		firestations.add(firestation);
		return firestation;
	}

	// TODO find getFirestationByAddress and change address if its not null
	public Firestation updateFirestation(Firestation firestation) {
		Firestation findFirestation = getFirestationByAddress(firestation.getAddress());
		if (findFirestation != null) {
			findFirestation.setAddress(firestation.getAddress());
			findFirestation.setStation(firestation.getStation());
			return firestation;
		}
		return null;
	}

	public boolean deleteFirestation(Firestation firestation) {
		for (Firestation findFirestation : firestations) {
			if (findFirestation.getStation().equals(firestation.getStation())
					&& findFirestation.getAddress().equals(firestation.getAddress())) {
				firestations.remove(findFirestation);
				return true;
			}
		}
		return false;
	}

}