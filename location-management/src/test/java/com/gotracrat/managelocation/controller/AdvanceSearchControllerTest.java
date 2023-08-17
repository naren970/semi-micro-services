package com.gotracrat.managelocation.controller;

import com.gotracrat.managelocation.service.AdvanceSearchService;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AdvanceSearchControllerTest {

    private  String uri = "/api/v1/advanceSearch/";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private AdvanceSearchService advanceSearchService;

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAdvanceSearchRepairLogNoteByUser() throws Exception {
        when(advanceSearchService.getAdvanceSearchRepairLogNoteByUser(ArgumentMatchers.any())).thenReturn(MockUtils.getAdvanceSearchRepairLogNoteByUser());
        String json = mapper.writeValueAsString(MockUtils.getAdvanceSearchRepairLogNoteByUser());
        uri = uri.concat("/repairLogNote");
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void getAdvanceSearchRepairLogNoteByUserNotFound() throws Exception {
        when(advanceSearchService.getAdvanceSearchRepairLogNoteByUser(ArgumentMatchers.any())).thenReturn(MockUtils.getAdvanceSearchRepairLogNoteByUserNotFound());
        String json = mapper.writeValueAsString(MockUtils.getAdvanceSearchRepairLogNoteByUserNotFound());
        uri = uri.concat("/repairLogNote");
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(400, response.getStatus());
    }

    @Test
    public void advancedSearch() throws Exception {
        when(advanceSearchService.itemAdvancedSearch(ArgumentMatchers.any())).thenReturn(MockUtils.mockItemAdvancedSearch());
        String json = mapper.writeValueAsString(MockUtils.mockItemAdsRequestDTO());
     //   uri = uri.concat("/repairLogNote");
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

}
