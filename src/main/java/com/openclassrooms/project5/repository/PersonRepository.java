package com.openclassrooms.project5.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.project5.domain.Person;

@Repository
public class PersonRepository {

	private List<Person> persons = new ArrayList<>();

	public List<Person> getPersons() {
		return persons;
	}
	public void add(Person person) {
		persons.add(person);
	}
	
	public List<String> findPersonByCity(String city) {
		
		List<String> result = new ArrayList<>();
		
		for(Person person : persons) {
			if (person.getCity().equals(city)) {
				result.add(person.getEmail());
			}
		}
		return result;
	}
	
	public List<Person> findPersonByName(String firstName, String lastName) {
		
		List<Person> result = new ArrayList<>();
		
		for(Person person : persons) {
			if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
				result.add(person);
			}
		}
		return result;
	}
}