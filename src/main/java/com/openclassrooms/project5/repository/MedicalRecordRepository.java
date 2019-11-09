package com.openclassrooms.project5.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.project5.domain.MedicalRecord;

@Repository
public class MedicalRecordRepository {
	
	private List<MedicalRecord> medicalrecords = new ArrayList<>();
	
	public List<MedicalRecord> getMedicalRecord() {
		return medicalrecords;
	}
	public void add(MedicalRecord medicalRecord) {
		medicalrecords.add(medicalRecord);
	} 
}