package com.openclassrooms.project5.domain;

public class Person {

	private MedicalRecord medicalRecord;
	private Firestation firestation;
	
   	public MedicalRecord getMedicalRecord() {
   		return medicalRecord;
   	}
    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
    
   	public Firestation getFirestation() {
   		return firestation;
   	}
    public void setFirestation(Firestation firestation) {
        this.firestation = firestation;
    }
	
}
