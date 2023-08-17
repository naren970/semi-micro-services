package com.gotracrat.attributesandtypes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AttachmentControllerTest  {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private ResourceLoader resourceLoader;

	/*@BeforeClass
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test(groups = { "api.attachment.*" })
	public void get_all_attachments_when_arg_model_type_is_companytype() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/api/attachment/getAllAttachments/companytype/14")).andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty().isNotEqualTo("[]");
	}

	@Test(groups = { "api.attachment.*" })
	public void get_all_attachments_when_arg_model_type_is_locationtype() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/api/attachment/getAllAttachments/locationtype/3")).andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty().isNotEqualTo("[]");
	}

	@Test(groups = { "api.attachment.*" })
	public void get_all_attachments_when_arg_model_type_is_itemtype() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/api/attachment/getAllAttachments/itemtype/67")).andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty().isNotEqualTo("[]");
	}
	
	@Test(groups = { "api.attachment.*" })
	public void get_all_attachments_when_arg_model_type_is_other() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/api/attachment/getAllAttachments/other/67")).andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		assertThat(mvcResult.getResponse().getContentAsString()).isEmpty();
	}
	
	@Test(groups = { "api.attachment.*" })
	public void get_all_attachments_when_arg_model_type_is_empty() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/api/attachment/getAllAttachments/''/0")).andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		assertThat(mvcResult.getResponse().getContentAsString()).isEmpty();
	}
	
	@Test(groups = { "api.attachment.*" })
	public void get_all_attachments_when_arg_entity_is_invalid() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/api/attachment/getAllAttachments/itemtype/99999999")).andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty().isEqualTo("[]");
	}
	
	@Test(groups = { "api.attachment.*" })
	public void get_all_attachment_when_arg_attachment_is_valid() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/api/attachment/26")).andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty();
	}
	
	@Test(groups = { "api.attachment.*" })
	public void get_all_attachment_when_arg_attachment_is_invalid() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/api/attachment/99999999")).andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		assertThat(mvcResult.getResponse().getContentAsString()).isEmpty();
	}

	@Test(groups = { "api.attachment.*" })
	public void should_create_attachment_when_module_type_is_companytype() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		AttachmentResource attachmentResource = createAttachmentResource("companytype");

		String jsonRequest = objectMapper.writeValueAsString(attachmentResource);

		MvcResult mvcResult = this.mockMvc
				.perform(post("/api/attachment").contentType("application/json").content(jsonRequest)).andReturn();
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty();
	}

	@Test(groups = { "api.attachment.*" })
	public void should_create_attachment_when_module_type_is_locationtype() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		AttachmentResource attachmentResource = createAttachmentResource("locationtype");

		String jsonRequest = objectMapper.writeValueAsString(attachmentResource);

		MvcResult mvcResult = this.mockMvc
				.perform(post("/api/attachment").contentType("application/json").content(jsonRequest)).andReturn();
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty();
	}

	@Test(groups = { "api.attachment.*" })
	public void should_create_attachment_when_module_type_is_itemtype() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		AttachmentResource attachmentResource = createAttachmentResource("itemtype");

		String jsonRequest = objectMapper.writeValueAsString(attachmentResource);

		MvcResult mvcResult = this.mockMvc
				.perform(post("/api/attachment").contentType("application/json").content(jsonRequest)).andReturn();
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty();
	}

	@Test(groups = { "api.attachment.*" })
	public void should_not_create_attachment_when_module_type_is_other() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		AttachmentResource attachmentResource = createAttachmentResource("other");

		String jsonRequest = objectMapper.writeValueAsString(attachmentResource);

		MvcResult mvcResult = this.mockMvc
				.perform(post("/api/attachment").contentType("application/json").content(jsonRequest)).andReturn();
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
		assertThat(mvcResult.getResponse().getContentAsString()).isEmpty();
	}

	@Test(groups = { "api.attachment.*" })
	public void should_not_create_attachment_when_module_type_not_selected() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		AttachmentResource attachmentResource = createAttachmentResource("");
		attachmentResource.setEntityid(0);

		String jsonRequest = objectMapper.writeValueAsString(attachmentResource);

		MvcResult mvcResult = this.mockMvc
				.perform(post("/api/attachment").contentType("application/json").content(jsonRequest)).andReturn();
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
		assertThat(mvcResult.getResponse().getContentAsString()).isEmpty();
	}

	@Test(groups = { "api.attachment.*" })
	public void should_not_create_attachment_when_attachment_file_is_empty() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		AttachmentResource attachmentResource = createAttachmentResource("");
		attachmentResource.setAttachmentFile("");

		String jsonRequest = objectMapper.writeValueAsString(attachmentResource);

		MvcResult mvcResult = this.mockMvc
				.perform(post("/api/attachment").contentType("application/json").content(jsonRequest)).andReturn();
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
		assertThat(mvcResult.getResponse().getContentAsString()).isEmpty();
	}

	@Test(groups = { "api.type.*" })
	public void should_update_when_valid_arg_attachmet_id() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		AttachmentResource attachmentResource = createAttachmentResource("companytype");
		attachmentResource.setDescription("Test Updated");

		String jsonRequest = objectMapper.writeValueAsString(attachmentResource);

		MvcResult mvcResult = this.mockMvc
				.perform(put("/api/attachment/24").contentType("application/json").content(jsonRequest)).andReturn();
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	@Test(groups = { "api.type.*" })
	public void should_not_update_when_invalid_arg_attachmet_id() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		AttachmentResource attachmentResource = createAttachmentResource("companytype");
		attachmentResource.setDescription("Test Updated");

		String jsonRequest = objectMapper.writeValueAsString(attachmentResource);

		MvcResult mvcResult = this.mockMvc
				.perform(put("/api/attachment/99999999").contentType("application/json").content(jsonRequest))
				.andReturn();
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}

	@Test(groups = { "api.attachment.*" })
	public void should_delete_attachment_when_valid_arg_attribute_id() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		AttachmentResource attachmentResource = createAttachmentResource("itemtype");

		String jsonRequest = objectMapper.writeValueAsString(attachmentResource);

		MvcResult mvcResult = this.mockMvc
				.perform(post("/api/attachment").contentType("application/json").content(jsonRequest)).andReturn();
		if (mvcResult.getResponse().getStatus() == HttpStatus.OK.value()) {
			JSONObject json = new JSONObject(mvcResult.getResponse().getContentAsString());
			Integer attachmentid = json.getInt("attachmentid");
			mvcResult = this.mockMvc.perform(delete("/api/attachment/" + attachmentid)).andReturn();
			assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
		}
	}

	@Test(groups = { "api.attachment.*" })
	public void should_not_delete_attachment_when_invalid_arg_attribute_id() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(delete("/api/attachment/99999999")).andReturn();
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}

	private AttachmentResource createAttachmentResource(String moduleType) throws Exception {

		Resource resource = resourceLoader.getResource("classpath:test-data/test_data.txt");

		String fileName = resource.getFilename();

		String filepath = resource.getFile().getAbsolutePath();

		AttachmentResource attachmentResource = new AttachmentResource();

		attachmentResource.setEntityid(67);
		attachmentResource.setModuleType(moduleType);
		attachmentResource.setFilename(fileName);
		attachmentResource.setContenttype("text/plain");
		attachmentResource.setAddedby("Hasan");
		attachmentResource.setAttachmentFile(filepath);
		attachmentResource.setDescription("Test File");
		attachmentResource.setDateadded(new Date());

		return attachmentResource;
	}*/
}
