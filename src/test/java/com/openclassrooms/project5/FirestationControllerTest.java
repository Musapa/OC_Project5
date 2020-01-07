package com.openclassrooms.project5;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.project5.domain.Firestation;
import com.openclassrooms.project5.dto.FirestationDTO;
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

	// ---------- URL tests ----------	
	
	// VALID http://localhost:8080/fire?address=<address>
	@Test
	public void peopleValidFirestationAddress() throws Exception {
		MvcResult result = mockMvc.perform(get("/fire").param("address", "1509 Culver St")).andExpect(status().isOk()).andReturn();
		String json = result.getResponse().getContentAsString();
		Firestation firestations = objectMapper.readValue(json, Firestation.class);

		assertEquals("5 people expected", 5, firestations.getPersons().size());
	}

	// INVALID http://localhost:8080/fire?address=<address>
	@Test
	public void peopleInvalidFirestationAddress() throws Exception {
		mockMvc.perform(get("/fire").param("address", "bad address")).andExpect(status().isNotFound());
	}

	// VALID http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	@Test
	public void floodStationValidStationNumbers() throws Exception {
		MvcResult result = mockMvc.perform(get("/flood/stations").param("stations", "1")).andExpect(status().isOk()).andReturn();
		String json = result.getResponse().getContentAsString();
		List<Firestation> firestations = objectMapper.readValue(json, new TypeReference<List<Firestation>>() {});

		assertEquals("There should be 3 stations for station 1", 3, firestations.size());
	}

	// INVALID http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	@Test
	public void floodStationInvalidStationNumbers() throws Exception {
		MvcResult result = mockMvc.perform(get("/flood/stations").param("stations", "invalid stations")).andExpect(status().isNotFound()).andReturn();
		String json = result.getResponse().getContentAsString();
		List<Firestation> firestations = objectMapper.readValue(json, new TypeReference<List<Firestation>>() {});

		assertEquals("There should be 0 stations for invalid station number. ", 0, firestations.size());
	}

	// VALID http://localhost:8080/phoneAlert?firestation=<firestation_number>
	@Test
	public void phoneNumbersValidStationNumber() throws Exception {
		MvcResult result = mockMvc.perform(get("/phoneAlert").param("firestation", "4")).andExpect(status().isOk()).andReturn();
		String json = result.getResponse().getContentAsString();
		List<String> phoneNumbers = objectMapper.readValue(json, new TypeReference<List<String>>() {});

		assertEquals("4 phone numbers are expected", 4, phoneNumbers.size());
	}

	// INVALID http://localhost:8080/phoneAlert?firestation=<firestation_number>
	@Test
	public void phoneNumbersInvalidStationNumber() throws Exception {
		MvcResult result = mockMvc.perform(get("/phoneAlert").param("firestation", "invalid station")).andExpect(status().isNotFound()).andReturn();
		String json = result.getResponse().getContentAsString();
		List<String> phoneNumbers = objectMapper.readValue(json, new TypeReference<List<String>>() {});

		assertEquals("0 phone numbers are expected", 0, phoneNumbers.size());
	}

	// VALID http://localhost:8080/firestation?stationNumber=<station_number>
	@Test
	public void numberOfChildrenAndAdultsValidStationNumber() throws Exception {
		MvcResult result = mockMvc.perform(get("/firestation").param("stationNumber", "1")).andExpect(status().isOk()).andReturn();
		String json = result.getResponse().getContentAsString();
		Station station = objectMapper.readValue(json, Station.class);

		assertEquals("In station number 1 we expected 1 child", 1, station.getNumberOfChildren());
		assertEquals("In station number 1 we expected 4 adults", 5, station.getNumberOfAdults());
	}

	// INVALID http://localhost:8080/firestation?stationNumber=<station_number>
	@Test
	public void numberOfChildrenAndAdultsInvalidStationNumber() throws Exception {
		MvcResult result = mockMvc.perform(get("/firestation").param("stationNumber", "invalid number")).andExpect(status().isNotFound()).andReturn();
		String json = result.getResponse().getContentAsString();
		Station station = objectMapper.readValue(json, Station.class);

		assertEquals("Invalid station number expected 0", 0, station.getNumberOfChildren());
		assertEquals("Invalid station number expected 0", 0, station.getNumberOfAdults());
	}

	// ---------- END OF URL tests ----------
	
	// ---------- ENDPOINTS tests -----------
	
	@Test
	public void addFirestation() throws Exception {
		FirestationDTO firestation = new FirestationDTO("Some Address", "100");
		String jsonContent = objectMapper.writeValueAsString(firestation);

		MvcResult result = mockMvc.perform(post("/firestation").content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String json = result.getResponse().getContentAsString();
		FirestationDTO firestationResult = objectMapper.readValue(json, FirestationDTO.class);
		
		assertEquals("Address correctly returned", true, firestation.getAddress().equals(firestationResult.getAddress()));
		assertEquals("Station number correctly returned", true, firestation.getStation().equals(firestationResult.getStation()));
	}

}
