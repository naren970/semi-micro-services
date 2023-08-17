package com.gotracrat.managelocation.controller;

import com.gotracrat.managelocation.service.FailureTypeService;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * @author Parasuram
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class FailureTypeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private FailureTypeService failureTypeService;

    ObjectMapper mapper = new ObjectMapper();

    private String URI = "/api/v1/failureType";


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetFailureTypeAndCause() throws Exception {
        when(failureTypeService.getFailureTypeAndCause(278, 100)).thenReturn(MockUtils.mockFailureTypesAndCauses());
        URI = URI.concat("/278/100");
        String resp = "{\"Electrical\":[\"Water in motor\",\"Dirt\",\"grease and foreign particles in motor\"]}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(resp, response.getContentAsString());
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void testCreateFailureType() throws Exception {
        when(failureTypeService.createFailureType(ArgumentMatchers.any())).thenReturn(MockUtils.mockCreateAndUpdateFailureType());
        String json = mapper.writeValueAsString(MockUtils.mockCreateAndUpdateFailureType());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(URI).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

  /*  @Test
    public void testUpdateFailureType() throws Exception {
        URI = URI.concat("/101");
        when(failureTypeService.updateFailureType(ArgumentMatchers.any())).thenReturn("Updated Successfully");
        String json = mapper.writeValueAsString(MockUtils.mockCreateAndUpdateFailureType());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(URI).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }*/

    @Test
    public void testDeleteFailureType() throws Exception {
        URI = URI.concat("/100/278/justin");
        doNothing().when(failureTypeService).deleteFailureType(ArgumentMatchers.any(), ArgumentMatchers.any());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(204, response.getStatus());

    }
}
