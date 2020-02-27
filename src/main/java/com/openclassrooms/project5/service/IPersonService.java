package com.openclassrooms.project5.service;

import java.util.List;

import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.dto.ChildAlert;

public interface IPersonService {

	List<String> getEmailsByCity(String city);

	List<Person> getPersonsInfoByFirstNameLastName(String firstName, String lastName);

	ChildAlert getChildrenInfoAlertByAddress(List<String> address);

	Person createPerson(Person person);

	Person updatePerson(Person person);

	boolean deletePerson(Person person);

}