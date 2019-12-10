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
	public void getFirestationTest() throws Exception {
		try {
			mockMvc.perform(get("/fire").param("address", "bad address"));
			fail("Exception expected");
		} catch (Throwable e) {

		}
		 MvcResult result = mockMvc.perform(get("/fire").param("address", "1509 Culver St")).andExpect(status().isOk()).andReturn();
		 String json = result.getResponse().getContentAsString();
		 Firestation firestation = objectMapper.readValue(json, Firestation.class);
		 
		 assertEquals("5 people expected", 5, firestation.getPersons().size());
	}
	
	@Test
	public void getFirestationNumberTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/flood/stations").param("stations", "1")).andExpect(status().isOk()).andReturn();
		String json = result.getResponse().getContentAsString();
		List<Firestation> firestation = objectMapper.readValue(json, new TypeReference<List<Firestation>>() {});
		
		assertEquals("station 1", true, firestation.addAll(firestation));
	}

	@Test
	public void findPhoneByStationTest() throws Exception {
		 MvcResult result = mockMvc.perform(get("/phoneAlert").param("firestation", "4")).andExpect(status().isOk()).andReturn();
		 String json = result.getResponse().getContentAsString();
		 Firestation firestation = objectMapper.readValue(json, Firestation.class);
		 
		 assertEquals("2 adresses expected", 2, firestation.getAddress().length());
	}

}
