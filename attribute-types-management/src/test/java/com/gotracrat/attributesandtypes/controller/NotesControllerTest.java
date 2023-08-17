package com.gotracrat.attributesandtypes.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Date;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gotracrat.attributesandtypes.entity.Journal;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class NotesControllerTest {

	/*
	 * private MockMvc mockMvc;
	 * 
	 * @Autowired private WebApplicationContext wac;
	 * 
	 * @BeforeClass public void init() { mockMvc =
	 * MockMvcBuilders.webAppContextSetup(wac).build(); }
	 * 
	 * @Test(groups = { "api.notes.*" }) public void
	 * get_all_notes_by_module_type_companytype() throws Exception {
	 * 
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/notes/getAllNotes/companytype/3")).andReturn()
	 * ;
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty(); }
	 * 
	 * @Test(groups = { "api.notes.*" }) public void
	 * get_all_notes_by_module_type_locationtype() throws Exception {
	 * 
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/notes/getAllNotes/locationtype/3")).andReturn(
	 * );
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty(); }
	 * 
	 * @Test(groups = { "api.notes.*" }) public void
	 * get_all_notes_by_module_type_itemtype() throws Exception {
	 * 
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/notes/getAllNotes/itemtype/3")).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty(); }
	 * 
	 * @Test(groups = { "api.notes.*" }) public void
	 * get_all_notes_by_module_type_other() throws Exception {
	 * 
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/notes/getAllNotes/other/3")).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.CONFLICT
	 * .value());
	 * assertThat(mvcResult.getResponse().getContentAsString()).isEmpty(); }
	 * 
	 * @Test(groups = { "api.notes.*" }) public void
	 * get_all_notes_when_invalid_company_id() throws Exception {
	 * 
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/notes/getAllNotes/itemtype/99999999")).
	 * andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ());
	 * assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("[]"); }
	 * 
	 * @Test(groups = { "api.notes.*" }) public void
	 * get_note_by_journal_id_when_valid_journal_id() throws Exception {
	 * 
	 * MvcResult mvcResult = this.mockMvc.perform(get("/api/notes/1")).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty(); }
	 * 
	 * @Test(groups = { "api.notes.*" }) public void
	 * get_note_by_journal_id_when_invalid_journal_id() throws Exception {
	 * 
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(get("/api/notes/99999999")).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NOT_FOUND.value());
	 * assertThat(mvcResult.getResponse().getContentAsString()).isEmpty(); }
	 * 
	 * @Test(groups = { "api.notes.*" }) public void
	 * should_createNote_when_module_type_is_companytype() throws Exception {
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * Journal journal = createJournal("companytype");
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(journal);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/notes").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty(); }
	 * 
	 * @Test(groups = { "api.notes.*" }) public void
	 * should_createNote_when_module_type_is_locationtype() throws Exception {
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * Journal journal = createJournal("locationtype");
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(journal);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/notes").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty(); }
	 * 
	 * @Test(groups = { "api.notes.*" }) public void
	 * should_createNote_when_module_type_is_itemtype() throws Exception {
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * Journal journal = createJournal("itemtype");
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(journal);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/notes").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty(); }
	 * 
	 * @Test(groups = { "api.notes.*" }) public void
	 * should_createNote_when_module_type_is_none() throws Exception { ObjectMapper
	 * objectMapper = new ObjectMapper();
	 * 
	 * Journal journal = createJournal("none");
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(journal);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/notes").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.CONFLICT
	 * .value());
	 * assertThat(mvcResult.getResponse().getContentAsString()).isEmpty(); }
	 * 
	 * @Test(groups = { "api.notes.*" }) public void
	 * should_update_journal_when_valid_journal_id() throws Exception {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * Journal journal = createJournal("companytype");
	 * 
	 * journal.setEntry("Test Updated");
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(journal);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(put("/api/notes/19").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value
	 * ()); }
	 * 
	 * @Test(groups = { "api.notes.*" }) public void
	 * should_not_update_journal_when_module_type_is_none() throws Exception {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * Journal journal = createJournal("none");
	 * 
	 * journal.setEntry("Test Updated");
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(journal);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(put("/api/notes/19").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.CONFLICT
	 * .value()); }
	 * 
	 * @Test(groups = { "api.notes.*" }) public void
	 * should_not_update_journal_when_journal_id_is_invalid() throws Exception {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * Journal journal = createJournal("companytype");
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(journal);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(put("/api/notes/99999999").contentType("application/json").content(
	 * jsonRequest)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NOT_FOUND.value()); }
	 * 
	 * @Test(groups = { "api.notes.*" }) public void
	 * should_delete_note_when_module_type_is_companytype() throws Exception {
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * Journal journal = createJournal("companytype");
	 * 
	 * String jsonRequest = objectMapper.writeValueAsString(journal);
	 * 
	 * MvcResult mvcResult = this.mockMvc
	 * .perform(post("/api/notes").contentType("application/json").content(
	 * jsonRequest)).andReturn(); if (mvcResult.getResponse().getStatus() ==
	 * HttpStatus.OK.value()) { JSONObject json = new
	 * JSONObject(mvcResult.getResponse().getContentAsString()); Integer journalId =
	 * json.getInt("journalid"); mvcResult =
	 * this.mockMvc.perform(delete("/api/notes/" + journalId)).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NO_CONTENT.value()); } }
	 * 
	 * @Test(groups = { "api.notes.*" }) public void
	 * should_not_delete_note_when_in_valid_journal_id() throws Exception {
	 * MvcResult mvcResult =
	 * this.mockMvc.perform(delete("/api/notes/99999999")).andReturn();
	 * assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.
	 * NOT_FOUND.value()); }
	 * 
	 * private Journal createJournal(String moduleType) {
	 * 
	 * Date date = new Date();
	 * 
	 * Journal journal = new Journal();
	 * 
	 * journal.setEntityid(1); journal.setModuleType(moduleType);
	 * journal.setEntry("Test"); journal.setEnteredby("hasan");
	 * journal.setEnteredon(date); journal.setEffectiveon(date);
	 * journal.setEntityxml("String"); journal.setEntityname("Test");
	 * journal.setLocationid(67); journal.setCompanyid(3);
	 * 
	 * return journal; }
	 */
}
