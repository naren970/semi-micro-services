package com.gotracrat.company.companymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class VendorControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;
	/*
	 * @BeforeClass public void init() { mockMvc =
	 * MockMvcBuilders.webAppContextSetup(wac).build(); }
	 * 
	 * @Test(groups = { "api.vendor.*" }) public void
	 * get_all_vendor_when_VENDOR_FLAG_is_false() throws Exception { boolean
	 * VENDOR_FLAG_PREV = GoTracratContants.VENDOR_FLAG;
	 * 
	 * GoTracratContants.VENDOR_FLAG = false; MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/vendor/getAllVendors/3")).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty().
	 * isNotEqualTo("[]");
	 * 
	 * GoTracratContants.VENDOR_FLAG = VENDOR_FLAG_PREV; }
	 * 
	 * @Test(groups = { "api.vendor.*" }) public void
	 * get_all_vendor_when_VENDOR_FLAG_is_true() throws Exception { boolean
	 * VENDOR_FLAG_PREV = GoTracratContants.VENDOR_FLAG;
	 * 
	 * GoTracratContants.VENDOR_FLAG = true; MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/vendor/getAllVendors/3")).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty().
	 * isNotEqualTo("[]");
	 * 
	 * GoTracratContants.VENDOR_FLAG = VENDOR_FLAG_PREV; }
	 * 
	 * @Test(groups = { "api.vendor.*" }) public void
	 * get_vendor_when_valid_company_arg() throws Exception {
	 * 
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/vendor/75")).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty(); }
	 * 
	 * @Test(groups = { "api.vendor.*" }) public void
	 * get_vendor_when_not_valid_company_arg() throws Exception {
	 * 
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/vendor/99999999")).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NOT_FOUND.value());
	 * assertThat(mvcResult.getResponse().getContentAsString()).isEmpty(); }
	 * 
	 * @Test(groups = { "api.vendor.*" }) public void should_create_company() throws
	 * Exception { ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * CompanyResource companyResource = createCompanyResource(); String jsonRequest
	 * = objectMapper.writeValueAsString(companyResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/vendor/addVendor").contentType("application/json").
	 * content(jsonRequest)) .andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty(); }
	 * 
	 * @Test(groups = { "api.vendor.*" }) public void should_update_company() throws
	 * Exception { ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * CompanyResource companyResource = createCompanyResource();
	 * companyResource.setDescription("Updated"); String jsonRequest =
	 * objectMapper.writeValueAsString(companyResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(put("/api/vendor/43").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.vendor.*" }) public void
	 * should_not_update_company_when_invalid() throws Exception { ObjectMapper
	 * objectMapper = new ObjectMapper();
	 * 
	 * CompanyResource companyResource = createCompanyResource();
	 * companyResource.setDescription("Updated"); String jsonRequest =
	 * objectMapper.writeValueAsString(companyResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(put("/api/vendor/99999999").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * 
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NOT_FOUND.value()); }
	 * 
	 * @Test(groups = { "api.vendor.*" }) public void
	 * should_delete_company_details() throws Exception { ObjectMapper objectMapper
	 * = new ObjectMapper();
	 * 
	 * CompanyResource companyResource = createCompanyResource(); String jsonRequest
	 * = objectMapper.writeValueAsString(companyResource);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/vendor/addVendor").contentType("application/json").
	 * content(jsonRequest)) .andReturn();
	 * 
	 * if (mvcResult.getResponse().getStatus() == HttpStatus.OK.value()) {
	 * JSONObject json = new
	 * JSONObject(mvcResult.getResponse().getContentAsString()); Integer companyid =
	 * json.getInt("companyid"); mvcResult =
	 * this.mockMvc.perform(delete("/api/vendor/" + companyid)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NO_CONTENT.value()); } }
	 * 
	 * @Test(groups = { "api.vendor.*" }) public void
	 * should_not_delete_company_details_when_invalid_arg() throws Exception {
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(delete("/api/vendor/99999999")).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NOT_FOUND.value()); }
	 * 
	 * private CompanyResource createCompanyResource() {
	 * 
	 * CompanyResource companyResource = new CompanyResource();
	 * 
	 * List<AttributevalueResource> attributevalues = new
	 * ArrayList<AttributevalueResource>();
	 * 
	 * AttributevalueResource attributevalueResource = new AttributevalueResource();
	 * 
	 * AttributenameResource attributenameResource = new AttributenameResource();
	 * 
	 * attributenameResource.setAttributenameid(71);
	 * 
	 * attributevalueResource.setAttributename(attributenameResource);
	 * attributevalueResource.setValue("Test");
	 * attributevalueResource.setEntityid(3);
	 * attributevalueResource.setEntitytypeid(3);
	 * attributevalueResource.setLastmodifiedby("Test");
	 * 
	 * attributevalues.add(attributevalueResource);
	 * 
	 * companyResource.setParentcompanyResourceList(Collections.EMPTY_LIST);
	 * 
	 * companyResource.setAttributevalues(attributevalues);
	 * 
	 * Announcement announcement = new Announcement();
	 * announcement.setAnnouncementtext("Test");
	 * announcement.setAnnouncementdate(new Date());
	 * 
	 * Type type = new Type();
	 * 
	 * type.setEntitytypeid(9); type.setIshidden(true);
	 * type.setLastmodifiedby("test"); type.setHostingfee(BigDecimal.ZERO);
	 * type.setAttributesearchdisplay(3); type.setTypemtbs(3);
	 * type.setTypespareratio(BigDecimal.ZERO);
	 * 
	 * Status status = new Status();
	 * 
	 * status.setStatus("Test"); status.setDestroyed(true);
	 * status.setEntitytypeid(3); status.setInservice(true);
	 * status.setUnderrepair(true); status.setSpare(true);
	 * 
	 * companyResource.setName("TEST"); companyResource.setAddress1("test street");
	 * companyResource.setAddress2("test 1331");
	 * companyResource.setCity("Test City"); companyResource.setState("AP");
	 * companyResource.setPostalcode("123456");
	 * companyResource.setPhone("14543466676"); companyResource.setFax("12347556");
	 * companyResource.setDescription("Test Company");
	 * companyResource.setSupplylevelwarning(true);
	 * companyResource.setIssandbox(true);
	 * 
	 * CompanyResource company = new CompanyResource();
	 * 
	 * company.setCompanyid(63); companyResource.setParentcompany(company);
	 * 
	 * companyResource.setAnnouncement(announcement); companyResource.setType(type);
	 * companyResource.setStatus(status);
	 * 
	 * return companyResource; }
	 */
}
