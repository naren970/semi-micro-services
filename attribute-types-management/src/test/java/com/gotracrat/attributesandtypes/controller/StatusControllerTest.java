package com.gotracrat.attributesandtypes.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StatusControllerTest {

	/*
	 * private MockMvc mockMvc;
	 * 
	 * @Autowired private WebApplicationContext wac;
	 * 
	 * @BeforeClass public void init() { mockMvc =
	 * MockMvcBuilders.webAppContextSetup(wac).build(); }
	 * 
	 * @Test(groups = { "api.status.*" }) public void
	 * get_all_status_when_arg_model_type_is_companytype() throws Exception {
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/status/getAllStatusByCompanyId/companytype/3")
	 * ) .andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.status.*" }) public void
	 * get_all_status_when_arg_model_type_is_locationtype() throws Exception {
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/status/getAllStatusByCompanyId/locationtype/3"
	 * )) .andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.status.*" }) public void
	 * get_all_status_when_arg_model_type_is_itemtype() throws Exception { MvcResult
	 * mvcResult =
	 * this.mockMvc.perform(get("/api/status/getAllStatusByCompanyId/itemtype/3")).
	 * andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.status.*" }) public void
	 * get_all_status_when_arg_model_type_is_none() throws Exception { MvcResult
	 * mvcResult =
	 * this.mockMvc.perform(get("/api/status/getAllStatusByCompanyId/none/3")).
	 * andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NOT_FOUND.value()); }
	 * 
	 * @Test(groups = { "api.status.*" }) public void
	 * get_all_status_when_arg_company_id_is_invalid() throws Exception { MvcResult
	 * mvcResult = this.mockMvc.perform(get(
	 * "/api/status/getAllStatusByCompanyId/companytype/99999999")) .andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ());
	 * assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("[]"); }
	 * 
	 * @Test(groups = { "api.status.*" }) public void
	 * get_status_by_id_when_arg_valid() throws Exception { MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/status/16")).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.status.*" }) public void
	 * get_status_by_id_when_arg_invalid() throws Exception { MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/status/99999999")).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NOT_FOUND.value()); }
	 * 
	 * @Test(groups = { "api.status.*" }) public void
	 * should_create_when_model_type_arg_companytype() throws Exception {
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * StatusResource statusResource = createStatusResource("companytype"); String
	 * jsonRequest = objectMapper.writeValueAsString(statusResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/status").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty(); }
	 * 
	 * @Test(groups = { "api.status.*" }) public void
	 * should_create_when_model_type_arg_locationtype() throws Exception {
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * StatusResource statusResource = createStatusResource("locationtype"); String
	 * jsonRequest = objectMapper.writeValueAsString(statusResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/status").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty(); }
	 * 
	 * @Test(groups = { "api.status.*" }) public void
	 * should_create_when_model_type_arg_itemtype() throws Exception { ObjectMapper
	 * objectMapper = new ObjectMapper();
	 * 
	 * StatusResource statusResource = createStatusResource("itemtype"); String
	 * jsonRequest = objectMapper.writeValueAsString(statusResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/status").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty(); }
	 * 
	 * @Test(groups = { "api.status.*" }) public void
	 * should_not_create_when_model_type_arg_other() throws Exception { ObjectMapper
	 * objectMapper = new ObjectMapper();
	 * 
	 * StatusResource statusResource = createStatusResource("other"); String
	 * jsonRequest = objectMapper.writeValueAsString(statusResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/status").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.CONFLICT
	 * .value());
	 * assertThat(mvcResult.getResponse().getContentAsString()).isEmpty(); }
	 * 
	 * @Test(groups = { "api.status.*" }) public void
	 * should_not_create_when_company_type_not_selected() throws Exception {
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * StatusResource statusResource = createStatusResource("other");
	 * 
	 * statusResource.setCompanyid(0);
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(statusResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/status").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.CONFLICT
	 * .value());
	 * assertThat(mvcResult.getResponse().getContentAsString()).isEmpty(); }
	 * 
	 * @Test(groups = { "api.status.*" }) public void
	 * should_update_status_when_status_id_arg_valid() throws Exception {
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * StatusResource statusResource = createStatusResource("locationtype");
	 * statusResource.setStatus("Test Updated"); String jsonRequest =
	 * objectMapper.writeValueAsString(statusResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(put("/api/status/40").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.status.*" }) public void
	 * should_not_update_status_when_status_id_arg_in_valid() throws Exception {
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * StatusResource statusResource = createStatusResource("locationtype");
	 * statusResource.setStatus("Test Updated"); String jsonRequest =
	 * objectMapper.writeValueAsString(statusResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(put("/api/status/99999999").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NOT_FOUND.value()); }
	 * 
	 * @Test(groups = { "api.status.*" }) public void
	 * should_delete_when_arg_status_id_is_valid() throws Exception { ObjectMapper
	 * objectMapper = new ObjectMapper();
	 * 
	 * StatusResource statusResource = createStatusResource("companytype"); String
	 * jsonRequest = objectMapper.writeValueAsString(statusResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/status").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * 
	 * if (mvcResult.getResponse().getStatus() == HttpStatus.OK.value()) {
	 * JSONObject json = new
	 * JSONObject(mvcResult.getResponse().getContentAsString()); Integer statusid =
	 * json.getInt("statusid"); mvcResult =
	 * this.mockMvc.perform(delete("/api/status/" + statusid)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NO_CONTENT.value()); } }
	 * 
	 * @Test(groups = { "api.status.*" }) public void
	 * should_not_delete_when_arg_status_id_is_invalid() throws Exception {
	 * 
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(delete("/api/status/99999999")).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NOT_FOUND.value());
	 * 
	 * }
	 * 
	 * private StatusResource createStatusResource(String modelType) {
	 * 
	 * StatusResource statusResource = new StatusResource();
	 * 
	 * statusResource.setStatusid(1); statusResource.setStatus("Test");
	 * statusResource.setCompanyid(3); statusResource.setDestroyed(true);
	 * statusResource.setEntitytypeid(1); statusResource.setModuleType(modelType);
	 * statusResource.setInservice(true); statusResource.setUnderrepair(true);
	 * statusResource.setSpare(true);
	 * 
	 * return statusResource; }
	 */
}
