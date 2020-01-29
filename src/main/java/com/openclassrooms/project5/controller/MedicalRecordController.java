package com.openclassrooms.project5.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.project5.domain.MedicalRecord;
import com.openclassrooms.project5.service.IMedicalRecordService;

@RestController
public class MedicalRecordController {

	private static final Logger log = LoggerFactory.getLogger(MedicalRecordController.class);

	@Autowired
	private IMedicalRecordService medicalRecordService;

	@RequestMapping(value = "/medicalRecord", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		MedicalRecord medicalRecordCreated = medicalRecordService.createMedicalRecord(medicalRecord);
		if (medicalRecordCreated == null) {
			log.info("MedicalRecord not created");
			return ResponseEntity.unprocessableEntity().build();
		}
		log.info("MedicalRecord created");
		return ResponseEntity.ok().body(medicalRecordCreated);
	}

	@RequestMapping(value = "/medicalRecord", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		MedicalRecord medicalRecordUpdated = medicalRecordService.updateMedicalRecord(medicalRecord);
		if (medicalRecordUpdated == null) {
			log.info("MedicalRecord not updated");
			return ResponseEntity.unprocessableEntity().build();
		}
		log.info("MedicalRecord updated");
		return ResponseEntity.ok().body(medicalRecordUpdated);
	}

	@RequestMapping(value = "/medicalRecord", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<MedicalRecord> deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		boolean medicalRecordDeleted = medicalRecordService.deleteMedicalRecord(medicalRecord);
		if (medicalRecordDeleted) {
			log.info("MedicalRecord deleted");
			return ResponseEntity.ok().build();
		}
		log.info("MedicalRecord not deleted");
		return ResponseEntity.notFound().build();
	}

}
