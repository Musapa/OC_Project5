package com.openclassrooms.project5.repository;

import java.util.List;

import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.dto.ChildAlert;

public interface IPersonRepository {

	void add(Person person);

	List<String> getEmailsByCity(String city);

	List<Person> getPersonsInfoByFirstNameLastName(String firstName, String lastName);

	ChildAlert getChildrenInfoAlertByAddress(List<String> address);

	Person createPerson(Person person);

	Person updatePerson(Person person);

	boolean deletePerson(Person person);

}