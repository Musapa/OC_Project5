package com.openclassrooms.project5.controller;

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

import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.dto.ChildAlert;
import com.openclassrooms.project5.dto.PersonDTO;
import com.openclassrooms.project5.service.PersonService;

@RestController
public class PersonController {

	private static final Logger log = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;

	// ---------- URLs ----------
	
	// http://localhost:8080/communityEmail?city=<city>
	@RequestMapping(value = "/communityEmail", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<String>> getEmailsByCity(@RequestParam(value = "city") String city) {
		log.info("Get email address on city: " + city);
		List<String> email = personService.getEmailsByCity(city);
		
		if (email.size() > 0) {
			log.info("Found " + email.size() + " email addresses on " + city);
			return ResponseEntity.ok().body(email);
		} else {
			log.error("Cannot find email addres with " + city);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(email);
		}
	}

	// http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	@RequestMapping(value = "/personInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Person>> getPersonsInfoByFirstNameLastName(@RequestParam(value = "firstName") String firstName, String lastName) {
		log.info("Get first name of person: " + firstName + " and get last name of person: " + lastName);
		List<Person> personInfo = personService.getPersonsInfoByFirstNameLastName(firstName, lastName);
				
		if (personInfo.size() > 0) {
			log.info("There is a " + personInfo.size() + " person");
			return ResponseEntity.ok().body(personInfo);
		} else {
			log.error("Cannot find persons with " + firstName + " " + lastName);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(personInfo);
		}
	}

	// http://localhost:8080/childAlert?address=<address>
	@RequestMapping(value = "/childAlert", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ChildAlert> getChildrenInfoAlertByAddress(@RequestParam(value = "address") String address) {
		log.info("Get number of children and adults on: " + address);
		ChildAlert childInfo = personService.getChildrenInfoAlertByAddress(address);
				
		if (childInfo.getChildren().size() > 0 && childInfo.getAdults().size() > 0) {
			log.info("There are " + childInfo.getChildren().size() + " childrens and " + childInfo.getAdults().size() + " adults on " + address);
			return ResponseEntity.ok().body(childInfo);
		} else {
			log.error("Cannot find person with address: " + address);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(childInfo);
		}
	}
	
	// ---------- END OF URLs ----------
	
	
	// ---------- ENDPOINTS ----------
	
	@RequestMapping(value = "/person", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
		Person person = convertToEntity(personDTO);
		Person personCreated = personService.createPerson(person);
		return ResponseEntity.ok().body(convertToDto(personCreated));
	}
	
	@RequestMapping(value = "/person", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO personDTO) {
		Person person = convertToEntity(personDTO);
		//TODO update Person
		Person personUpdated = personService.updatePerson(person);
		return ResponseEntity.ok().body(convertToDto(personUpdated));
	}
	
	@RequestMapping(value = "/person", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<PersonDTO> deletePerson(@RequestBody PersonDTO personDTO) {
		Person person = convertToEntity(personDTO);
		
		if (personService.deletePerson(person)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	// ---------- END OF ENDPOINTS ----------
	
	private PersonDTO convertToDto(Person person) {
		return new PersonDTO(person.getFirstName(), person.getLastName(), person.getPhone(), person.getZip(), person.getAddress(), person.getCity(), person.getEmail(), person.getMedicalRecord());
	}
	
	private Person convertToEntity(PersonDTO personDTO) {
		return new Person(personDTO.getFirstName(), personDTO.getLastName(), personDTO.getPhone(), personDTO.getZip(), personDTO.getAddress(), personDTO.getCity(), personDTO.getEmail(), personDTO.getMedicalRecord());
	}
}
