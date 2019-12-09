package com.openclassrooms.project5;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.openclassrooms.project5.controller.FirestationController;
import com.openclassrooms.project5.controller.PersonController;
import com.openclassrooms.project5.domain.Firestation;

@RunWith(SpringRunner.class)
@SpringBootTest

public class FirestationControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webContext;

	@Autowired
	private FirestationController firestationController;
	@Autowired
	private PersonController personController;

	private Firestation firestation;

	@Before
	public void setupMockmvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Before
	public void create() {
		firestation = new Firestation();
		firestation.setAddress("Culver Street");
		firestation.setStation("Station 1");
	}
	
	@Test
	public void getFirestationTest() throws Exception {
		mockMvc.perform(post("/fire").param("address", firestation.getAddress().toString()))
				.andExpect(view().name("Culver Street")).andExpect(model().errorCount(0))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("Cannot find firestation."));
	}

}
