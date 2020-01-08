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
	
	public MedicalRecord getMedicalRecord(String firstName, String lastName) {
		for (MedicalRecord medicalRecord : medicalRecords) {
			if (medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName)) {
				return medicalRecord;
			}
		}
		return null;
	}
	
	
	// ---------- ENDPOINTS ----------
	
	public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
		MedicalRecord findMedicalRecord = getMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());
		if (findMedicalRecord != null) {
		medicalRecords.add(medicalRecord);
		return medicalRecord;
		}
		return null;
	}
	
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
		MedicalRecord findMedicalRecord = getMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());
		if (findMedicalRecord != null) {
			findMedicalRecord.setAllergies(medicalRecord.getAllergies());
			findMedicalRecord.setMedications(medicalRecord.getMedications());
			// findMedicalRecord.setBirthdate(medicalRecord.getBirthdate()); PROBLEM WITH return
			return medicalRecord;
		}
		return null;
	}
	
	public boolean deleteMedicalRecord(MedicalRecord medicalRecord) {
		for (MedicalRecord findMedicalRecord : medicalRecords) {
			if (findMedicalRecord.getFirstName().equals(medicalRecord.getFirstName()) && findMedicalRecord.getLastName().equals(medicalRecord.getLastName())) {
				medicalRecords.remove(findMedicalRecord);
				return true;
			}
		}
		return false;
	}
	
	// ---------- END OF ENDPOINTS ----------
}