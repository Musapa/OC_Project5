package com.openclassrooms.project5.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.project5.domain.Firestation;

@Repository
public class FirestationRepository {

	private List<Firestation> firestations = new ArrayList<>();

	List<Firestation> getFirestation() {
		return firestations;
	}

}