package com.openclassrooms.project5.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.project5.domain.Firestation;
import com.openclassrooms.project5.dto.Station;
import com.openclassrooms.project5.service.FirestationService;

@RestController
public class FirestationController {

	@Autowired
	private FirestationService firestationService;

	// http://localhost:8080/fire?address=<address>
	
	@RequestMapping(value = "/fire", method = RequestMethod.GET, produces = "application/json")
	public Firestation getFirestation(@RequestParam(value = "address") String address) {

		return firestationService.findFirestationByAddress(address);
	}

	// http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	@RequestMapping(value = "/flood/stations", method = RequestMethod.GET, produces = "application/json")
	public List<Firestation> getFirestationNumber(@RequestParam(value = "stations") String station) {
		String[] stations = station.split(",");
		return firestationService.findFirestationByNumbers(stations);
	}

	// http://localhost:8080/phoneAlert?firestation=<firestation_number>
	@RequestMapping(value = "/phoneAlert", method = RequestMethod.GET, produces = "application/json")
	public List<String> getPhoneByStation(@RequestParam(value = "firestation") String station) {

		return new ArrayList<>(new HashSet<>(firestationService.findPhoneByStation(station)));
	}

	// http://localhost:8080/firestation?stationNumber=<station_number>
	@RequestMapping(value = "/firestation", method = RequestMethod.GET, produces = "application/json")
	public Station getListOfStations(@RequestParam(value = "stationNumber") String station) {

		return firestationService.findByStation(station);
	}

}
