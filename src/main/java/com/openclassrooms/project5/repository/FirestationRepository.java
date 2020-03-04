package com.openclassrooms.project5.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.project5.domain.Firestation;
import com.openclassrooms.project5.domain.Person;

@Repository
public class FirestationRepository implements IFirestationRepository {

	private List<Firestation> firestations = new ArrayList<>();

	@Override
	public void add(Firestation firestation) {
		firestations.add(firestation);
	}

	@Override
	public Firestation getFirestationByAddress(String address) {
		for (Firestation firestation : firestations) {
			if (firestation.getAddress().contains(address)) {
				return firestation;
			}
		}
		return null;
	}

	@Override
	public List<Firestation> getFirestationHouseholdsByStationNumbers(String station) {

		List<Firestation> result = new ArrayList<>();

		for (Firestation firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				result.add(firestation);
			}
		}
		return result;
	}

	@Override
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

	@Override
	public List<Firestation> getListOfPeopleByStationNumber(String station) {

		List<Firestation> result = new ArrayList<>();

		for (Firestation firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				result.add(firestation);
			}
		}
		return result;
	}

	@Override
	public Firestation createFirestation(Firestation firestation) {
		for (String address : firestation.getAddress()) {
			Firestation findFirestation = getFirestationByAddress(address);
			if (findFirestation == null) {
				firestations.add(firestation);
				return firestation;
			}	
		}
		return null;
	}

	@Override
	public Firestation updateFirestation(Firestation firestation) {
		Firestation findFirestation = getFirestationByStation(firestation.getStation());
		if (findFirestation != null) {
			findFirestation.setAddress(firestation.getAddress());
			findFirestation.setStation(firestation.getStation());
			return firestation;
		}
		return null;
	}

	@Override
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

	private Firestation getFirestationByStation(String station) {
		for (Firestation firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				return firestation;
			}
		}
		return null;
	}
}