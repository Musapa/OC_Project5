package com.openclassrooms.project5.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.dto.ChildAlert;

@Repository
public class PersonRepository {

	private List<Person> persons = new ArrayList<>();

	public List<Person> getPersons() {
		return persons;
	}

	public void add(Person person) {
		persons.add(person);
	}

	// http://localhost:8080/communityEmail?city=<city>
	public List<String> findPersonByCity(String city) {

		List<String> result = new ArrayList<>();

		for (Person person : persons) {
			if (person.getCity().equals(city)) {
				result.add(person.getEmail());
			}
		}
		return result;
	}

	// http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	public List<Person> findPersonByName(String firstName, String lastName) {

		List<Person> result = new ArrayList<>();

		for (Person person : persons) {
			if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
				result.add(person);
			}
		}
		return result;
	}

	// http://localhost:8080/childAlert?address=<address>
	public ChildAlert findChildAlertByAddress(String address) {

		ChildAlert result = new ChildAlert();
		long now = new Date().getTime();

		for (Person person : persons) {
			if (person.getAddress().equals(address)) {
				if ((now - (365 * 18 * 24 * 60 * 60 * 1000L) > person.getMedicalRecord().getBirthdate().getTime())) {
					result.getAdults().add(person);
				} else {
					result.getChildren().add(person);
				}
			}
		}
		return result;
	}
}