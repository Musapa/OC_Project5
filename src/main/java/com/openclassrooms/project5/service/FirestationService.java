package com.openclassrooms.project5.service;

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
}
