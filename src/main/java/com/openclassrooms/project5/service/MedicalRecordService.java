package com.openclassrooms.project5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.project5.domain.MedicalRecord;
import com.openclassrooms.project5.repository.IMedicalRecordRepository;

@Service
public class MedicalRecordService implements IMedicalRecordService {

	@Autowired
	private IMedicalRecordRepository medicalRecordRepository;

	@Override
	public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordRepository.createMedicalRecord(medicalRecord);
	}

	@Override
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordRepository.updateMedicalRecord(medicalRecord);
	}

	@Override
	public boolean deleteMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordRepository.deleteMedicalRecord(medicalRecord);
	}

}
