package com.gotracrat.attributesandtypes.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.gotracrat.attributesandtypes.controller.resource.AllAttachmentDTO;
import com.gotracrat.attributesandtypes.controller.resource.AttachmentAddDTO;
import com.gotracrat.attributesandtypes.controller.resource.AttachmentResource;
import com.gotracrat.attributesandtypes.controller.resource.AttachmentUserLogDTO;
import com.gotracrat.attributesandtypes.entity.Attachment;
import com.gotracrat.attributesandtypes.entity.UserLog;
import com.gotracrat.attributesandtypes.service.AttachmentService;
import com.gotracrat.attributesandtypes.utils.GotracratUtility;
import com.gotracrat.attributesandtypes.utils.ModulesEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * Rest controller
 *
 * @author prabhakar
 * @since 2018-05-25
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "/api/attachment")
@Slf4j
public class AttachmentController {

	private static final String NOT_FOUND = "Attachment not found";

	@Autowired
	private AttachmentService attachmentService;

	@Operation(summary = "This is to fetch all Attachments by Company ID and Entity ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Attachments found successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping("getAllAttachments/{moduleType}/{companyID}/{entityId}")
	public ResponseEntity<List<AttachmentResource>> getAllAttachments(@PathVariable("companyID") Integer companyId,
			@PathVariable("moduleType") String moduleType, @PathVariable("entityId") int entityId) throws IOException {
		log.info(moduleType + "Attachments get by companyId and entityId: " + companyId + "," + entityId);
		return ResponseEntity.ok(attachmentService.getAllAttachments(companyId, moduleType, entityId));
	}

	/**
	 * Get All Attachments with out actual attachment byte data,used to improve
	 * performance
	 * 
	 * @param moduleType
	 * @param entityId
	 * @return
	 */
	@Operation(summary = "This is to fetch all Attachments by Entity ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Attachments found successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping("getAllAttachments/{moduleType}/{entityId}")
	public ResponseEntity<List<AllAttachmentDTO>> getAllAttachments(@PathVariable("moduleType") String moduleType,
			@PathVariable("entityId") int entityId) {
		log.info(moduleType + "Attachments get by entityId: " + entityId);
		return ResponseEntity.ok(attachmentService.getAllAttachments(moduleType, entityId));

	}

	/**
	 * 
	 * @param attachmentId
	 * @return
	 */
	@Operation(summary = "This is to fetch Attachment by Attachment ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Attachment found successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping("{attachmentId}")
	public ResponseEntity<AttachmentResource> get(@PathVariable("attachmentId") Integer attachmentId) {
		log.info("Attachment get by attachmentId: " + attachmentId);
		Optional<Attachment> attachmentOptional = attachmentService.get(attachmentId);
		Attachment attachment = null;
		if (attachmentOptional.isPresent()) {
			attachment = attachmentOptional.get();
		}
		if (attachment == null) {
			log.info(NOT_FOUND);
			return new ResponseEntity<>((AttachmentResource) null, HttpStatus.NOT_FOUND);
		}
		log.info("Attachment found");
		AttachmentResource attachmentResource = new AttachmentResource();
		BeanUtils.copyProperties(attachment, attachmentResource);
		String attachmetString = "";
		try {
			attachmetString = GotracratUtility.decompress(attachment.getAttachment());
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		attachmentResource.setAttachmentFile(attachmetString);
		return new ResponseEntity<>(attachmentResource, HttpStatus.OK);
	}

	/**
	 * 
	 * @param attachmentAddDTO
	 * @return
	 * @throws IOException
	 */

	@Operation(summary = "This is to create multiple Attachments")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Attachments created successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@PostMapping(value = "/createMultipleAttachments", consumes = "application/json")
	public ResponseEntity<List<AttachmentResource>> createMultipleAttachments(
			@RequestBody AttachmentAddDTO attachmentAddDTO) throws IOException {
		log.info("Attachments creation request");
		try {
			if (attachmentAddDTO == null) {
				log.info(NOT_FOUND);
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			final List<AttachmentResource> created = attachmentService.createMultipleAttachments(attachmentAddDTO);
			if (created == null) {
				log.info(NOT_FOUND);
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			log.info("Attachment Created successfully");
			return new ResponseEntity<>(created, HttpStatus.OK);
		} catch (BeansException e) {
			e.printStackTrace();
			return new ResponseEntity<>((List<AttachmentResource>) null, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	/**
	 * 
	 * @param attachmentid
	 * @param attachmentResource
	 * @return
	 * @throws IOException
	 */
	@Operation(summary = "This is to update existing Attachment by Attachment ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Attachment updated successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@PutMapping(value = "{attachmentId}", consumes = "application/json")
	public ResponseEntity<Void> update(@PathVariable("attachmentId") Integer attachmentId,
			@RequestBody AttachmentResource attachmentResource) throws IOException {
		log.info("Attachment update request by attachmentId: "+attachmentId);
		attachmentResource.setAttachmentid(attachmentId);
		if (attachmentService.save(attachmentResource, new UserLog())) {
			log.info("Attachment succesfully updated");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		log.info(NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "This is to delete Attachment by Attachment ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Attachment deleted successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@DeleteMapping("{attachmentId}/{companyId}/{userName}")
	public ResponseEntity<String> delete(@PathVariable("attachmentId") Integer attachmentId,
										 @PathVariable("companyId") Integer companyId, @PathVariable("userName") String userName,
										 @RequestParam(name = "noteType", required = false) String noteType,
										 @RequestParam(name = "itemTypeName", required = false) String itemTypeName,
										 @RequestParam(name = "itemTag", required = false) String itemTag,
										 @RequestParam(name = "noteName", required = false) String noteName,
										 @RequestParam(name = "locationName", required = false) String locationName,
										 @RequestParam(name = "poNumber", required = false) String poNumber,
										 @RequestParam(name = "jobNumber", required = false) String jobNumber) {
		log.info("Attachment deletion request by attachmentId: "+attachmentId);
		AttachmentUserLogDTO attachmentUserLogDTO = new AttachmentUserLogDTO();
		UserLog userLog = getUserLog(companyId, userName, ModulesEnum.ATTACHMENT.getModule(), "");
		attachmentUserLogDTO.setNoteType(noteType);
		attachmentUserLogDTO.setItemTypeName(itemTypeName);
		attachmentUserLogDTO.setItemTag(itemTag);
		attachmentUserLogDTO.setNoteName(noteName);
		attachmentUserLogDTO.setLocationName(locationName);
		attachmentUserLogDTO.setPoNumber(poNumber);
		attachmentUserLogDTO.setJobNumber(jobNumber);
		return ResponseEntity.ok(attachmentService.delete(attachmentId, userLog, attachmentUserLogDTO));
	}

	@Operation(summary = "This is to download Attachment by Attachment ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Attachment downloaded successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping("/downloadaudiofile/{attachmentID}")
	public ResponseEntity<Resource> downloadFile(@PathVariable int attachmentID, HttpServletRequest request) {
		log.info("Download attachment by Id: "+attachmentID);
		Resource resource = attachmentService.loadFileAsResource(attachmentID);
		if (resource == null) {
			log.info(NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
		}
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	private UserLog getUserLog(Integer companyId, String userName, String userAction, String actionComment) {
		return GotracratUtility.getUserLog(userName, companyId, userAction, actionComment);
	}
}