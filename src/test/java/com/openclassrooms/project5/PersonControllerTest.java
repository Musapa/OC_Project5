package com.openclassrooms.project5;

import static org.junit.Assert.assertEquals;
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
import com.openclassrooms.project5.domain.Person;
import com.openclassrooms.project5.dto.ChildAlert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonControllerTest {

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
	public void communityEmailTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/communityEmail").param("city", "Culver")).andExpect(status().isOk())
				.andReturn();
		String json = result.getResponse().getContentAsString();
		List<String> emails = objectMapper.readValue(json, new TypeReference<List<String>>() {});

		assertEquals("23 emails are expected", 23, emails.size());
	}
	
	@Test
	public void personInfoTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/personInfo").param("firstName", "John").param("lastName", "Boyd")).andExpect(status().isOk())
				.andReturn();
		String json = result.getResponse().getContentAsString();
		List<Person> findPerson = objectMapper.readValue(json, new TypeReference<List<Person>>() {
		});

		assertEquals("There should be 3 stations for station 1", 1, findPerson.size());
	}
	
	@Test
	public void childAlertTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/childAlert").param("address", "892 Downing Ct")).andExpect(status().isOk())
				.andReturn();
		String json = result.getResponse().getContentAsString();
		ChildAlert childAlerts = objectMapper.readValue(json, ChildAlert.class);

		assertEquals("On address 892 Downing Ct we expected 2 children", 1, childAlerts.getChildren().size());
		assertEquals("On address 892 Downing Ct we expected 2 adults", 2, childAlerts.getAdults().size());
	}

}
