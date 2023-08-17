package com.gotracrat.managelocation.controller;

import com.gotracrat.managelocation.resource.ItemResource;
import com.gotracrat.managelocation.service.ItemServiceImpl;
import com.gotracrat.managelocation.utils.MockUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ItemControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ItemServiceImpl itemService;

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testGetItem() throws Exception {
        when(itemService.getItem(any())).thenReturn(MockUtils.mockItemResource());
        String uri = "/api/v1/item/389957";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());

    }


    @Test
    public void testGetItemNotFound() throws Exception {
        when(itemService.getItem(any())).thenReturn(null);
        String uri = "/api/v1/item/389957";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(404, response.getStatus());

    }

    @Test
    public void testVwGetItem() throws Exception {
        when(itemService.getItemById(any())).thenReturn(MockUtils.mockVwGetItem(true).get());
        String uri = "/api/v1/item/getItem/389957";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());

    }


    /**
     * Test to create Item service call
     */
    @Test
    public void testCreateItem() throws Exception {
        when(itemService.createItem(ArgumentMatchers.any())).thenReturn(MockUtils.mockItemResource());
        String uri = "/api/v1/item/";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(mapper.writeValueAsString(MockUtils.mockItemResourceForCreateAndUpdate(true))).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void testUpdateItem() throws Exception {
        doNothing().when(itemService).updateItem(ArgumentMatchers.any());
        String uri = "/api/v1/item/389957";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(mapper.writeValueAsString(MockUtils.mockItemResourceForCreateAndUpdate(false))).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void testDeleteItem() throws Exception {
        when(itemService.deleteItem(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn("Item Deleted Successfully");
        String uri = "/api/v1/item/389957/279/ypatel/MM00008/Motor";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(204, response.getStatus());
    }

    @Test
    public void testSearchItemsForTablet() throws Exception {
        when(itemService.searchItems(any(), any(), any(), any())).thenReturn(MockUtils.mockSearchItemsResponseForTablet());
        String uri = "/api/v1/item/search/279/true/2345";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(mapper.writeValueAsString(MockUtils.mockSearchRequestDTO())).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());

    }

    @Test
    public void testSearchItems() throws Exception {
        when(itemService.searchItems(any())).thenReturn(MockUtils.mockSearchItemsResponse());
        String uri = "/api/v1/item/search";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(mapper.writeValueAsString(MockUtils.mockSearchRequestDTO())).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());

    }

    @Test
    public void testMasterSearch() throws Exception {
        when(itemService.masterSearch(any())).thenReturn(MockUtils.mockMasterSearchServiceResponse());
        String uri = "/api/v1/item/masterSearch";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(mapper.writeValueAsString(MockUtils.mockMasterSearchRequest())).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());

    }

    @Test
    public void testMasterSearchWithAttributes() throws Exception {
        when(itemService.masterSearchWithAttributes(any())).thenReturn(MockUtils.mockMasterSearchWithAttributes());
        String uri = "/api/v1/item/masterSearchAttributes";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(mapper.writeValueAsString(MockUtils.mockMasterSearchWithAttributesRequest())).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());

    }


    @Test
    public void testSearchTagsByCompanyId() throws Exception {
        when(itemService.searchTags(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(MockUtils.mockTagSuggestions());
        String uri = "/api/v1/item/suggestions/mm01/279";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void testCheckTagAvailability() throws Exception {
        when(itemService.searchTagsByType(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(MockUtils.mockTagAvailability());
        String uri = "/api/v1/item/checkTagAvailability/mm01/2457";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void testUpdateDefaultImageForItem() throws Exception {
        doNothing().when(itemService).updateDefaultImageForItem(any(), any());
        String uri = "/api/v1/item/389957/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void testGetAllItemAttributesForReplacement() throws Exception {
        when(itemService.getAllItemAttributesForReplacement(ArgumentMatchers.any())).thenReturn(MockUtils.mockFindReplacementDTO());
        String uri = "/api/v1/item//findReplacement/389957";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void testGetJournalByItemId() throws Exception {
        when(itemService.getChangeLog(ArgumentMatchers.any())).thenReturn(MockUtils.mockChangeLog());
        String uri = "/api/v1/item/journal/389957";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void testGetWarehouseTag() throws Exception {
        when(itemService.getWarehouseTag(ArgumentMatchers.any())).thenReturn(MockUtils.mockWareHouseTag().get(0));
        String uri = "/api/v1/item/warehouseTag/389957";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }
}
