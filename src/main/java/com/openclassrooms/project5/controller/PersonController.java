package com.openclassrooms.project5.controller;

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
	public Person getPersonsByEmail(@RequestParam(value = "city") String email) {

		return personService.findPersonByEmail(email);
	}
	
	@RequestMapping(value = "/personInfo", method = RequestMethod.GET, produces = "application/json")
	public Person getPersonsInfo(@RequestParam(value = "firstName") String name) {

		return personService.findPersonByName(name);
	}
	
}
