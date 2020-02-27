package com.openclassrooms.project5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.project5.domain.Firestation;
import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.dto.Station;
import com.openclassrooms.project5.repository.IFirestationRepository;

@Service
public class FirestationService implements IFirestationService {

	@Autowired
	private IFirestationRepository firestationRepository;

	@Override
	public Firestation getFirestationByAddress(List<String> address) {
		return firestationRepository.getFirestationByAddress(address);
	}

	@Override
	public List<Firestation> getFirestationHouseholdsByStationNumbers(String stations[]) {
		List<Firestation> result = new ArrayList<>();

		for (String station : stations) {
			result.addAll(firestationRepository.getFirestationHouseholdsByStationNumbers(station));
		}
		return result;
	}

	@Override
	public List<String> getPhoneNumbersByStationNumber(String station) {
		return firestationRepository.getPhoneNumbersByStationNumber(station);
	}

	@Override
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

	@Override
	public Firestation createFirestation(Firestation firestation) {
		return firestationRepository.createFirestation(firestation);
	}

	@Override
	public Firestation updateFirestation(Firestation firestation) {
		return firestationRepository.updateFirestation(firestation);
	}

	@Override
	public boolean deleteFirestation(Firestation firestation) {
		return firestationRepository.deleteFirestation(firestation);
	}

}
