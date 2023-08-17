package com.gotracrat.attributesandtypes.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TypeControllerTest {

	/*
	 * private MockMvc mockMvc;
	 * 
	 * @Autowired private WebApplicationContext wac;
	 * 
	 * @BeforeClass public void init() { mockMvc =
	 * MockMvcBuilders.webAppContextSetup(wac).build(); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * get_all_type_when_arg_model_type_is_companytype() throws Exception {
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/type/getAllType/companytype/1")).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * get_all_type_when_arg_model_type_is_locationtype() throws Exception {
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/type/getAllType/locationtype/1")).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * get_all_type_when_arg_model_type_is_itemtype() throws Exception { MvcResult
	 * mvcResult =
	 * this.mockMvc.perform(get("/api/type/getAllType/itemtype/1")).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * get_all_type_when_arg_model_type_is_other() throws Exception { MvcResult
	 * mvcResult =
	 * this.mockMvc.perform(get("/api/type/getAllType/none/1")).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isEmpty(); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * get_all_type_when_arg_company_type_is_not_selected() throws Exception {
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/type/getAllType/none/0")).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isEmpty(); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * get_all_Type_With_Hierarchy_when_model_type_is_companytype() throws Exception
	 * { MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/type/getAllTypeWithHierarchy/companytype/1")).
	 * andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * get_all_Type_With_Hierarchy_when_model_type_is_locationtype() throws
	 * Exception { MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/type/getAllTypeWithHierarchy/locationtype/1"))
	 * .andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * get_all_Type_With_Hierarchy_when_model_type_is_other() throws Exception {
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/type/getAllTypeWithHierarchy/none/1")).
	 * andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * get_all_Type_With_Hierarchy_when_model_type_is_not_selected() throws
	 * Exception { MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/type/getAllTypeWithHierarchy/none/0")).
	 * andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void get_Type_With_valid_type_id()
	 * throws Exception { MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/type/50")).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotNull().
	 * isNotEmpty(); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void get_Type_With_invalid_type_id()
	 * throws Exception { MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/type/999")).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NOT_FOUND.value());
	 * assertThat(mvcResult.getResponse().getContentAsString()).isEmpty(); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * should_create_when_model_type_arg_companytype() throws Exception {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * TypeResource typeResource = createTypeResourceObject("companytype");
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(typeResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/type/").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty(); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * should_create_when_model_type_arg_locationtype() throws Exception {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * TypeResource typeResource = createTypeResourceObject("locationtype");
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(typeResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/type/").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty(); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * should_create_when_model_type_arg_itemtype() throws Exception {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * TypeResource typeResource = createTypeResourceObject("itemtype");
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(typeResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/type/").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty(); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * should_create_when_model_type_arg_other() throws Exception {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * TypeResource typeResource = createTypeResourceObject("other");
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(typeResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/type/").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.CONFLICT
	 * .value());
	 * assertThat(mvcResult.getResponse().getContentAsString()).isEmpty(); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * should_create_when_model_type_arg_empty() throws Exception {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * TypeResource typeResource = createTypeResourceObject("");
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(typeResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/type/").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.CONFLICT
	 * .value());
	 * assertThat(mvcResult.getResponse().getContentAsString()).isEmpty(); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * should_create_when_company_id_not_selected() throws Exception {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * TypeResource typeResource = createTypeResourceObject("");
	 * 
	 * CompanyResource company = typeResource.getCompany();
	 * 
	 * company.setCompanyid(0);
	 * 
	 * typeResource.setCompany(company);
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(typeResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/type/").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.CONFLICT
	 * .value());
	 * assertThat(mvcResult.getResponse().getContentAsString()).isEmpty(); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * should_update_when_model_type_arg_companytype() throws Exception {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * TypeResource typeResource = createTypeResourceObject("companytype");
	 * 
	 * typeResource.setDescription("Test Updated");
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(typeResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(put("/api/type/189").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * should_update_when_invalid_type_id() throws Exception {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * TypeResource typeResource = createTypeResourceObject("companytype");
	 * 
	 * typeResource.setDescription("Test Updated");
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(typeResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(put("/api/type/999").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NOT_FOUND.value()); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * should_delete_when_valid_type_id() throws Exception {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * TypeResource typeResource = createTypeResourceObject("companytype");
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(typeResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/type/").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * 
	 * if (mvcResult.getResponse().getStatus() == HttpStatus.OK.value()) {
	 * JSONObject json = new
	 * JSONObject(mvcResult.getResponse().getContentAsString()); Integer typeId =
	 * json.getInt("typeid"); mvcResult = this.mockMvc.perform(delete("/api/type/" +
	 * typeId)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NO_CONTENT.value()); } }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * should_delete_when_invalid_type_id() throws Exception { MvcResult mvcResult =
	 * this.mockMvc.perform(delete("/api/type/999")).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NOT_FOUND.value()); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * get_all_type_hierarchy_when_arg_model_type_is_companytype() throws Exception
	 * { MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/type/getAllTypeHierarchy/companytype/1")).
	 * andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * get_all_type_hierarchy_when_arg_model_type_is_locationtype() throws Exception
	 * { MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/type/getAllTypeHierarchy/locationtype/1")).
	 * andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * get_all_type_hierarchy_when_arg_model_type_is_itemtype() throws Exception {
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/type/getAllTypeHierarchy/itemtype/1")).
	 * andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * get_all_type_hierarchy_when_arg_model_type_is_other() throws Exception {
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/type/getAllTypeHierarchy/none/1")).andReturn()
	 * ;
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isEmpty(); }
	 * 
	 * @Test(groups = { "api.type.*" }) public void
	 * get_all_type_hierarchy_when_arg_company_type_is_not_selected() throws
	 * Exception { MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/type/getAllTypeHierarchy/none/0")).andReturn()
	 * ;
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isEmpty(); }
	 * 
	 * private TypeResource createTypeResourceObject(String type) { TypeResource
	 * typeResource = new TypeResource();
	 * 
	 * CompanyResource companyResource = new CompanyResource();
	 * 
	 * companyResource.setCompanyid(3);
	 * 
	 * typeResource.setName("Test"); typeResource.setDescription("Test Desc");
	 * typeResource.setModuleType(type); typeResource.setEntitytypeid(9);
	 * typeResource.setIshidden(true); typeResource.setLastmodifiedby("test");
	 * typeResource.setHostingfee(BigDecimal.ZERO);
	 * typeResource.setAttributesearchdisplay(1); typeResource.setTypemtbs(1);
	 * typeResource.setTypespareratio(BigDecimal.ZERO);
	 * 
	 * TypeResource resource = new TypeResource(); resource.setTypeid(0);
	 * 
	 * typeResource.setParentid(resource);
	 * 
	 * typeResource.setCompany(companyResource);
	 * 
	 * return typeResource; }
	 */
}
