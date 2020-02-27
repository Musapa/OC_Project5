package com.openclassrooms.project5.repository;

import java.util.List;

import com.openclassrooms.project5.domain.Firestation;

public interface IFirestationRepository {

	void add(Firestation firestation);

	Firestation getFirestationByAddress(List<String> address);

	List<Firestation> getFirestationHouseholdsByStationNumbers(String station);

	List<String> getPhoneNumbersByStationNumber(String station);

	List<Firestation> getListOfPeopleByStationNumber(String station);

	Firestation createFirestation(Firestation firestation);

	Firestation updateFirestation(Firestation firestation);

	boolean deleteFirestation(Firestation firestation);

}