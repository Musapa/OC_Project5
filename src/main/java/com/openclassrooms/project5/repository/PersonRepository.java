package com.openclassrooms.project5.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.exception.ApiException;

@Repository
public class PersonRepository {

	private List<Person> persons = new ArrayList<>();

	public List<Person> getPersons() {
		return persons;
	}
	public void add(Person person) {
		persons.add(person);
	}
	
	public Person findPersonByEmail(String email) {
		for(Person person : persons) {
			if (person.getEmail().equals(email)) {
				return person;
			}
		}
		throw new ApiException("Cannot find email.");
	}
	
	public Person findPersonByName(String firstName) {
		for(Person person : persons) {
			if (person.getFirstName().equals(firstName)) {
				return person;
			}
		}
		throw new ApiException("Cannot find name.");
	}
}