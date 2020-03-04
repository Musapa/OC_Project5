package com.openclassrooms.project5.service;

import java.util.List;

import com.openclassrooms.project5.domain.Firestation;
import com.openclassrooms.project5.dto.Station;

public interface IFirestationService {


	Firestation getFirestationByAddress(String address);

	List<Firestation> getFirestationHouseholdsByStationNumbers(String stations[]);

	List<String> getPhoneNumbersByStationNumber(String station);

	Station getListOfPeopleByStationNumber(String station);

	Firestation createFirestation(Firestation firestation);

	Firestation updateFirestation(Firestation firestation);

	boolean deleteFirestation(Firestation firestation);

}