package com.openclassrooms.project5;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


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
	
	
	// ---------- ENDPOINTS tests -----------
	
	@Test
	public void addMedicalRecord() throws Exception {
		MedicalRecord medicalRecord = new MedicalRecord("Some firstName", "Some lastName", null , null, null);
		String jsonContent = objectMapper.writeValueAsString(medicalRecord);

		MvcResult result = mockMvc.perform(post("/medicalRecord").content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String json = result.getResponse().getContentAsString();
		MedicalRecord medicalRecordResult = objectMapper.readValue(json, MedicalRecord.class);
		
		assertEquals("First name correctly returned", true, medicalRecord.getFirstName().equals(medicalRecordResult.getFirstName()));
		assertEquals("Last name correctly returned", true, medicalRecord.getLastName().equals(medicalRecordResult.getLastName()));
		assertEquals("Date correctly returned", true, medicalRecord.getBirthdate().equals(medicalRecordResult.getBirthdate()));
		
	}

}
