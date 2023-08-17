package com.gotracrat.managelocation.controller;

import com.gotracrat.managelocation.service.ReportsService;
import com.gotracrat.managelocation.utils.MockUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
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

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ReportsControllerTest {

    private  String uri = "/api/v1/reports";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ReportsService reportsService;

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getSpareMotor() throws Exception {
        when(reportsService.getSpareMotor(1)).thenReturn(MockUtils.getSpareMotor());
        uri = uri.concat("/spare/1");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void getServiceReports() throws Exception {
        when(reportsService.getServiceReports(ArgumentMatchers.any())).thenReturn(MockUtils.getServiceReports());
        String json = mapper.writeValueAsString(MockUtils.mockServiceReportsRequestForMonth());
        uri = uri.concat("/serviceReport");
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void getInServiceVsSpareReports() throws Exception {
        when(reportsService.getInServiceVsSpareReports(ArgumentMatchers.any())).thenReturn(MockUtils.getInServiceVsSpareReports());
        String json = mapper.writeValueAsString(MockUtils.mockInServiceVsSpareRequset());
        uri = uri.concat("/inServiceVsSpare");
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }
}
