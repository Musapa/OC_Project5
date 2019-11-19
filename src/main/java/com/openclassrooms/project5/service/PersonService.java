package com.openclassrooms.project5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public Person findPersonByEmail(String email) {
		return personRepository.findPersonByEmail(email);
	}
	
	public Person findPersonByName(String name) {
		return personRepository.findPersonByName(name);
	}
}
