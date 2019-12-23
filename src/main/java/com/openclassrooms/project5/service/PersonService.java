package com.openclassrooms.project5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.dto.ChildAlert;
import com.openclassrooms.project5.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	// ---------- URLs ----------
	
	// http://localhost:8080/communityEmail?city=<city>
	public List<String> getEmailsByCity(String city) {
		return personRepository.getEmailsByCity(city);
	}

	// http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	public List<Person> getPersonInfoByName(String firstName, String lastName) {
		return personRepository.getPersonInfoByName(firstName, lastName);
	}
	
	// http://localhost:8080/childAlert?address=<address>
	public ChildAlert getChildAlertByAddress(String address) {
		return personRepository.getChildAlertByAddress(address);
	}
	// ---------- END OF URLs ----------
}
