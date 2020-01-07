package com.openclassrooms.project5.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

		if (firestation != null) {
			log.info("Found station number: " + firestation.getStation() + "by " + address);
			return ResponseEntity.ok().body(firestation);
		} else {
			log.error("Cannot find station with " + address);
			return ResponseEntity.notFound().build();
		}

	}

	// http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	@RequestMapping(value = "/flood/stations", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Firestation>> getFirestationHouseholdsByStationNumbers(@RequestParam(value = "stations") String station) {
		log.info("Get Firestation with station number " + station);
		String[] stations = station.split(",");
		List<Firestation> firestation = firestationService.getFirestationHouseholdsByStationNumbers(stations);

		if (firestation.size() > 0) {
			log.info("On station " + station + " found " + firestation.size() + " households.");
			return ResponseEntity.status(HttpStatus.OK).body(firestation);
		} else {
			log.error("Cannot find households with " + station);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(firestation);
		}
	}

	// http://localhost:8080/phoneAlert?firestation=<firestation_number>
	@RequestMapping(value = "/phoneAlert", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<String>> getPhoneNumbersByStationNumber(@RequestParam(value = "firestation") String station) {
		log.info("Get phone numbers by station number " + station);
		List<String> result = new ArrayList<>(
		new HashSet<>(firestationService.getPhoneNumbersByStationNumber(station)));

		if (result.size() > 0) {
			log.info("There are " + result.size() + " phone numbers in station number " + station);
			return ResponseEntity.ok().body(result);
		} else {
			log.error("Cannot find phone numbers with" + station);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		}
	}

	// http://localhost:8080/firestation?stationNumber=<station_number>
	@RequestMapping(value = "/firestation", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Station> getListOfPeopleByStationNumber(@RequestParam(value = "stationNumber") String stationNumber) {
		log.info("Get a list of people (adults and children) serviced by station number: " + stationNumber);
		Station station = firestationService.getListOfPeopleByStationNumber(stationNumber);

		if (station.getPersons().size() > 0) {
			log.info("Number of adults:" + station.getNumberOfAdults() + " Number of children:"
					+ station.getNumberOfChildren());
			return ResponseEntity.ok().body(station);
		} else {
			log.error("Cannot find phone numbers with" + stationNumber);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(station);
		}
	}
	// ---------- END OF URLs ----------

	
	// ---------- ENDPOINTS ----------
	
	@RequestMapping(value = "/firestation", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<FirestationDTO> addFirestaion(@RequestBody FirestationDTO firestationDTO) {
		Firestation firestation = convertToEntity(firestationDTO);
		Firestation firestationCreated = firestationService.createFirestation(firestation);
		return ResponseEntity.ok().body(convertToDto(firestationCreated));
	}

	@RequestMapping(value = "/firestation", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<FirestationDTO> updateFirestaion(@RequestBody FirestationDTO firestationDTO) {
		Firestation firestation = convertToEntity(firestationDTO);
		//TODO update Firestation
		Firestation firestationUpdated = firestationService.updateFirestation(firestation);
		return ResponseEntity.ok().body(convertToDto(firestationUpdated));
	}

	@RequestMapping(value = "/firestation", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<FirestationDTO> deleteFirestaion(@RequestBody FirestationDTO firestationDTO) {
		Firestation firestation = convertToEntity(firestationDTO);
		
		if (firestationService.deleteFirestation(firestation)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	// ---------- END OF ENDPOINTS ----------
	
	private FirestationDTO convertToDto(Firestation firestation) {
		return new FirestationDTO(firestation.getAddress(), firestation.getStation());
	}
	
	private Firestation convertToEntity(FirestationDTO firestationDTO) {
		return new Firestation(firestationDTO.getAddress(), firestationDTO.getStation());
	}
}
