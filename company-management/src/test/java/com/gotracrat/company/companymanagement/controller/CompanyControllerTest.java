package com.gotracrat.company.companymanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gotracrat.managecompany.service.CompanyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CompanyControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private CompanyService companyService;

	ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/*@Test
	public void getDashBoardData() throws Exception {
		when(companyService.getDashboardData(453,"true","1")).thenReturn(MockUtils.getDashBoardData());
		String uri = "/api/v1/dashboard/getRecentData/453/true/1";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		Assert.assertNotNull(response);
		Assert.assertEquals(200, response.getStatus());
	}*/

}
