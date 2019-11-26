package com.openclassrooms.project5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.project5.domain.Firestation;
import com.openclassrooms.project5.service.FirestationService;

@RestController
public class FirestationController {

	@Autowired
	private FirestationService firestationService;

	@RequestMapping(value = "/fire", method = RequestMethod.GET, produces = "application/json")
	public Firestation getFirestation(@RequestParam(value = "address") String address) {

		return firestationService.findFirestationByAddress(address);
	}
	
	@RequestMapping(value = "/firestation", method = RequestMethod.GET, produces = "application/json")
	public List<Firestation> getFirestationNumber(@RequestParam(value = "stationNumber") String station) {

		return firestationService.findFirestationByNumber(station);
	}
	
	@RequestMapping(value = "/phoneAlert", method = RequestMethod.GET, produces = "application/json")
	public List<String> getPhoneByStation(@RequestParam(value = "firestation") String station) {

		return firestationService.findPhoneByStation(station);
	}
}
