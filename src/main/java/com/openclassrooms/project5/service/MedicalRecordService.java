package com.openclassrooms.project5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.project5.domain.MedicalRecord;
import com.openclassrooms.project5.repository.IMedicalRecordRepository;

@Service
public class MedicalRecordService implements IMedicalRecordService {

	@Autowired
	private IMedicalRecordRepository medicalRecordRepository;

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.service.IMedicalRecordService#createMedicalRecord(com.openclassrooms.project5.domain.MedicalRecord)
	 */
	@Override
	public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordRepository.createMedicalRecord(medicalRecord);
	}

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.service.IMedicalRecordService#updateMedicalRecord(com.openclassrooms.project5.domain.MedicalRecord)
	 */
	@Override
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordRepository.updateMedicalRecord(medicalRecord);
	}

	/* (non-Javadoc)
	 * @see com.openclassrooms.project5.service.IMedicalRecordService#deleteMedicalRecord(com.openclassrooms.project5.domain.MedicalRecord)
	 */
	@Override
	public boolean deleteMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordRepository.deleteMedicalRecord(medicalRecord);
	}

}
