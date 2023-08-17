package com.gotracrat.managelocation.controller;

import com.gotracrat.managelocation.service.DashboardService;
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
public class DashBoardControllerTest {

    private  String URI = "/api/v1/dashboard/";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private DashboardService dashboardService;

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getDashBoardData() throws Exception {
        when(dashboardService.getRecentDashboardData(453,"true","1")).thenReturn(MockUtils.getDashBoardData());
        URI = URI.concat("getRecentData/453/true/1");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }


    @Test
    public void getAllRepairsInDashboard() throws Exception {
        when(dashboardService.getAllRepairsInDashboard(ArgumentMatchers.any())).thenReturn(MockUtils.getAllRepairsInDashboard());
        String json = mapper.writeValueAsString(MockUtils.mockAllRepairs());
        String uri = "/api/v1/dashboard/getAllRepairs";
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

   /* @Test
    public void getDashboardResultsByTimeFrame() throws Exception {
        when(dashboardService.getDashboardResultsByTimeFrame(453,"LastYear","false",0,"true","99B27614-A682-4BA3-B9CE-7E52CFA659D7")).thenReturn(MockUtils.getDashboardResultsByTimeFrame());
        String uri = "/api/v1/dashboard/primaryFindingsChart/453/LASTYEAR/false/0/true/99B27614-A682-4BA3-B9CE-7E52CFA659D7";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }*/

    @Test
    public void getFailureCausesAndRepairCosts() throws Exception {
        when(dashboardService.getFailureCausesAndRepairCosts(ArgumentMatchers.any())).thenReturn(MockUtils.getDashboardResultsByTimeFrame());
        String json = mapper.writeValueAsString(MockUtils.mockFailureCauses());
        String uri = "/api/v1/dashboard/causesChart";
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

   /* @Test
    public void getDashboardResultsByRange() throws Exception {
        when(dashboardService.getDashboardResultsByRange(453,"20-06-2021","28-06-2021",1,"true","true","99B27614-A682-4BA3-B9CE-7E52CFA659D7")).thenReturn(MockUtils.getDashboardResultsByTimeFrame());
        String uri = "/api/v1/dashboard/primaryFindingsChartInRange/453/20-06-2021/28-06-2021/false/0/true/99B27614-A682-4BA3-B9CE-7E52CFA659D7";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }
*/
    @Test
    public void getFailureCausesinrange() throws Exception {
        when(dashboardService.getFailureCausesInRange(ArgumentMatchers.any())).thenReturn(MockUtils.getDashboardResultsByTimeFrame());
        String json = mapper.writeValueAsString(MockUtils.mockFailureCauses());
        String uri = "/api/v1/dashboard/causesChartInRange";
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void getRepairJobsByFailureCause() throws Exception {
        when(dashboardService.getRepairJobsByFailureCause(ArgumentMatchers.any())).thenReturn(MockUtils.getAllRepairsInDashboard());
        String json = mapper.writeValueAsString(MockUtils.mockFailureCauses());
        String uri = "/api/v1/dashboard/repairJobsByFailureCause";
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void getRepairJobsByFailureCauseInRange() throws Exception {
        when(dashboardService.getRepairJobsByFailureCauseInRange(ArgumentMatchers.any())).thenReturn(MockUtils.getAllRepairsInDashboard());
        String json = mapper.writeValueAsString(MockUtils.mockFailureCauses());
        String uri = "/api/v1/dashboard/repairJobsByFailureCauseInRange";
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }
}
