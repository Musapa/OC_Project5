package com.openclassrooms.project5.repository;

import java.util.List;

import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.dto.ChildAlert;

public interface IPersonRepository {

	void add(Person person);

	// http://localhost:8080/communityEmail?city=<city>
	List<String> getEmailsByCity(String city);

	// http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	List<Person> getPersonsInfoByFirstNameLastName(String firstName, String lastName);

	// http://localhost:8080/childAlert?address=<address>
	ChildAlert getChildrenInfoAlertByAddress(String address);

	Person createPerson(Person person);

	Person updatePerson(Person person);

	boolean deletePerson(Person person);

}