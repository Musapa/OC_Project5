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

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.service.IPersonService#getEmailsByCity(java.lang.String)
	 */
	@Override
	public List<String> getEmailsByCity(String city) {
		return personRepository.getEmailsByCity(city);
	}

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.service.IPersonService#getPersonsInfoByFirstNameLastName(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Person> getPersonsInfoByFirstNameLastName(String firstName, String lastName) {
		return personRepository.getPersonsInfoByFirstNameLastName(firstName, lastName);
	}

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.service.IPersonService#getChildrenInfoAlertByAddress(java.lang.String)
	 */
	@Override
	public ChildAlert getChildrenInfoAlertByAddress(String address) {
		return personRepository.getChildrenInfoAlertByAddress(address);
	}

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.service.IPersonService#createPerson(com.openclassrooms.project5.domain.Person)
	 */
	@Override
	public Person createPerson(Person person) {
		return personRepository.createPerson(person);
	}

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.service.IPersonService#updatePerson(com.openclassrooms.project5.domain.Person)
	 */
	@Override
	public Person updatePerson(Person person) {
		return personRepository.updatePerson(person);
	}

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.service.IPersonService#deletePerson(com.openclassrooms.project5.domain.Person)
	 */
	@Override
	public boolean deletePerson(Person person) {
		return personRepository.deletePerson(person);
	}

}
