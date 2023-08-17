package com.gotracrat.attributesandtypes.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AttributenameControllerTest {

	/*
	 * private MockMvc mockMvc;
	 * 
	 * @Autowired private WebApplicationContext wac;
	 * 
	 * @BeforeClass public void init() { mockMvc =
	 * MockMvcBuilders.webAppContextSetup(wac).build(); }
	 * 
	 * @Test(groups = { "api.attributename.*" }) public void
	 * get_all_attributes_by_type_id_when_arg_type_id_is_valid() throws Exception {
	 * 
	 * MvcResult mvcResult =
	 * mockMvc.perform(get("/api/attributename/getAllAttributes/35")).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty().
	 * isNotEqualTo("[]"); }
	 * 
	 * @Test(groups = { "api.attributename.*" }) public void
	 * get_all_attributes_by_type_id_when_arg_type_id_is_invalid() throws Exception
	 * {
	 * 
	 * MvcResult mvcResult =
	 * mockMvc.perform(get("/api/attributename/getAllAttributes/99999999")).
	 * andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ());
	 * assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("[]"); }
	 * 
	 * @Test(groups = { "api.attributename.*" }) public void
	 * get_attribute_by_attribute_name_id_when_arg_is_valid() throws Exception {
	 * 
	 * MvcResult mvcResult =
	 * mockMvc.perform(get("/api/attributename/41")).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty().
	 * isNotEqualTo("[]"); }
	 * 
	 * @Test(groups = { "api.attributename.*" }) public void
	 * get_attribute_by_attribute_name_id_when_arg_is_invalid() throws Exception {
	 * 
	 * MvcResult mvcResult =
	 * mockMvc.perform(get("/api/attributename/99999999")).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NOT_FOUND.value());
	 * assertThat(mvcResult.getResponse().getContentAsString()).isEmpty(); }
	 * 
	 * @Test(groups = { "api.attributename.*" }) public void
	 * should_create_attribute() throws Exception { ObjectMapper objectMapper = new
	 * ObjectMapper();
	 * 
	 * AttributenameResource attributenameResource =
	 * createAttributesearchtypeResource();
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(attributenameResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/attributename").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty(); }
	 * 
	 * @Test(groups = { "api.attributename.*" }, expectedExceptions = {
	 * Exception.class }) public void
	 * should_not_create_attribute_when_type_id_is_not_selected() throws Exception {
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * AttributenameResource attributenameResource =
	 * createAttributesearchtypeResource();
	 * 
	 * TypeResource resource = attributenameResource.getType();
	 * 
	 * resource.setTypeid(0);
	 * 
	 * attributenameResource.setType(resource);
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(attributenameResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/attributename").contentType("application/json").content(
	 * jsonRequest)).andReturn(); }
	 * 
	 * @Test(groups = { "api.attributename.*" }, expectedExceptions = {
	 * Exception.class }) public void
	 * should_not_create_attribute_when_attribute_id_is_not_selected() throws
	 * Exception { ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * AttributenameResource attributenameResource =
	 * createAttributesearchtypeResource();
	 * 
	 * AttributetypeResource attributetypeResource =
	 * attributenameResource.getAttributetype();
	 * 
	 * attributetypeResource.setAttributetypeid(0);
	 * 
	 * attributenameResource.setAttributetype(attributetypeResource);
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(attributenameResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/attributename").contentType("application/json").content(
	 * jsonRequest)).andReturn(); }
	 * 
	 * @Test(groups = { "api.attributename.*" }) public void
	 * should_update_attribute() throws Exception { ObjectMapper objectMapper = new
	 * ObjectMapper();
	 * 
	 * AttributenameResource attributenameResource =
	 * createAttributesearchtypeResource();
	 * attributenameResource.setName("Test Updated");
	 * 
	 * List<AttributelistitemResource> attributelistitemResources = new
	 * ArrayList<AttributelistitemResource>();
	 * 
	 * AttributelistitemResource attributelistitemResource = new
	 * AttributelistitemResource();
	 * 
	 * attributelistitemResource.setAttributenameid(1);
	 * attributelistitemResource.setListitem("Test");
	 * 
	 * attributelistitemResources.add(attributelistitemResource);
	 * 
	 * attributenameResource.setAttributelistitemResource(attributelistitemResources
	 * );
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(attributenameResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(put("/api/attributename/74").contentType("application/json").content
	 * (jsonRequest)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.attributename.*" }) public void
	 * should_update_attribute_when_invalid_attribute_id() throws Exception {
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * AttributenameResource attributenameResource =
	 * createAttributesearchtypeResource();
	 * attributenameResource.setName("Test Updated");
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(attributenameResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(put("/api/attributename/99999999").contentType("application/json").
	 * content(jsonRequest)) .andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NOT_FOUND.value()); }
	 * 
	 * @Test(groups = { "api.attributename.*" }) public void
	 * should_delete_attribute() throws Exception { ObjectMapper objectMapper = new
	 * ObjectMapper();
	 * 
	 * AttributenameResource attributenameResource =
	 * createAttributesearchtypeResource();
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(attributenameResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/attributename").contentType("application/json").content(
	 * jsonRequest)).andReturn(); if (mvcResult.getResponse().getStatus() ==
	 * HttpStatus.OK.value()) { JSONObject json = new
	 * JSONObject(mvcResult.getResponse().getContentAsString()); Integer
	 * attributenameid = json.getInt("attributenameid"); mvcResult =
	 * this.mockMvc.perform(delete("/api/attributename/" +
	 * attributenameid)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NO_CONTENT.value()); } }
	 * 
	 * @Test(groups = { "api.attributename.*" }) public void
	 * should_not_delete_attribute_when_attribute_id_invalid() throws Exception {
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(delete("/api/attributename/99999999")).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NOT_FOUND.value()); }
	 * 
	 * @Test(groups = { "api.attributename.*" }) public void get_all_attributes()
	 * throws Exception {
	 * 
	 * MvcResult mvcResult =
	 * mockMvc.perform(get("/api/attributename/getAllAttributetypes")).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty().
	 * isNotEqualTo("[]"); }
	 * 
	 * @Test(groups = { "api.attributename.*" }) public void
	 * get_all_attribute_search_type_by_attribute_type_id_when_arg_valid() throws
	 * Exception {
	 * 
	 * MvcResult mvcResult =
	 * mockMvc.perform(get("/api/attributename/getAllAttributeSearchType/1")).
	 * andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty().
	 * isNotEqualTo("[]"); }
	 * 
	 * @Test(groups = { "api.attributename.*" }) public void
	 * get_all_attribute_search_type_by_attribute_type_id_when_arg_invalid() throws
	 * Exception {
	 * 
	 * MvcResult mvcResult =
	 * mockMvc.perform(get("/api/attributename/getAllAttributeSearchType/99999999"))
	 * .andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ());
	 * assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("[]"); }
	 * 
	 * private AttributenameResource createAttributesearchtypeResource() {
	 * 
	 * AttributenameResource attributenameResource = new AttributenameResource();
	 * 
	 * TypeResource resource = new TypeResource(); resource.setTypeid(2);
	 * resource.setTypeList(new ArrayList<TypeResource>());
	 * 
	 * AttributetypeResource attributetypeResource = new AttributetypeResource();
	 * attributetypeResource.setAttributetypeid(1);
	 * 
	 * AttributesearchtypeResource attributesearchtypeResource = new
	 * AttributesearchtypeResource();
	 * attributesearchtypeResource.setAttributesearchtypeid(3);
	 * 
	 * attributenameResource.setType(resource);
	 * attributenameResource.setName("Test");
	 * attributenameResource.setAttributetype(attributetypeResource);
	 * attributenameResource.setTooltip("Test Tooltip");
	 * attributenameResource.setSearchtype(attributesearchtypeResource);
	 * attributenameResource.setIsrequired(true);
	 * attributenameResource.setDisplayorder(6);
	 * attributenameResource.setIsrequiredformatch(false);
	 * attributenameResource.setIsmanufacturer(false);
	 * 
	 * return attributenameResource; }
	 */
}
