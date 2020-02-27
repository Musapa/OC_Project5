package com.openclassrooms.project5.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.dto.ChildAlert;

@Repository
public class PersonRepository implements IPersonRepository {

	private List<Person> persons = new ArrayList<>();

	@Override
	public void add(Person person) {
		persons.add(person);
	}

	@Override
	public List<String> getEmailsByCity(String city) {

		List<String> result = new ArrayList<>();

		for (Person person : persons) {
			if (person.getCity().equals(city)) {
				result.add(person.getEmail());
			}
		}
		return result;
	}

	@Override
	public List<Person> getPersonsInfoByFirstNameLastName(String firstName, String lastName) {

		List<Person> result = new ArrayList<>();

		for (Person person : persons) {
			if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
				result.add(person);
			}
		}
		return result;
	}

	@Override
	public ChildAlert getChildrenInfoAlertByAddress(List<String> address) {

		ChildAlert result = new ChildAlert();

		for (Person person : persons) {
			if (person.getAddress().equals(address)) {
				if (person.isChild()) {
					result.getChildren().add(person);
				} else {
					result.getAdults().add(person);
				}
			}
		}
		return result;
	}

	@Override
	public Person createPerson(Person person) {
		Person findPerson = getPersonFirstLastName(person.getFirstName(), person.getLastName());
		if (findPerson == null) {
			persons.add(person);
			return person;
		}
		return null;
	}

	@Override
	public Person updatePerson(Person person) {
		Person findPerson = getPersonFirstLastName(person.getFirstName(), person.getLastName());
		if (findPerson != null) {
			findPerson.setFirstName(person.getFirstName());
			findPerson.setLastName(person.getLastName());
			findPerson.setAddress(person.getAddress());
			findPerson.setCity(person.getCity());
			findPerson.setEmail(person.getEmail());
			findPerson.setMedicalRecord(person.getMedicalRecord());
			findPerson.setPhone(person.getPhone());
			findPerson.setZip(person.getZip());
			return person;
		}
		return null;
	}

	@Override
	public boolean deletePerson(Person person) {
		for (Person findPerson : persons) {
			if (findPerson.getFirstName().equals(person.getFirstName())
					&& findPerson.getLastName().equals(person.getLastName())) {
				persons.remove(findPerson);
				return true;
			}
		}
		return false;
	}

	private Person getPersonFirstLastName(String firstName, String lastName) {
		for (Person person : persons) {
			if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
				return person;
			}
		}
		return null;
	}

}