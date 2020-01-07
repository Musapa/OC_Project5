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

	// ---------- URL tests ----------	
	
	// VALID http://localhost:8080/communityEmail?city=<city>
	@Test
	public void communityEmailValidCityName() throws Exception {
		MvcResult result = mockMvc.perform(get("/communityEmail").param("city", "Culver")).andExpect(status().isOk()).andReturn();
		String json = result.getResponse().getContentAsString();
		List<String> emails = objectMapper.readValue(json, new TypeReference<List<String>>() {});

		assertEquals("23 emails are expected", 23, emails.size());
	}
	
	// INVALID http://localhost:8080/communityEmail?city=<city>
	@Test
	public void communityEmailInvalidCityName() throws Exception {
		MvcResult result = mockMvc.perform(get("/communityEmail").param("city", "wrong city")).andExpect(status().isNotFound()).andReturn();
		String json = result.getResponse().getContentAsString();
		List<String> emails = objectMapper.readValue(json, new TypeReference<List<String>>() {});

		assertEquals("0 emails are expected", 0, emails.size());
	}
	
	// VALID http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	@Test
	public void personInfoValidTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/personInfo").param("firstName", "John").param("lastName", "Boyd")).andExpect(status().isOk()).andReturn();
		String json = result.getResponse().getContentAsString();
		List<Person> findPerson = objectMapper.readValue(json, new TypeReference<List<Person>>() {});

		assertEquals("There is one person with name John Boyd", 1, findPerson.size());
	}
	
	// INVALID http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	@Test
	public void personInfoInvalidTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/personInfo").param("firstName", "wrong first name").param("lastName", "wrong last name")).andExpect(status().isNotFound()).andReturn();
		String json = result.getResponse().getContentAsString();
		List<Person> findPerson = objectMapper.readValue(json, new TypeReference<List<Person>>() {});

		assertEquals("There is no person with wrong first & last name", 0, findPerson.size());
	}
	
	// VALID http://localhost:8080/childAlert?address=<address>
	@Test
	public void childAlertValidTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/childAlert").param("address", "892 Downing Ct")).andExpect(status().isOk()).andReturn();
		String json = result.getResponse().getContentAsString();
		ChildAlert childAlerts = objectMapper.readValue(json, ChildAlert.class);

		assertEquals("On address 892 Downing Ct we expected 2 children", 1, childAlerts.getChildren().size());
		assertEquals("On address 892 Downing Ct we expected 2 adults", 2, childAlerts.getAdults().size());
	}

	// INVALID http://localhost:8080/childAlert?address=<address>
	@Test
	public void childAlertInvalidTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/childAlert").param("address", "wrong address")).andExpect(status().isNotFound()).andReturn();
		String json = result.getResponse().getContentAsString();
		ChildAlert childAlerts = objectMapper.readValue(json, ChildAlert.class);

		assertEquals("Wrong address we expected 0 children", 0, childAlerts.getChildren().size());
		assertEquals("Wrong address we expected 0 adults", 0, childAlerts.getAdults().size());
	}
	
	// ---------- END OF URL tests ----------
}
