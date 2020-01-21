package com.openclassrooms.project5;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
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
import com.openclassrooms.project5.domain.MedicalRecord;
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

	// VALID http://localhost:8080/communityEmail?city=<city>
	@Test
	public void communityEmailValidCityName() throws Exception {
		MvcResult result = mockMvc.perform(get("/communityEmail").param("city", "Culver")).andExpect(status().isOk())
				.andReturn();
		String json = result.getResponse().getContentAsString();
		List<String> emails = objectMapper.readValue(json, new TypeReference<List<String>>() {
		});

		assertEquals("23 emails are expected", 23, emails.size());
	}

	// INVALID http://localhost:8080/communityEmail?city=<city>
	@Test
	public void communityEmailInvalidCityName() throws Exception {
		MvcResult result = mockMvc.perform(get("/communityEmail").param("city", "wrong city"))
				.andExpect(status().isNotFound()).andReturn();
		String json = result.getResponse().getContentAsString();
		List<String> emails = objectMapper.readValue(json, new TypeReference<List<String>>() {
		});

		assertEquals("0 emails are expected", 0, emails.size());
	}

	// VALID
	// http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	@Test
	public void personInfoValidTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/personInfo").param("firstName", "John").param("lastName", "Boyd"))
				.andExpect(status().isOk()).andReturn();
		String json = result.getResponse().getContentAsString();
		List<Person> findPerson = objectMapper.readValue(json, new TypeReference<List<Person>>() {
		});

		assertEquals("There is one person with name John Boyd", 1, findPerson.size());
	}

	// INVALID
	// http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	@Test
	public void personInfoInvalidTest() throws Exception {
		MvcResult result = mockMvc
				.perform(get("/personInfo").param("firstName", "wrong first name").param("lastName", "wrong last name"))
				.andExpect(status().isNotFound()).andReturn();
		String json = result.getResponse().getContentAsString();
		List<Person> findPerson = objectMapper.readValue(json, new TypeReference<List<Person>>() {
		});

		assertEquals("There is no person with wrong first & last name", 0, findPerson.size());
	}

	// VALID http://localhost:8080/childAlert?address=<address>
	@Test
	public void childAlertValidTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/childAlert").param("address", "892 Downing Ct"))
				.andExpect(status().isOk()).andReturn();
		String json = result.getResponse().getContentAsString();
		ChildAlert childAlerts = objectMapper.readValue(json, ChildAlert.class);

		assertEquals("On address 892 Downing Ct we expected 2 children", 1, childAlerts.getChildren().size());
		assertEquals("On address 892 Downing Ct we expected 2 adults", 2, childAlerts.getAdults().size());
	}

	// INVALID http://localhost:8080/childAlert?address=<address>
	@Test
	public void childAlertInvalidTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/childAlert").param("address", "wrong address"))
				.andExpect(status().isNotFound()).andReturn();
		String json = result.getResponse().getContentAsString();
		ChildAlert childAlerts = objectMapper.readValue(json, ChildAlert.class);

		assertEquals("Wrong address we expected 0 children", 0, childAlerts.getChildren().size());
		assertEquals("Wrong address we expected 0 adults", 0, childAlerts.getAdults().size());
	}

	@Test
	public void addPerson() throws Exception {
		Person person = new Person("firstName1", "lastName1", "address1", "city1", "zip1", "phone1", "email1", new MedicalRecord("firstName1", "lastName1", new Date(),
				Arrays.asList("medication1", "medication2"), Arrays.asList("allergies1", "allergies2")));
		String jsonContent = objectMapper.writeValueAsString(person);

		MvcResult result = mockMvc.perform(post("/person").content(jsonContent).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andReturn();

		String json = result.getResponse().getContentAsString();
		Person personResult = objectMapper.readValue(json, Person.class);

		assertEquals("Address correctly returned", true, person.getAddress().equals(personResult.getAddress()));
	}
	
	@Test
	public void updatePerson() throws Exception {
		Person person = new Person("firstName1", "lastName1", "address1", "city1", "zip1", "phone1", "email1", new MedicalRecord());
		Person updatePerson = new Person("firstName2", "lastName2", "address2", "city2", "zip2", "phone2", "email2", new MedicalRecord());
		String jsonContent = objectMapper.writeValueAsString(person);
		String jsonContent2 = objectMapper.writeValueAsString(updatePerson);

		mockMvc.perform(post("/person").content(jsonContent).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		MvcResult result = mockMvc.perform(put("/person").content(jsonContent2)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();

		String json = result.getResponse().getContentAsString();
		Person personResult = objectMapper.readValue(json, Person.class);

		assertEquals("Address correctly updated", true,
				personResult.getAddress().equals(updatePerson.getAddress()));
	}
	
	@Test
	public void deletePerson() throws Exception {
		Person person = new Person("firstName1", "lastName1", "address1", "city1", "zip1", "phone1", "email1", new MedicalRecord());
		String jsonContent = objectMapper.writeValueAsString(person);

		mockMvc.perform(post("/person").content(jsonContent).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		// first delete attempt will succeed
		mockMvc.perform(delete("/person").content(jsonContent).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		// second delete attempt will fail
		mockMvc.perform(delete("/person").content(jsonContent).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
}
