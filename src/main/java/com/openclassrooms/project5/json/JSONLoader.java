package com.openclassrooms.project5.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsoniter.JsonIterator;
import com.openclassrooms.project5.domain.Firestation;
import com.openclassrooms.project5.domain.MedicalRecord;
import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.repository.IFirestationRepository;
import com.openclassrooms.project5.repository.IMedicalRecordRepository;
import com.openclassrooms.project5.repository.IPersonRepository;

@Component
public class JSONLoader {

	private IFirestationRepository firestationRepository;
	private IMedicalRecordRepository medicalRecordRepository;
	private IPersonRepository personRepository;

	// constuctor injecton show that repository is created first
	public JSONLoader(@Autowired IFirestationRepository firestationRepository,
			@Autowired IMedicalRecordRepository medicalRecordRepository, @Autowired IPersonRepository personRepository)
			throws IOException {

		this.firestationRepository = firestationRepository;
		this.medicalRecordRepository = medicalRecordRepository;
		this.personRepository = personRepository;

		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("data.json");
		String data = readFromInputStream(inputStream);
		// System.out.println("Data " + data);
		// from JSON to object, with class specified
		JSONData jsonData = JsonIterator.deserialize(data, JSONData.class);
		loadRepository(jsonData);
	}

	private String readFromInputStream(InputStream inputStream) throws IOException {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}
		return resultStringBuilder.toString();
	}

	private void loadRepository(JSONData jsonData) {

		HashMap<String, Firestation> firestationAddressMap = new HashMap<>();
		HashMap<String, Firestation> firestationMap = new HashMap<>();

		for (FirestationData firestationData : jsonData.firestations) {
			Firestation firestation = firestationMap.get(firestationData.getStation());

			if (firestation == null) {
				firestation = new Firestation();
				firestation.setStation(firestationData.getStation());
				firestationRepository.add(firestation);
			}
			firestationAddressMap.put(firestationData.getAddress(), firestation);
			firestation.addAddress(firestationData.getAddress());
		}

		HashMap<String, MedicalRecord> medicalrecordMap = new HashMap<>();
		for (MedicalRecord medicalrecord : jsonData.medicalrecords) {
			medicalrecordMap.put(medicalrecord.getFirstName() + medicalrecord.getLastName(), medicalrecord);
			medicalRecordRepository.add(medicalrecord);
		}

		for (Person person : jsonData.persons) {
			MedicalRecord medicalRecord = medicalrecordMap.get(person.getFirstName() + person.getLastName());

			if (medicalRecord != null) {
				person.setMedicalRecord(medicalRecord);
			}

			personRepository.add(person);

			Firestation firestation = firestationAddressMap.get(person.getAddress());

			if (firestation != null) {
				firestation.getPersons().add(person);
			}
		}
	}

}
