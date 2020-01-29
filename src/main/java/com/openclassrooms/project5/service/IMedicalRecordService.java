package com.openclassrooms.project5.service;

import com.openclassrooms.project5.domain.MedicalRecord;

public interface IMedicalRecordService {

	MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);

	MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);

	boolean deleteMedicalRecord(MedicalRecord medicalRecord);

}