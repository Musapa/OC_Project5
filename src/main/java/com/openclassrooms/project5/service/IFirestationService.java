package com.openclassrooms.project5.service;

import java.util.List;

import com.openclassrooms.project5.domain.Firestation;
import com.openclassrooms.project5.dto.Station;

public interface IFirestationService {

	// http://localhost:8080/fire?address=<address>
	Firestation getFirestationByAddress(String address);

	// http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	List<Firestation> getFirestationHouseholdsByStationNumbers(String stations[]);

	// http://localhost:8080/phoneAlert?firestation=<firestation_number>
	List<String> getPhoneNumbersByStationNumber(String station);

	// http://localhost:8080/firestation?stationNumber=<station_number>
	Station getListOfPeopleByStationNumber(String station);

	Firestation createFirestation(Firestation firestation);

	Firestation updateFirestation(Firestation firestation);

	boolean deleteFirestation(Firestation firestation);

}