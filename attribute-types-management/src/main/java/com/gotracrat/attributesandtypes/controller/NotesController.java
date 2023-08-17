/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:58 )
 * 
*/
package com.gotracrat.attributesandtypes.controller;

import java.util.ArrayList;
import java.util.List;

import com.gotracrat.attributesandtypes.service.JournalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gotracrat.attributesandtypes.controller.resource.JournalResource;
import com.gotracrat.attributesandtypes.entity.Journal;
import com.gotracrat.attributesandtypes.entity.UserLog;
import com.gotracrat.attributesandtypes.repository.JournalDAO;
import com.gotracrat.attributesandtypes.utils.EntityTypeIDEnum;
import com.gotracrat.attributesandtypes.utils.GoTracratConstants;
import com.gotracrat.attributesandtypes.utils.GotracratUtility;
import com.gotracrat.attributesandtypes.utils.JournalTypeIDEnum;
import com.gotracrat.attributesandtypes.utils.ModulesEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * Rest controller
 * 
 * @author prabhakar
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "/api/notes")
@Slf4j
public class NotesController {

	private static final String NOT_FOUND = "Journal not found";
	private static final String CONFLICT = "Note parameter has conflict";

	@Autowired
	private JournalService journalService;

	@Autowired
	private JournalDAO journalDAO;

	@Operation(summary = "This is to fetch all Notes by Company ID and Entity ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Notes Found",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping("getAllNotes/{moduleType}/{companyID}/{entityId}")
	public ResponseEntity<List<JournalResource>> getAllNotes(@PathVariable("companyID") int companyId,
			@PathVariable("moduleType") String moduleType, @PathVariable("entityId") int entityId) {
		log.info("Get "+moduleType+" Notes by companyId and entityId: "+companyId+","+entityId);
		int entityTypeId = this.findEntityTypeId(companyId, moduleType);
		if (entityTypeId == 0) {
			log.info(CONFLICT);
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		final List<Journal> notesList = journalService.getAllNotes(JournalTypeIDEnum.NOTES_JOURNALTYPE_ID.typeID(),
				companyId, entityTypeId, entityId);
		final List<JournalResource> JournalResourceList = new ArrayList<>();
		if (notesList != null) {
			for (Journal journal : notesList) {
				JournalResource journalResource = new JournalResource();
				BeanUtils.copyProperties(journal, journalResource);
				JournalResourceList.add(journalResource);
			}
		}
		log.info("Notes Found");
		return new ResponseEntity<>(JournalResourceList, HttpStatus.OK);
	}
	@Operation(summary = "This is to fetch all Changes Log by Company ID and Entity ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "ChangesLog Found",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping("getAllChangesLog/{moduleType}/{companyID}/{entityId}")
	public ResponseEntity<List<JournalResource>> getAllChangesLog(@PathVariable("companyID") int companyId,
			@PathVariable("moduleType") String moduleType, @PathVariable("entityId") int entityId) {
		log.info("Get Item ChangeLog by companyId and entityId: "+companyId+","+entityId);
		int entityTypeId = this.findEntityTypeId(companyId, moduleType);
		if (entityTypeId == 0) {
			log.info(CONFLICT);
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		final List<Journal> notesList = journalService.getAllNotes(companyId, entityTypeId, entityId);
		final List<JournalResource> JournalResourceList = new ArrayList<>();
		if (notesList != null) {
			for (Journal journal : notesList) {
				JournalResource journalResource = new JournalResource();
				BeanUtils.copyProperties(journal, journalResource);
				JournalResourceList.add(journalResource);
			}
		}
		log.info("ChangeLog Found");
		return new ResponseEntity<>(JournalResourceList, HttpStatus.OK);
	}

	@Operation(summary = "This is to fetch Note by Journal ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Note Found",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping("{journalId}")
	public ResponseEntity<JournalResource> getNote(@PathVariable("journalId") Integer journalId) {
		log.info("Get Note by Id: "+journalId);
		final Journal journal = journalService.get(journalId);
		if (journal == null) {
			log.info(NOT_FOUND);
			return new ResponseEntity<>((JournalResource) null, HttpStatus.NOT_FOUND);
		}
		log.info("Note Found");
		JournalResource journalResource = new JournalResource();
		BeanUtils.copyProperties(journal, journalResource);
		return new ResponseEntity<>(journalResource, HttpStatus.OK);
	}

	@Operation(summary = "This is to create Note")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Note created successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@PostMapping(consumes = "application/json")
	public ResponseEntity<JournalResource> createNote(@RequestBody Journal journal) {
		log.info("Note creation request");
		int entityTypeId = this.findEntityTypeId(journal.getCompanyid(), journal.getModuleType());
		if (entityTypeId == 0) {
			log.info(CONFLICT);
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		journal.setEntitytypeid(entityTypeId);
		journal.setJournaltypeid(JournalTypeIDEnum.NOTES_JOURNALTYPE_ID.typeID());
		if (entityTypeId == 2) {
			Integer locationId = journalDAO.getLocationId(journal.getEntityid());
			journal.setLocationid(locationId);
		}
		String actionComment = journalService.getActionComment(journal, GoTracratConstants.CREATE_NOTE_TYPE);
		UserLog userLog = GotracratUtility.getUserLog(journal.getEnteredby(), journal.getCompanyid(),
				ModulesEnum.NOTES.getModule(), actionComment);
		final Journal created = journalService.create(journal, userLog);
		log.info("Note Created Successfully");
		JournalResource journalResource = new JournalResource();
		BeanUtils.copyProperties(created, journalResource);
		return new ResponseEntity<>(journalResource, HttpStatus.OK);
	}

	@Operation(summary = "This is to update Notes by Journal ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Notes updated successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@PutMapping(value = "{journalid}", consumes = "application/json")
	public ResponseEntity<Void> updateNotes(@PathVariable("journalid") Integer journalid,
			@RequestBody Journal journal) {
		log.info("Note update request by Id: "+journalid);
		journal.setJournalid(journalid);
		int entitytypeid = this.findEntityTypeId(journal.getCompanyid(), journal.getModuleType());
		if (entitytypeid == 0) {
			log.info(CONFLICT);
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		journal.setEntitytypeid(entitytypeid);
		journal.setJournaltypeid(JournalTypeIDEnum.NOTES_JOURNALTYPE_ID.typeID());
		String actionComment = journalService.getActionComment(journal, GoTracratConstants.UPDATE_NOTE_TYPE);
		UserLog userLog = GotracratUtility.getUserLog(journal.getEnteredby(), journal.getCompanyid(),
				ModulesEnum.NOTES.getModule(), actionComment);
		if (journalService.save(journal, userLog)) {
			log.info("Note updated succesfully");
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "This is to delete Note")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Note deleted successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@DeleteMapping("{journalid}/{userName}")
	public ResponseEntity<String> deleteNote(@PathVariable("journalid") Integer journalid,
											 @PathVariable("userName") String userName,
											 @RequestParam(name = "location", required = false) String locationName,
											 @RequestParam(name = "tag", required = false) String itemTag,
											 @RequestParam(name = "type", required = false) String itemTypeName) {
		log.info("Note deletion request by Id: " + journalid);
		Journal journalTemp = new Journal();
		journalTemp.setLocationname(locationName);
		journalTemp.setItemTag(itemTag);
		journalTemp.setItemTypeName(itemTypeName);
		journalTemp.setEnteredby(userName);
		return ResponseEntity.ok(journalService.delete(journalid, new UserLog(), journalTemp));
	}

	private int findEntityTypeId(int companyId, String moduleType) {
		if (moduleType != null && !moduleType.isEmpty() && companyId != 0) {
			if (moduleType.equalsIgnoreCase(GoTracratConstants.COMPANY_MODULE_TYPE)) {
				return EntityTypeIDEnum.COMPANY_TYPE_ENTITYTYPE_ID.entityTypeID();
			} else if (moduleType.equalsIgnoreCase(GoTracratConstants.LOCATION_MODULE_TYPE)) {
				return EntityTypeIDEnum.LOCATION_TYPE_ENTITYTYPE_ID.entityTypeID();
			} else if (moduleType.equalsIgnoreCase(GoTracratConstants.ITEM_MODULE_TYPE)) {
				return EntityTypeIDEnum.ITEM_TYPE_ENTITYTYPE_ID.entityTypeID();
			} else if (moduleType.equalsIgnoreCase(GoTracratConstants.ITEM_MODULE_NOTE_TYPE)) {
				return EntityTypeIDEnum.ITEM_TYPE_NOTE_ENTITYTYPE_ID.entityTypeID();
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

}
