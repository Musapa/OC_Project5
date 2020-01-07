package com.openclassrooms.project5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.project5.domain.Firestation;
import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.dto.Station;
import com.openclassrooms.project5.repository.FirestationRepository;

@Service
public class FirestationService {

	@Autowired
	private FirestationRepository firestationRepository;

	// ---------- URLs ----------
	
	// http://localhost:8080/fire?address=<address>
	public Firestation getFirestationByAddress(String address) {
		return firestationRepository.getFirestationByAddress(address);
	}

	// http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	public List<Firestation> getFirestationHouseholdsByStationNumbers(String stations[]) {
		List<Firestation> result = new ArrayList<>();

		for (String station : stations) {
			result.addAll(firestationRepository.getFirestationHouseholdsByStationNumbers(station));
		}
		return result;
	}

	// http://localhost:8080/phoneAlert?firestation=<firestation_number>
	public List<String> getPhoneNumbersByStationNumber(String station) {
		return firestationRepository.getPhoneNumbersByStationNumber(station);
	}

	// http://localhost:8080/firestation?stationNumber=<station_number>
	public Station getListOfPeopleByStationNumber(String station) {
		List<Firestation> firestations = firestationRepository.getListOfPeopleByStationNumber(station);
		List<Person> persons = new ArrayList<>();
		int numberOfChildren = 0;
		int numberOfAdults = 0;

		for (Firestation firestation : firestations) {
			for (Person person : firestation.getPersons()) {
				persons.add(person);

				if (person.isChild()) {
					numberOfChildren++;
				} else {
					numberOfAdults++;
				}
			}
		}
		return new Station(station, persons, numberOfAdults, numberOfChildren);
	}
	// ---------- END OF URLs ----------
	
	
	// ---------- ENDPOINTS ----------
	
	public Firestation createFirestation(Firestation firestation) {
		return firestationRepository.createFirestation(firestation);
	}
	
	public Firestation updateFirestation(Firestation firestation) {
		return firestationRepository.updateFirestation(firestation);
	}
	
	public boolean deleteFirestation(Firestation firestation) {
		return firestationRepository.deleteFirestation(firestation);
	}
	
	// ---------- END OF ENDPOINTS ----------
}
