package com.openclassrooms.project5.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.project5.domain.Person;

@Repository
public class PersonRepository {

	private List<Person> persons = new ArrayList<>();

	List<Person> getPersons() {
		return persons;
	}

}