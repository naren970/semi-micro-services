package com.gotracrat.managelocation.controller;

import com.gotracrat.managelocation.service.LocationService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class LocationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private LocationService locationService;

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getLocationsByCompanyId() throws Exception {
        when(locationService.getAllLocations(453)).thenReturn(MockUtils.getLocationsByCompanyId());
        String uri = "/api/v1/location/getAllLocations/453";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void getAllLocationsWithHierarchyByCompanyId() throws Exception {
        when(locationService.getAllLocationsWithHierarchy(453)).thenReturn(MockUtils.getAllLocationsWithHierarchyByCompanyId());
        String uri = "/api/v1/location/getAllLocationsWithHierarchy/453";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void getLocation() throws Exception {
        when(locationService.get(1)).thenReturn(MockUtils.getAllLocationsWithHierarchyByCompanyId().get(0));
        String uri = "/api/v1/location/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }


    @Test
    public void createLocations() throws Exception {
        when(locationService.createLocation(ArgumentMatchers.any())).thenReturn(MockUtils.getAllLocationsWithHierarchyByCompanyId());
        String json = mapper.writeValueAsString(MockUtils.mockLocationsForCreateAndUpdate());
        String uri = "/api/v1/location";
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void mergeLocations() throws Exception {
        when(locationService.mergeLocation(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(MockUtils.mergeLocations());
        String json = mapper.writeValueAsString(MockUtils.mergeLocations());
        String uri = "/api/v1/location/mergeLocations/453";
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }
    @Test
    public void updateLocation() throws Exception {
        when(locationService.updateLocation(MockUtils.mockLocationsForCreateAndUpdate().get(0))).thenReturn(true);
        String json = mapper.writeValueAsString(MockUtils.mockLocationsForCreateAndUpdate());
        String uri = "/api/v1/location/1";
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
           Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void deleteLocation() throws Exception {
        when(locationService.delete(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn("Location Deleted successfully");
        String uri = "/api/v1/location/1/453/ypatel";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(204, response.getStatus());
    }

    @Test
    public void getAllLocationsByUser() throws Exception {
        when(locationService.getAllLocationsByUser(453,"12")).thenReturn(MockUtils.getAllLocationsWithHierarchyByCompanyId());
        String uri = "/api/v1/location/getAllLocationsByUser/453/12";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void getAllLocationsByUserForNotFound() throws Exception {
        when(locationService.getAllLocationsByUser(2,"2")).thenReturn(null);
        String uri = "/api/v1/location/getAllLocationsByUser/2/2";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(404, response.getStatus());
    }

    @Test
    public void getCloneAddressFromParentLocation() throws Exception {
        when(locationService.getCloneAddressFromParentLocation(1,453)).thenReturn(MockUtils.getAllLocationsWithHierarchyByCompanyId().get(0));
        String uri = "/api/v1/location/getCloneAddressFromParentLocation/1/453";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void getCloneAddressFromParentLocationForNotFound() throws Exception {
        when(locationService.getCloneAddressFromParentLocation(2,2)).thenReturn(null);
        String uri = "/api/v1/location/getCloneAddressFromParentLocation/2/2";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(404, response.getStatus());
    }
}
