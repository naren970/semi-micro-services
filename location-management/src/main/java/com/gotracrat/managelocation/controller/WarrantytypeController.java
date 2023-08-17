package com.gotracrat.managelocation.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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
import com.gotracrat.managelocation.resource.WarrantyTypeResource;
import com.gotracrat.managelocation.entity.UserLog;
import com.gotracrat.managelocation.service.WarrantyTypeService;
import com.gotracrat.managelocation.utils.GoTracratConstants;
import com.gotracrat.managelocation.utils.GotracratUtility;
import com.gotracrat.managelocation.utils.ModulesEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * Rest controller
 * @author prabhakar
 * @since 2018-08-13
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "/api/v1/warrantytype")
@Slf4j
public class WarrantytypeController {

	@Autowired
	private WarrantyTypeService warrantyTypeService;

	@Operation(summary = "This is to fetch all Warranty Type by Company ID")
	@io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Warranty Type found",
					content = {@Content(mediaType = "application/json")}),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
					description = "Warranty Type not found",
					content = @Content)
	})
	@GetMapping("getAlltWarrantytypeByCompanyId/{companyId}")
	public ResponseEntity<List<WarrantyTypeResource>> getAllWarrantyTypeByCompanyId(
			@PathVariable("companyId") final Integer companyId) {
		log.info("Get Warranty Types by companyId: " + companyId);
		return ResponseEntity.ok(warrantyTypeService.getAllWarrantyTypeByCompanyId(companyId));
	}

	@Operation(summary = "This is to fetch Warranty Type by Warranty Type ID")
	@io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Warranty Type found",
					content = {@Content(mediaType = "application/json")}),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
					description = "Warranty Type not found",
					content = @Content)
	})
	@GetMapping("{warrantyTypeId}")
	public ResponseEntity<WarrantyTypeResource> getWarrantyType(
			@PathVariable("warrantyTypeId") final Integer warrantyTypeId) {
		log.info("Get WarrantyType by Id: " + warrantyTypeId);
		return ResponseEntity.ok(warrantyTypeService.getWarrantyType(warrantyTypeId));
	}

	@Operation(summary = "This is to create Warranty Type")
	@io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Warranty Type created successfully",
					content = {@Content(mediaType = "application/json")}),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
					description = "Warranty Type not found",
					content = @Content)
	})
	@PostMapping(consumes = "application/json")
	public ResponseEntity<WarrantyTypeResource> createWarrantyType(
			@RequestBody final WarrantyTypeResource warrantyTypeResource) {
		log.info("WarrantyType creation request");
		String actionComment = "WarrantyType:" + warrantyTypeResource.getWarrantytype() + " "
				+ GoTracratConstants.CREATED_BY + " " + warrantyTypeResource.getUserName();
		UserLog userLog = GotracratUtility.getUserLog( warrantyTypeResource.getUserName(),warrantyTypeResource.getCompanyid(),
				ModulesEnum.WARRANTYTYPE.getModule(), actionComment);
		return ResponseEntity.ok(warrantyTypeService.createWarrantyType(warrantyTypeResource, userLog));
	}

	@Operation(summary = "This is to update Warranty Type by Warranty Type ID")
	@io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Warranty Type updated successfully",
					content = {@Content(mediaType = "application/json")}),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
					description = "Warranty Type not found",
					content = @Content)
	})
	@PutMapping(value = "{warrantyTypeId}", consumes = "application/json")
	public ResponseEntity<Void> updateWarrantyType(@PathVariable("warrantyTypeId") final Integer warrantyTypeId,
													 @RequestBody final WarrantyTypeResource warrantyTypeResource) {
		log.info("WarrantyType update request by Id: " + warrantyTypeId);
		String actionComment = "WarrantyType:" + warrantyTypeResource.getWarrantytype() + " "
				+ GoTracratConstants.UPDATED_BY + " " + warrantyTypeResource.getUserName();
		UserLog userLog = GotracratUtility.getUserLog(warrantyTypeResource.getUserName(),warrantyTypeResource.getCompanyid(),
				ModulesEnum.WARRANTYTYPE.getModule(), actionComment);
		warrantyTypeResource.setWarrantytypeid(warrantyTypeId);
		warrantyTypeService.updateWarrantyType(warrantyTypeResource, userLog);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Operation(summary = "This is to delete Warranty Type by WarrantyType ID, Company ID, WarrantyType Name and User Name")
	@io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Warranty Type deleted successfully",
					content = {@Content(mediaType = "application/json")}),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
					description = "Warranty Type not found",
					content = @Content)
	})
	@DeleteMapping("{warrantyTypeId}/{companyId}/{userName}/{warrantyTypeName}")
	public ResponseEntity<String> delete(@PathVariable("warrantyTypeId") Integer warrantyTypeId,
									   @PathVariable("companyId") Integer companyId, @PathVariable("userName") String userName,
									   @PathVariable("warrantyTypeName") String warrantyTypeName) {
		log.info("WarrantyType deletion request: " + warrantyTypeId);
		String actionComment = "WarrantyType:" + warrantyTypeName + " " + GoTracratConstants.DELETED_BY + " " + userName;
		UserLog userLog = GotracratUtility.getUserLog(userName,companyId, ModulesEnum.WARRANTYTYPE.getModule(), actionComment);
		return ResponseEntity.ok(warrantyTypeService.delete(warrantyTypeId, userLog));
	}
}
