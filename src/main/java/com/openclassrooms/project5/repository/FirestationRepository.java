package com.openclassrooms.project5.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.project5.domain.Firestation;
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
}