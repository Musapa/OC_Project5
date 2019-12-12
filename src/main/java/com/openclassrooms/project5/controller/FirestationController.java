package com.openclassrooms.project5.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private static final Logger log = LoggerFactory.getLogger(FirestationController.class);
	
	@Autowired
	private FirestationService firestationService;

	// http://localhost:8080/fire?address=<address>
	@RequestMapping(value = "/fire", method = RequestMethod.GET, produces = "application/json")
	public Firestation getFirestation(@RequestParam(value = "address") String address) {
		log.info("Get Firestation with address: " + address);
		Firestation firestation = firestationService.findFirestationByAddress(address);
		log.info("Found station number: " + firestation.getStation() + "by " + address);
		return firestation;
	}

	// http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	@RequestMapping(value = "/flood/stations", method = RequestMethod.GET, produces = "application/json")
	public List<Firestation> getFirestationNumber(@RequestParam(value = "stations") String station) {
		log.info("Get Firestation with station number " + station);
		String[] stations = station.split(",");
		List<Firestation> firestation = firestationService.findFirestationByNumbers(stations);
		log.info("On station " + station + " found " + firestation.size() + " households.");
		return firestation;
	}

	// http://localhost:8080/phoneAlert?firestation=<firestation_number>
	@RequestMapping(value = "/phoneAlert", method = RequestMethod.GET, produces = "application/json")
	public List<String> getPhoneAlert(@RequestParam(value = "firestation") String station) {
		log.info("Get phone numbers by station number " + station);
		return new ArrayList<>(new HashSet<>(firestationService.findPhoneByStation(station)));
	}

	// http://localhost:8080/firestation?stationNumber=<station_number>
	@RequestMapping(value = "/firestation", method = RequestMethod.GET, produces = "application/json")
	public Station getListOfStations(@RequestParam(value = "stationNumber") String station) {
		log.info("Get a list of people (adults and children) serviced by station number: " + station);
		Station firestation = firestationService.findByStation(station);
		log.info("Number of adults:" + firestation.getNumberOfAdults() + " Number of children:" + firestation.getNumberOfChildren());
		return firestation;
	}

}
