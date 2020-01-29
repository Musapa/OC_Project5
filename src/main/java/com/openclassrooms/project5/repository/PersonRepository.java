package com.openclassrooms.project5.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.dto.ChildAlert;

@Repository
public class PersonRepository implements IPersonRepository {

	private List<Person> persons = new ArrayList<>();

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.repository.IPersonRepository#add(com.openclassrooms.project5.domain.Person)
	 */
	@Override
	public void add(Person person) {
		persons.add(person);
	}

	// http://localhost:8080/communityEmail?city=<city>
	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.repository.IPersonRepository#getEmailsByCity(java.lang.String)
	 */
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

	// http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.repository.IPersonRepository#getPersonsInfoByFirstNameLastName(java.lang.String, java.lang.String)
	 */
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

	// http://localhost:8080/childAlert?address=<address>
	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.repository.IPersonRepository#getChildrenInfoAlertByAddress(java.lang.String)
	 */
	@Override
	public ChildAlert getChildrenInfoAlertByAddress(String address) {

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

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.repository.IPersonRepository#createPerson(com.openclassrooms.project5.domain.Person)
	 */
	@Override
	public Person createPerson(Person person) {
		Person findPerson = getPersonFirstLastName(person.getFirstName(), person.getLastName());
		if (findPerson == null) {
			persons.add(person);
			return person;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.repository.IPersonRepository#updatePerson(com.openclassrooms.project5.domain.Person)
	 */
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

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.repository.IPersonRepository#deletePerson(com.openclassrooms.project5.domain.Person)
	 */
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