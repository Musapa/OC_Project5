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
import com.openclassrooms.project5.repository.FirestationRepository;
import com.openclassrooms.project5.repository.MedicalRecordRepository;
import com.openclassrooms.project5.repository.PersonRepository;

@Component
public class JSONLoader {

	private FirestationRepository firestationRepository;
	private MedicalRecordRepository medicalRecordRepository;
	private PersonRepository personRepository;

	// constuctor injecton show that repository is created first
	public JSONLoader(@Autowired FirestationRepository firestationRepository,
			@Autowired MedicalRecordRepository medicalRecordRepository, @Autowired PersonRepository personRepository)
			throws IOException {

		this.firestationRepository = firestationRepository;
		this.medicalRecordRepository = medicalRecordRepository;
		this.personRepository = personRepository;

		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("data.json");
		String data = readFromInputStream(inputStream);
		//System.out.println("Data " + data);
		JSONData jsonData = JsonIterator.deserialize(data, JSONData.class);
		loadRepository(jsonData);
		//System.out.println("Testing " + personRepository.getPersons().size());
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

		HashMap<String, Firestation> firestationMap = new HashMap<>();
		for (Firestation firestation : jsonData.firestations) {
			firestationMap.put(firestation.getAddress(), firestation);
			firestationRepository.add(firestation);
		}

		HashMap<String, MedicalRecord> medicalrecordMap = new HashMap<>();
		for (MedicalRecord medicalrecord : jsonData.medicalrecords) {
			medicalrecordMap.put(medicalrecord.getFirstName() + medicalrecord.getLastName(), medicalrecord);
			medicalRecordRepository.add(medicalrecord);
		}

		for (PersonData personData : jsonData.persons) {
			Firestation firestation = firestationMap.get(personData.getAddress());
			MedicalRecord medicalRecord = medicalrecordMap.get(personData.getFirstName() + personData.getLastName());

			personRepository.add(new Person(personData, medicalRecord, firestation));
		}
	}

}
