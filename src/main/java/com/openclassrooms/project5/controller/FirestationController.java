package com.openclassrooms.project5.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.project5.domain.Firestation;
import com.openclassrooms.project5.dto.FirestationDTO;
import com.openclassrooms.project5.dto.Station;
import com.openclassrooms.project5.service.FirestationService;

@RestController
public class FirestationController {

	private static final Logger log = LoggerFactory.getLogger(FirestationController.class);

	@Autowired
	private FirestationService firestationService;

	// ---------- URLs ----------
	
	// http://localhost:8080/fire?address=<address>
	@RequestMapping(value = "/fire", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Firestation> getFirestationByAddress(@RequestParam(value = "address") String address) {
		log.info("Get Firestation with address: " + address);
		Firestation firestation = firestationService.getFirestationByAddress(address);
		log.info("Found station number: " + firestation.getStation() + "by " + address);

	    return ResponseEntity.ok().body(firestation);
	
	}

	// http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	@RequestMapping(value = "/flood/stations", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Firestation>> getFirestationHouseholdsByStationNumbers(@RequestParam(value = "stations") String station) {
		log.info("Get Firestation with station number " + station);
		String[] stations = station.split(",");
		List<Firestation> firestation = firestationService.getFirestationHouseholdsByStationNumbers(stations);
		log.info("On station " + station + " found " + firestation.size() + " households.");
				
	    if (station == "1" || station == "2" || station == "3" || station == "4" || station == "5") {
	        return ResponseEntity.status(HttpStatus.OK).body(firestation);
	    } 
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(firestation);
	}

	// http://localhost:8080/phoneAlert?firestation=<firestation_number>
	@RequestMapping(value = "/phoneAlert", method = RequestMethod.GET, produces = "application/json")
	public List<String> getPhoneNumbersByStationNumber(@RequestParam(value = "firestation") String station) {
		log.info("Get phone numbers by station number " + station);
		List<String> result = new ArrayList<>(
				new HashSet<>(firestationService.getPhoneNumbersByStationNumber(station)));
		log.info("There are " + result.size() + " phone nmubers in station number " + station);
		return result;
	}

	// http://localhost:8080/firestation?stationNumber=<station_number>
	@RequestMapping(value = "/firestation", method = RequestMethod.GET, produces = "application/json")
	public Station getListOfPeopleByStationNumber(@RequestParam(value = "stationNumber") String station) {
		log.info("Get a list of people (adults and children) serviced by station number: " + station);
		Station firestation = firestationService.getListOfPeopleByStationNumber(station);
		log.info("Number of adults:" + firestation.getNumberOfAdults() + " Number of children:"
				+ firestation.getNumberOfChildren());
		return firestation;
	}
	// ---------- END OF URLs ----------

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public FirestationDTO addFirestaion(@RequestBody FirestationDTO firestationDTO) {
		Firestation firestation = convertToEntity(firestationDTO);
		Firestation firestationCreated = firestationService.createFirestation(firestation);
		return convertToDto(firestationCreated);
	}

	public Firestation updateFirestaion() {
		return null;
	}

	public Firestation deleteFirestaion() {
		return null;
	}

	private Firestation convertToEntity(FirestationDTO firestationDTO) {
		return new Firestation(firestationDTO.getAddress(), firestationDTO.getStation());
	}

	private FirestationDTO convertToDto(Firestation firestation) {
		return new FirestationDTO(firestation.getAddress(), firestation.getStation());
	}
}
