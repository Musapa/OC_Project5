package com.openclassrooms.project5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.dto.ChildAlert;
import com.openclassrooms.project5.repository.IPersonRepository;

@Service
public class PersonService implements IPersonService {

	@Autowired
	private IPersonRepository personRepository;

	@Override
	public List<String> getEmailsByCity(String city) {
		return personRepository.getEmailsByCity(city);
	}

	@Override
	public List<Person> getPersonsInfoByFirstNameLastName(String firstName, String lastName) {
		return personRepository.getPersonsInfoByFirstNameLastName(firstName, lastName);
	}

	@Override
	public ChildAlert getChildrenInfoAlertByAddress(List<String> address) {
		return personRepository.getChildrenInfoAlertByAddress(address);
	}

	@Override
	public Person createPerson(Person person) {
		return personRepository.createPerson(person);
	}

	@Override
	public Person updatePerson(Person person) {
		return personRepository.updatePerson(person);
	}

	@Override
	public boolean deletePerson(Person person) {
		return personRepository.deletePerson(person);
	}

}
