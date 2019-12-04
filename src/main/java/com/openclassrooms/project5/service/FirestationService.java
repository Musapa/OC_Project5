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

	public Firestation findFirestationByAddress(String address) {
		return firestationRepository.findFirestationByAddress(address);
	}

	public List<Firestation> findFirestationByNumber(String station) {
		return firestationRepository.findFirestationByNumber(station);
	}

	public List<String> findPhoneByStation(String station) {
		return firestationRepository.findPhoneByStation(station);
	}

	public Station findByStation(String station) {
		List<Firestation> firestations = firestationRepository.findByStation(station);
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
}
