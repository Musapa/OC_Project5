package com.openclassrooms.project5.controller;

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

	// TODO what is RequestMehod.GET and http://localhost:8080/fire?address=1509 Culver St
	@RequestMapping(value = "/fire", method = RequestMethod.GET, produces = "application/json")
	public Firestation getFirestation(@RequestParam(value = "address") String address) {

		return firestationService.findFirestationByAddress(address);
	}

}
