package com.openclassrooms.project5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.project5.domain.Firestation;
import com.openclassrooms.project5.dto.Station;

@RunWith(SpringRunner.class)
@SpringBootTest

public class FirestationControllerTest {

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
	public void firestationTest() throws Exception {
		try {
			mockMvc.perform(get("/fire").param("address", "bad address"));
			fail("Exception expected");
		} catch (Throwable e) {

		}
		MvcResult result = mockMvc.perform(get("/fire").param("address", "1509 Culver St")).andExpect(status().isOk())
				.andReturn();
		String json = result.getResponse().getContentAsString();
		Firestation firestations = objectMapper.readValue(json, Firestation.class);

		assertEquals("5 people expected", 5, firestations.getPersons().size());
	}

	@Test
	public void floodStationTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/flood/stations").param("stations", "1")).andExpect(status().isOk())
				.andReturn();
		String json = result.getResponse().getContentAsString();
		List<Firestation> firestations = objectMapper.readValue(json, new TypeReference<List<Firestation>>() {
		});

		assertEquals("There should be 3 stations for station 1", 3, firestations.size());
	}

	@Test
	public void phoneAlertTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/phoneAlert").param("firestation", "4")).andExpect(status().isOk())
				.andReturn();
		String json = result.getResponse().getContentAsString();
		List<String> phoneNumbers = objectMapper.readValue(json, new TypeReference<List<String>>() {
		});

		assertEquals("4 phone numbers are expected", 4, phoneNumbers.size());
	}

	@Test
	public void stationNumberTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/firestation").param("stationNumber", "1")).andExpect(status().isOk())
				.andReturn();
		String json = result.getResponse().getContentAsString();
		Station listOfStations = objectMapper.readValue(json, Station.class);

		assertEquals("In station number 1 we expected 1 children", 1, listOfStations.getNumberOfChildren());
		assertEquals("In station number 1 we expected 4 adults", 5, listOfStations.getNumberOfAdults());
	}

}
