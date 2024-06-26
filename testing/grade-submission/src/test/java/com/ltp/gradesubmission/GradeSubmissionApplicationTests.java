package com.ltp.gradesubmission;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest //for integration test, scan for beans in your application
@AutoConfigureMockMvc //create a mock mvc and autowired all the beans in Spring container
class GradeSubmissionApplicationTests {

	// @Autowired
	// private GradeController controller;

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void contextLoads() {
		// assertNotNull(controller);
		assertNotNull(mockMvc);
	}

	@Test
	public void testShowGradeForm() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/?id=123");
		
		mockMvc.perform(request)
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("form"))
			.andExpect(model().attributeExists("grade"));
	}

	@Test
	public void testSuccessfulSubmission() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit")
			.param("name", "Harry")
			.param("subject", "Potions")
			.param("score","C-");

		mockMvc.perform(request)
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/grades"));
	}

	@Test
	public void testUnsuccessfulSubmission() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit")
			.param("name", "     ")
			.param("subject", "    ")
			.param("score","R+");
		
		mockMvc.perform(request)
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("form"));
	}

	@Test
	public void testGetGrades() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/grades");
		mockMvc.perform(request)
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("grades"))
			.andExpect(model().attributeExists("grades"));

	}
}
