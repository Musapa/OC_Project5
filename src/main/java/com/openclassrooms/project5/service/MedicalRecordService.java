package com.openclassrooms.project5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.project5.domain.MedicalRecord;
import com.openclassrooms.project5.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordRepository.createMedicalRecord(medicalRecord);
	}

	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordRepository.updateMedicalRecord(medicalRecord);
	}

	public boolean deleteMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordRepository.deleteMedicalRecord(medicalRecord);
	}

}
