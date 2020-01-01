package com.openclassrooms.project5.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.dto.ChildAlert;
import com.openclassrooms.project5.service.PersonService;

@RestController
public class PersonController {

	private static final Logger log = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;

	// ---------- URLs ----------
	
	// http://localhost:8080/communityEmail?city=<city>
	@RequestMapping(value = "/communityEmail", method = RequestMethod.GET, produces = "application/json")
	public List<String> getEmailsByCity(@RequestParam(value = "city") String city) {
		log.info("Get email address on city: " + city);
		List<String> email = personService.getEmailsByCity(city);
		log.info("Found " + email.size() + " email addresses on " + city);
		return email;
	}

	// http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	@RequestMapping(value = "/personInfo", method = RequestMethod.GET, produces = "application/json")
	public List<Person> getPersonsInfoByName(@RequestParam(value = "firstName") String firstName, String lastName) {
		log.info("Get first name of person: " + firstName + " and get last name of person: " + lastName);
		List<Person> personInfo = personService.getPersonInfoByName(firstName, lastName);
		log.info("There is a " + personInfo.size() + " person");
		return personInfo;
	}

	// http://localhost:8080/childAlert?address=<address>
	@RequestMapping(value = "/childAlert", method = RequestMethod.GET, produces = "application/json")
	public ChildAlert getChildAlertByAddress(@RequestParam(value = "address") String address) {
		log.info("Get number of children and adults on: " + address);
		ChildAlert childInfo = personService.getChildAlertByAddress(address);
		log.info("There are " + childInfo.getChildren().size() + " childrens and " + childInfo.getAdults().size() + " adults on " + address);
		return childInfo;
	}
	// ---------- END OF URLs ----------
	
	/*@RequestMapping(value = "/person", method = RequestMethod.POST, produces = "application/json")
	public Person createPerson(@RequestParam(value = "firstName") String firstName) {
		
	}*/
	
	
}
