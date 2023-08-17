package com.gotracrat.managelocation.controller;

import com.gotracrat.managelocation.service.WarrantyTypeService;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class WarrantyTypeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private WarrantyTypeService warrantyTypeService;

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllWarrantyTypeByCompanyId() throws Exception {
        when(warrantyTypeService.getAllWarrantyTypeByCompanyId(453)).thenReturn(MockUtils.getAllWarrantyTypeByCompanyId());
        String uri = "/api/v1/warrantyType/getAllWarrantyTypeByCompanyId/453";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }


    @Test
    public void getWarrantyType() throws Exception {
        when(warrantyTypeService.getWarrantyType(1)).thenReturn(MockUtils.getAllWarrantyTypeByCompanyId().get(0));
        String uri = "/api/v1/warrantyType/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }


    @Test
    public void createWarrantyType() throws Exception {
        when(warrantyTypeService.createWarrantyType(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(MockUtils.getAllWarrantyTypeByCompanyId().get(0));
        String json = mapper.writeValueAsString(MockUtils.mockWarrantyTypeForCreateAndUpdate());
        String uri = "/api/v1/warrantyType";
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void updateWarrantyType() throws Exception {
        doNothing().when(warrantyTypeService).updateWarrantyType(ArgumentMatchers.any(),ArgumentMatchers.any());
        String json = mapper.writeValueAsString(MockUtils.mockWarrantyTypeForCreateAndUpdate());
        String uri = "/api/v1/warrantyType/1";
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
           Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void delete() throws Exception {
       doNothing().when(warrantyTypeService).delete(ArgumentMatchers.any(),ArgumentMatchers.any());
        String uri = "/api/v1/warrantyType/1/453/ypatel/repair";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(204, response.getStatus());
    }

}
