package com.openclassrooms.project5.json;

import java.util.HashMap;

import com.openclassrooms.project5.domain.Firestation;
import com.openclassrooms.project5.domain.MedicalRecord;
import com.openclassrooms.project5.json.PersonData;

public class PersonData {

	private void loadRepository(JSONData jsonData) {
	HashMap<String, PersonData> personMap = new HashMap<>();
	HashMap<String, Firestation> firestationMap = new HashMap<>();
	HashMap<String, MedicalRecord> medicalrecordMap = new HashMap<>();
	
	for (PersonData personData : jsonData.persons) {
		Firestation firestation= firestationMap.get(personData.getAddress();
		MedicalRecord medicalRecord= medicalRecordMap.get(personData.getAddress();
		// check that firestation and medical are not null and throw exception if null

		firestationRepository.add(firestation);
		medicalRepository.add(medicalRecord);
		personRepository.add(new Person(firestation, medicalrecord, personData));
	}
	
	}
	
}
