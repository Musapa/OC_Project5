package com.openclassrooms.project5.repository;

import com.openclassrooms.project5.domain.MedicalRecord;

public interface IMedicalRecordRepository {

	void add(MedicalRecord medicalRecord);

	MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);

	MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);

	boolean deleteMedicalRecord(MedicalRecord medicalRecord);

}