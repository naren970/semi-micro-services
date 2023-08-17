/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:59 )
 * 
*/
package com.gotracrat.attributesandtypes.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.gotracrat.attributesandtypes.controller.resource.StatusResource;
import com.gotracrat.attributesandtypes.entity.Status;
import com.gotracrat.attributesandtypes.entity.UserLog;
import com.gotracrat.attributesandtypes.repository.StatusRepository;
import com.gotracrat.attributesandtypes.service.StatusService;
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
@RequestMapping(value = "/api/status")
@Slf4j
public class StatusController {

	private static final String NOT_FOUND = "Status not found";
	private static final String STATUS_FOUND =" Status found";
	private static final String CONFLICT = "Status boby parameter has conflict";

	@Autowired
	private StatusService statusService;

	@Autowired
	private StatusRepository statusRepository;

	/**
	 * @param companyID
	 * @param moduleType
	 * @return
	 */
	@Operation(summary = "This is to fetch all Status by Company ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Status found",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping("getAllStatusByCompanyId/{moduletype}/{companyID}")
	public ResponseEntity<List<StatusResource>> getAllStatusByCompanyId(@PathVariable("companyID") Integer companyID,
			@PathVariable("moduletype") String moduleType) {
		log.info(moduleType+" Status get by companyId: "+companyID);
		final List<StatusResource> statusList = statusService.getAllStatusByCompanyId(companyID, moduleType);
		if (statusList == null) {
			log.info(NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		log.info(STATUS_FOUND);
		return new ResponseEntity<>(statusList, HttpStatus.OK);
	}

	/**
	 * @param companyID
	 * @return
	 */
	@Operation(summary = "This is to fetch all ItemStatus for Tablet by Company ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "ItemStatus for Tablet found",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping("getAllItemStatusTablet/{companyID}")
	public ResponseEntity<List<StatusResource>> getAllItemStatusTablet(@PathVariable("companyID") Integer companyID) {
		log.info("Get All Item Status for Tablet with companyId:"+companyID);
		final List<StatusResource> statusList = statusService.getAllItemStatusTablet(companyID);
		log.info(STATUS_FOUND);
		return new ResponseEntity<>(statusList, HttpStatus.OK);
	}

	@Operation(summary = "This is to fetch Status by Status ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Status found",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping("{statusid}")
	public ResponseEntity<StatusResource> getStatus(@PathVariable("statusid") Integer statusId) {
		log.info("Get Status by Id: "+statusId);
		final Optional<Status> statusOptional = statusService.get(statusId);
		Status status = null;
		if (statusOptional.isPresent()) {
			status = statusOptional.get();
		}
		if (status == null) {
			log.info(NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		StatusResource statusResource = new StatusResource();
		BeanUtils.copyProperties(status, statusResource);
		statusResource.setCompanyid(status.getCompany().getCompanyid());
		log.info(STATUS_FOUND);
		return new ResponseEntity<>(statusResource, HttpStatus.OK);
	}

	@Operation(summary = "This is to create Status")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Status created successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@PostMapping(consumes = "application/json")
	public ResponseEntity<StatusResource> createStatus(@RequestBody StatusResource statusResource) {
		log.info("Status creation request");
		String actionComment = GotracratUtility.getCategoryByModuleType(statusResource.getModuleType(), 0) + "Status: "
				+ statusResource.getStatus() + " Added by " + statusResource.getLastmodifiedby();
		UserLog userLog = getUserLog(statusResource.getCompanyid(), statusResource.getLastmodifiedby(),
				ModulesEnum.STATUS.getModule(), actionComment);
		final StatusResource created = statusService.createStatus(statusResource, userLog);
		if (created == null) {
			log.info(CONFLICT);
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		log.info("Status Created");
		return new ResponseEntity<>(created, HttpStatus.OK);
	}

	@Operation(summary = "This is to update Status by Status ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Status updated successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@PutMapping(value = "{statusid}", consumes = "application/json")
	public ResponseEntity<Void> updateStatus(@PathVariable("statusid") Integer statusId,
			@RequestBody StatusResource statusResource) {
		log.info("Status update request by Id: "+statusId);
		statusResource.setStatusid(statusId);
		String actionComment = null;
		if (statusResource.getOldStatus() != null
				&& !statusResource.getOldStatus().equalsIgnoreCase(statusResource.getStatus())) {
			actionComment = GotracratUtility.getCategoryByModuleType(statusResource.getModuleType(), 0)
					+ "Status Updated by " + statusResource.getLastmodifiedby() + "- Status Was: "
					+ statusResource.getOldStatus() + " Status is: " + statusResource.getStatus();
		} else {
			actionComment = GotracratUtility.getCategoryByModuleType(statusResource.getModuleType(), 0) + "Status: "
					+ statusResource.getStatus() + " Updated by " + statusResource.getLastmodifiedby();
		}
		UserLog userLog = getUserLog(statusResource.getCompanyid(), statusResource.getLastmodifiedby(),
				ModulesEnum.STATUS.getModule(), actionComment);
		if (statusService.updateStatus(statusResource, userLog)) {
			log.info("Status updated succesfully");
			return new ResponseEntity<>(HttpStatus.OK);
		} 
		log.info(NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "This is to delete Status")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Status deleted successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@DeleteMapping("{statusid}/{username}")
	public ResponseEntity<Void> deleteStatus(@PathVariable("statusid") Integer statusId,
			@PathVariable("username") String userName) {
		log.info("Status deletion request by statusId: "+statusId);
		Optional<Status> statusOptional = statusRepository.findById(statusId);
		Status status = null;
		if (statusOptional.isPresent()) {
			status = statusOptional.get();
		}
		UserLog userLog = null;
		String actionComment = GotracratUtility.getCategoryByModuleType(null, status.getEntitytypeid()) + "Status: "
				+ status.getStatus() + " Deleted by " + userName;
		userLog = getUserLog(status.getCompany().getCompanyid(), userName, ModulesEnum.STATUS.getModule(),
				actionComment);
		if (statusService.delete(status, userLog)) {
			log.info("Status succesfully deleted");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		log.info(NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	private UserLog getUserLog(Integer companyId, String userName, String userAction, String actionComment) {
		return GotracratUtility.getUserLog(userName, companyId, userAction, actionComment);
	}
}
