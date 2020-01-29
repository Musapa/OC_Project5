package com.openclassrooms.project5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.project5.domain.Firestation;
import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.dto.Station;
import com.openclassrooms.project5.repository.IFirestationRepository;

@Service
public class FirestationService implements IFirestationService {

	@Autowired
	private IFirestationRepository firestationRepository;

	// http://localhost:8080/fire?address=<address>
	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.service.IFirestationService#getFirestationByAddress(java.lang.String)
	 */
	@Override
	public Firestation getFirestationByAddress(String address) {
		return firestationRepository.getFirestationByAddress(address);
	}

	// http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.service.IFirestationService#getFirestationHouseholdsByStationNumbers(java.lang.String[])
	 */
	@Override
	public List<Firestation> getFirestationHouseholdsByStationNumbers(String stations[]) {
		List<Firestation> result = new ArrayList<>();

		for (String station : stations) {
			result.addAll(firestationRepository.getFirestationHouseholdsByStationNumbers(station));
		}
		return result;
	}

	// http://localhost:8080/phoneAlert?firestation=<firestation_number>
	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.service.IFirestationService#getPhoneNumbersByStationNumber(java.lang.String)
	 */
	@Override
	public List<String> getPhoneNumbersByStationNumber(String station) {
		return firestationRepository.getPhoneNumbersByStationNumber(station);
	}

	// http://localhost:8080/firestation?stationNumber=<station_number>
	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.service.IFirestationService#getListOfPeopleByStationNumber(java.lang.String)
	 */
	@Override
	public Station getListOfPeopleByStationNumber(String station) {
		List<Firestation> firestations = firestationRepository.getListOfPeopleByStationNumber(station);
		List<Person> persons = new ArrayList<>();
		int numberOfChildren = 0;
		int numberOfAdults = 0;

		for (Firestation firestation : firestations) {
			for (Person person : firestation.getPersons()) {
				persons.add(person);

				if (person.isChild()) {
					numberOfChildren++;
				} else {
					numberOfAdults++;
				}
			}
		}
		return new Station(station, persons, numberOfAdults, numberOfChildren);
	}

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.service.IFirestationService#createFirestation(com.openclassrooms.project5.domain.Firestation)
	 */
	@Override
	public Firestation createFirestation(Firestation firestation) {
		return firestationRepository.createFirestation(firestation);
	}

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.service.IFirestationService#updateFirestation(com.openclassrooms.project5.domain.Firestation)
	 */
	@Override
	public Firestation updateFirestation(Firestation firestation) {
		return firestationRepository.updateFirestation(firestation);
	}

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.service.IFirestationService#deleteFirestation(com.openclassrooms.project5.domain.Firestation)
	 */
	@Override
	public boolean deleteFirestation(Firestation firestation) {
		return firestationRepository.deleteFirestation(firestation);
	}

}
