package com.openclassrooms.project5.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.project5.domain.Firestation;
import com.openclassrooms.project5.domain.Person;

@Repository
public class FirestationRepository implements IFirestationRepository {

	private List<Firestation> firestations = new ArrayList<>();

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.repository.IFirestationRepository#add(com.openclassrooms.project5.domain.Firestation)
	 */
	@Override
	public void add(Firestation firestation) {
		firestations.add(firestation);
	}

	// http://localhost:8080/fire?address=<address>
	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.repository.IFirestationRepository#getFirestationByAddress(java.lang.String)
	 */
	@Override
	public Firestation getFirestationByAddress(String address) {
		for (Firestation firestation : firestations) {
			if (firestation.getAddress().equals(address)) {
				return firestation;
			}
		}
		return null;
	}

	// http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.repository.IFirestationRepository#getFirestationHouseholdsByStationNumbers(java.lang.String)
	 */
	@Override
	public List<Firestation> getFirestationHouseholdsByStationNumbers(String station) {

		List<Firestation> result = new ArrayList<>();

		for (Firestation firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				result.add(firestation);
			}
		}
		return result;
	}

	// http://localhost:8080/phoneAlert?firestation=<firestation_number>
	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.repository.IFirestationRepository#getPhoneNumbersByStationNumber(java.lang.String)
	 */
	@Override
	public List<String> getPhoneNumbersByStationNumber(String station) {

		List<String> result = new ArrayList<>();

		for (Firestation firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				for (Person person : firestation.getPersons()) {
					result.add(person.getPhone());
				}
			}
		}
		return result;
	}

	// http://localhost:8080/firestation?stationNumber=<station_number>
	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.repository.IFirestationRepository#getListOfPeopleByStationNumber(java.lang.String)
	 */
	@Override
	public List<Firestation> getListOfPeopleByStationNumber(String station) {

		List<Firestation> result = new ArrayList<>();

		for (Firestation firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				result.add(firestation);
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.repository.IFirestationRepository#createFirestation(com.openclassrooms.project5.domain.Firestation)
	 */
	@Override
	public Firestation createFirestation(Firestation firestation) {
		Firestation findFirestation = getFirestationByAddress(firestation.getAddress());
		if (findFirestation == null) {
			firestations.add(firestation);
			return firestation;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.repository.IFirestationRepository#updateFirestation(com.openclassrooms.project5.domain.Firestation)
	 */
	@Override
	public Firestation updateFirestation(Firestation firestation) {
		Firestation findFirestation = getFirestationByStation(firestation.getStation());
		if (findFirestation != null) {
			findFirestation.setAddress(firestation.getAddress());
			findFirestation.setStation(firestation.getStation());
			return firestation;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.repository.IFirestationRepository#deleteFirestation(com.openclassrooms.project5.domain.Firestation)
	 */
	@Override
	public boolean deleteFirestation(Firestation firestation) {
		for (Firestation findFirestation : firestations) {
			if (findFirestation.getStation().equals(firestation.getStation())
					&& findFirestation.getAddress().equals(firestation.getAddress())) {
				firestations.remove(findFirestation);
				return true;
			}
		}
		return false;
	}

	private Firestation getFirestationByStation(String station) {
		for (Firestation firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				return firestation;
			}
		}
		return null;
	}
}