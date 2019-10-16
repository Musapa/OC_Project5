package com.openclassrooms.project5.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openclassrooms.project5.repository.PersonRepository;

@Component
public class JSONLoader {
	
	@Autowired
	private PersonRepository personRepository;
	
	
	public JSONLoader() {
		//TODO read JSON file and load into JSONData object
		//insert into to the repository
	}
	
}
