package com.openclassrooms.project5.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.project5.domain.MedicalRecord;

@Repository
public class MedicalRecordRepository {

	private List<MedicalRecord> medicalRecords = new ArrayList<>();

	public List<MedicalRecord> getMedicalRecord() {
		return medicalRecords;
	}

	public void add(MedicalRecord medicalRecord) {
		medicalRecords.add(medicalRecord);
	}

	public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
		MedicalRecord findMedicalRecord = getMedicalRecordByFirstLastName(medicalRecord.getFirstName(), medicalRecord.getLastName());
		if (findMedicalRecord == null) {
			medicalRecords.add(medicalRecord);
			return medicalRecord;
		}
		return null;
	}

	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
		MedicalRecord findMedicalRecord = getMedicalRecordByFirstLastName(medicalRecord.getFirstName(), medicalRecord.getLastName());
		if (findMedicalRecord != null) {
			findMedicalRecord.setFirstName(medicalRecord.getFirstName());
			findMedicalRecord.setLastName(medicalRecord.getLastName());
			findMedicalRecord.setBirthdate(medicalRecord.getBirthdate());
			findMedicalRecord.setMedications(medicalRecord.getMedications());
			findMedicalRecord.setAllergies(medicalRecord.getAllergies());
			return medicalRecord;
		}
		return null;
	}

	public boolean deleteMedicalRecord(MedicalRecord medicalRecord) {
		for (MedicalRecord findMedicalRecord : medicalRecords) {
			if (findMedicalRecord.getFirstName().equals(medicalRecord.getFirstName())
					&& findMedicalRecord.getLastName().equals(medicalRecord.getLastName())) {
				medicalRecords.remove(findMedicalRecord);
				return true;
			}
		}
		return false;
	}

	private MedicalRecord getMedicalRecordByFirstLastName(String firstName, String lastName) {
		for (MedicalRecord medicalRecord : medicalRecords) {
			if (medicalRecord.getFirstName().equals(firstName) & medicalRecord.getLastName().equals(lastName)) {
				return medicalRecord;
			}
		}
		return null;
	}

}