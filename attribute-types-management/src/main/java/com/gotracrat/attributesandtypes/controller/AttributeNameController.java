/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:46:08 )
 *
*/
package com.gotracrat.attributesandtypes.controller;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import org.springframework.web.bind.annotation.RestController;

import com.gotracrat.attributesandtypes.controller.resource.AttributenameResource;
import com.gotracrat.attributesandtypes.controller.resource.AttributesearchtypeResource;
import com.gotracrat.attributesandtypes.controller.resource.AttributetypeResource;
import com.gotracrat.attributesandtypes.entity.UserLog;
import com.gotracrat.attributesandtypes.service.AttributenameService;
import com.gotracrat.attributesandtypes.service.AttributetypeService;
import com.gotracrat.attributesandtypes.service.AttributetypesearchtypeService;
import com.gotracrat.attributesandtypes.utils.GotracratUtility;
import com.gotracrat.attributesandtypes.utils.ModulesEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * Rest controller
 * 
 * @author prabhakar
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "/api/attributename")
@Slf4j
public class AttributeNameController {

	private static final String NOT_FOUND = "Attributename not found";

	@Autowired
	private AttributenameService attributenameService;

	@Autowired
	private AttributetypeService attributetypeService;

	@Autowired
	private AttributetypesearchtypeService attributetypesearchtypeService;

	@Operation(summary = "This is to fetch all Attributes by Type ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Attributes found",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping(value = "/getAllAttributes/{typeId}", produces = "application/json")
	public ResponseEntity<List<AttributenameResource>> getAllAttributesByTypeId(
			@PathVariable("typeId") Integer typeId) {
		log.info("Get Attributes by typeId: "+typeId);
		final List<AttributenameResource> attributenameResourceList = attributenameService
				.getAllAttributesByTypeId(typeId);
		log.info("Attributes found");
		return new ResponseEntity<>(attributenameResourceList, HttpStatus.OK);
	}

	@Operation(summary = "This is to fetch Types and Attributes by Company ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Types and Attributes found",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping("/getTypesAndAttributes/{companyId}")
	public ResponseEntity<Map<Integer, List<AttributenameResource>>> getTypesAndAttributes(
			@PathVariable("companyId") Integer companyId) {
		log.info("Get types and attributes by companyId: "+companyId);
		final Map<Integer, List<AttributenameResource>> typesAndAttributes = attributenameService
				.getItemTypeAndAttributes(companyId);
		log.info("Types and Attributes found");
		return new ResponseEntity<>(typesAndAttributes, HttpStatus.OK);
	}

	@Operation(summary = "This is to fetch Attribute by AttributeNameID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Attribute found",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping("{attributenameid}")
	public ResponseEntity<AttributenameResource> getAttribute(
			@PathVariable("attributenameid") Integer attributeNameId) {
		log.info("Get Attribute by Id: "+attributeNameId);
		final AttributenameResource attributeName = attributenameService.getAttribute(attributeNameId);
		if (attributeName == null) {
			log.info(NOT_FOUND);
			return new ResponseEntity<>((AttributenameResource) null, HttpStatus.NOT_FOUND);
		}
		log.info("Attribute found");
		return new ResponseEntity<>(attributeName, HttpStatus.OK);
	}

	@Operation(summary = "This is to create Attribute")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Attribute created successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@PostMapping(consumes = "application/json")
	public ResponseEntity<AttributenameResource> createAttribute(@RequestBody AttributenameResource attributename)
			throws Exception {
		log.info("Attribute creation request");
		String actionComment = "Attribute :" + attributename.getName() + " added for " + attributename.getModuleType()
				+ " type :" + attributename.getType().getName() + " by " + attributename.getLastmodifiedby();
		UserLog userLog = getUserLog(attributename.getCompanyId(), attributename.getLastmodifiedby(),
				ModulesEnum.ATTRIBUTENAME.getModule(), actionComment);
		if (attributename.getType() == null || attributename.getType().getTypeid() == 0) {
			throw new Exception("Type not selected");
		} else if (attributename.getAttributetype() == null
				|| attributename.getAttributetype().getAttributetypeid() == 0) {
			throw new Exception("Please select AttributeType");
		}
		final AttributenameResource created = attributenameService.createAttribute(attributename, userLog);
		log.info("Attribute Created");
		return new ResponseEntity<>(created, HttpStatus.OK);

	}

	@Operation(summary = "This is to update Attribute by AttributeNameID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Attribute updated successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@PutMapping(value = "{attributenameid}", consumes = "application/json")
	public ResponseEntity<Void> update(@PathVariable("attributenameid") Integer attributenameId,
			@RequestBody AttributenameResource attributenameResource) {
		log.info("Attribute update request by Id: "+attributenameId);
		attributenameResource.setAttributenameid(attributenameId);
		String actionComment = "Attribute :" + attributenameResource.getName() + " of "
				+ attributenameResource.getModuleType() + " type :" + attributenameResource.getType().getName()
				+ " Updated by " + attributenameResource.getLastmodifiedby();
		UserLog userLog = getUserLog(attributenameResource.getCompanyId(), attributenameResource.getLastmodifiedby(),
				ModulesEnum.ATTRIBUTENAME.getModule(), actionComment);
		if (attributenameService.exist(attributenameResource)) {
			attributenameService.saveAttribute(attributenameResource, userLog);
			log.info("Attribute updated succesfully");
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			log.info(NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "This is to delete Attribute by AttributeNameID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Attribute deleted successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@DeleteMapping("{attributenameid}/{companyid}/{username}/{attributeName}/{typeName}/{moduleType}")
	public ResponseEntity<String> delete(@PathVariable("attributenameid") Integer attributenameId,
										 @PathVariable("companyid") Integer companyid, @PathVariable("username") String username,
										 @PathVariable("attributeName") String attributeName, @PathVariable("typeName") String typeName,
										 @PathVariable("moduleType") String moduleType) {
		log.info("Attribute deletion request by Id: "+attributenameId);
		String actionComment = "Attribute :" + attributeName + " of " + moduleType + " type :" + typeName
				+ " Deleted by " + username;
		UserLog userLog = getUserLog(companyid, username, ModulesEnum.ATTRIBUTENAME.getModule(), actionComment);
		return ResponseEntity.ok(attributenameService.delete(attributenameId, userLog));
	}

	@Operation(summary = "This is to fetch all AttributeTypes")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "AttributeTypes found",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping("/getAllAttributetypes")
	public ResponseEntity<List<AttributetypeResource>> getAllAttributetypes() {
		log.info("getAllAttributetypes ");
		final List<AttributetypeResource> attributetypeResourceList = attributetypeService.getAllAttributetypes();
		log.info("Attributetypes found");
		return new ResponseEntity<>(attributetypeResourceList, HttpStatus.OK);
	}

	@Operation(summary = "This is to fetch all AttributeSearchType by AttributeTypeID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "All AttributeSearchTypes found",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping("/getAllAttributeSearchType/{attributetypeid}")
	public ResponseEntity<List<AttributesearchtypeResource>> getAllAttributeSearchType(
			@PathVariable("attributetypeid") Integer attributeTypeId) {
		log.info("GetAllAttributeSearchType by attributeTypeId: "+attributeTypeId);
		final List<AttributesearchtypeResource> attributetypeResourceList = attributetypesearchtypeService
				.getAllAttributeSearchType(attributeTypeId);
		log.info("GetAllAttributeSearchTypes found : {}", attributetypeResourceList);
		return new ResponseEntity<>(attributetypeResourceList, HttpStatus.OK);
	}

	@Operation(summary = "This is to update Attribute Order")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Attribute Order updated successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@PutMapping(consumes = "application/json")
	public ResponseEntity<List<AttributenameResource>> updateAttributeOrder(
			@RequestBody List<AttributenameResource> attributenamelist) throws Exception {
		log.info("Attribute order change request");
		final List<AttributenameResource> updated = attributenameService.updateAttributeOrder(attributenamelist);
		log.info("Attribute order updated");
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	private UserLog getUserLog(Integer companyId, String userName, String userAction, String actionComment) {
		return GotracratUtility.getUserLog(userName, companyId, userAction, actionComment);
	}

}
