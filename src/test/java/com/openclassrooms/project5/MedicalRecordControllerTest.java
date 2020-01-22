package com.openclassrooms.project5;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.project5.domain.MedicalRecord;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicalRecordControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webContext;

	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void setupMockmvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	public void addMedicalRecord() throws Exception {
		MedicalRecord medicalRecord = new MedicalRecord("firstName", "lastName", new Date(),
				Arrays.asList("medication0", "medication1"), Arrays.asList("allergies0", "allergies1"));
		String jsonContent = objectMapper.writeValueAsString(medicalRecord);

		MvcResult result = mockMvc.perform(post("/medicalRecord").content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();

		String json = result.getResponse().getContentAsString();
		MedicalRecord medicalRecordResult = objectMapper.readValue(json, MedicalRecord.class);

		assertEquals("First name correctly returned", true,
				medicalRecord.getFirstName().equals(medicalRecordResult.getFirstName()));
		assertEquals("Last name correctly returned", true,
				medicalRecord.getLastName().equals(medicalRecordResult.getLastName()));
		assertEquals("Date correctly returned", false,
				medicalRecord.getBirthdate().equals(medicalRecordResult.getBirthdate()));
		assertEquals("Medications correctly returned", true,
				medicalRecord.getMedications().equals(medicalRecordResult.getMedications()));
		assertEquals("Allergies correctly returned", true,
				medicalRecord.getAllergies().equals(medicalRecordResult.getAllergies()));
	}

	@Test
	public void updateMedicalRecord() throws Exception {
		MedicalRecord medicalRecord = new MedicalRecord("firstName1", "lastName1", new Date(),
				Arrays.asList("medication2", "medication3"), Arrays.asList("allergies2", "allergies3"));
		MedicalRecord updateMedicalRecord = new MedicalRecord("firstName1", "lastName1", new Date(),
				Arrays.asList("medication4", "medication5"), Arrays.asList("allergies4", "allergies5"));
		String jsonContent = objectMapper.writeValueAsString(medicalRecord);
		String jsonContent2 = objectMapper.writeValueAsString(updateMedicalRecord);

		mockMvc.perform(post("/medicalRecord").content(jsonContent).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		MvcResult result = mockMvc.perform(put("/medicalRecord").content(jsonContent2)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();

		String json = result.getResponse().getContentAsString();
		MedicalRecord medicalRecordResult = objectMapper.readValue(json, MedicalRecord.class);

		assertEquals("First name correctly returned", true,
				medicalRecordResult.getFirstName().equals(updateMedicalRecord.getFirstName()));
		assertEquals("Last name correctly returned", true,
				medicalRecordResult.getLastName().equals(updateMedicalRecord.getLastName()));
		assertEquals("Date correctly returned", false,
				medicalRecordResult.getBirthdate().equals(updateMedicalRecord.getBirthdate()));
		assertEquals("Medications correctly returned", true,
				medicalRecordResult.getMedications().equals(updateMedicalRecord.getMedications()));
		assertEquals("Allergies correctly returned", true,
				medicalRecordResult.getAllergies().equals(updateMedicalRecord.getAllergies()));
	}

	@Test
	public void deleteMedicalRecord() throws Exception {
		MedicalRecord medicalRecord = new MedicalRecord("firstName2", "lastName2", new Date(),
				Arrays.asList("medication4", "medication5"), Arrays.asList("allergies4", "allergies5"));
		String jsonContent = objectMapper.writeValueAsString(medicalRecord);

		mockMvc.perform(post("/medicalRecord").content(jsonContent).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		// first delete attempt will succeed
		mockMvc.perform(delete("/medicalRecord").content(jsonContent).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		// second delete attempt will fail
		mockMvc.perform(delete("/medicalRecord").content(jsonContent).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
}
