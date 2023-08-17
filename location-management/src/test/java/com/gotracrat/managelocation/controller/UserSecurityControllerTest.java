package com.gotracrat.managelocation.controller;


import com.gotracrat.managelocation.service.UserSecurityService;
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
public class UserSecurityControllerTest {

    private  String uri = "/api/v1/userSecurity";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserSecurityService userSecurityService;

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getUserSecurity() throws Exception {
        when(userSecurityService.getUserSecurity("1", 1, 1)).thenReturn(MockUtils.getUserSecurity());
        uri = uri.concat("/getUserSecurity/1/1/1");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }


    @Test
    public void createOrUpdateUserSecurity() throws Exception {
        when(userSecurityService.createOrUpdateUserSecurity(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(MockUtils.getUserSecurity());
        String json = mapper.writeValueAsString(MockUtils.getUserSecurity());
        //  String uri = "/api/v1/userSecurity";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void delete() throws Exception {
        doNothing().when(userSecurityService).delete("1", 1, 2, MockUtils.mockUserLog());
        uri = uri.concat("/1/453/1/ypatel");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(204, response.getStatus());
    }

    @Test
    public void getLevelsByUserName() throws Exception {
        when(userSecurityService.getLevels(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(MockUtils.getLevelsByUserName());
        uri = uri.concat("/ypatel/1");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void getRolesForUser() throws Exception {
        when(userSecurityService.getAllRolesForUser(ArgumentMatchers.any())).thenReturn(MockUtils.getAllRolesForUser());
        uri = uri.concat("/getAllRolesForUser/1");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void getRolesForALoggedInUser() throws Exception {
        when(userSecurityService.getRolesForALoggedInUser(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(MockUtils.getRolesForALoggedInUser());
        uri = uri.concat("/getAllRolesForUser/1");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

}
