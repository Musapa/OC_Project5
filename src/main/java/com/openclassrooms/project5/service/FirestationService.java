package com.openclassrooms.project5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.project5.domain.Firestation;
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
	
	public List<Firestation> findPeopleByStations(String station) {
		return firestationRepository.findPeopleByStations(station);
	}
}
