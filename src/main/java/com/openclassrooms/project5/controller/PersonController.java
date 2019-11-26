package com.openclassrooms.project5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value = "/communityEmail", method = RequestMethod.GET, produces = "application/json")
	public List<String> getPersonsByEmail(@RequestParam(value = "city") String city) {

		return personService.findPersonByCity(city);
	}
	
	@RequestMapping(value = "/personInfo", method = RequestMethod.GET, produces = "application/json")
	public List<Person> getPersonsInfo(@RequestParam(value = "firstName") String firstName, String lastName) {

		return personService.findPersonByName(firstName, lastName);
	}
	
	@RequestMapping(value = "/childAlert", method = RequestMethod.GET, produces = "application/json")
	public List<Person> getChildInfo(@RequestParam(value = "address") String address) {

		return personService.findPersonByAdress(address);
	}
	
}