package com.openclassrooms.project5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public List<String> findPersonByCity(String city) {
		return personRepository.findPersonByCity(city);
	}
	
	public List<Person> findPersonByName(String firstName, String lastName) {
		return personRepository.findPersonByName(firstName, lastName);
	}
	
	public List<Person> findPersonByAdress(String address) {
		return personRepository.findPersonByAdress(address);
	}
	
}
