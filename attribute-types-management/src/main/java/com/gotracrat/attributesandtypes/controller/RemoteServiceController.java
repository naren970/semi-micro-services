package com.gotracrat.attributesandtypes.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gotracrat.attributesandtypes.controller.resource.AttributeValueResource;
import com.gotracrat.attributesandtypes.service.AttributenameService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge=100000L) 
@RequestMapping(value = "/api/remote")
@Slf4j
public class RemoteServiceController {
	
	@Autowired
	private AttributenameService attributenameService;

	@Operation(summary = "This is to fetch all Attributes by Entity ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Attributes Found",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping("/getAllAttributesByEntityId/{entityId}/{entityTypeId}")
	public ResponseEntity<List<AttributeValueResource>> getAllAttributesByEntityId(
			@PathVariable("entityId") Integer entityId,@PathVariable("entityTypeId") Integer entityTypeId) {
		log.info("Attributename get with id {}");
		final List<AttributeValueResource> attributenameResources = attributenameService
				.getAllAttributesByEntityId(entityId,entityTypeId);
		log.info("Attributename found : {}", attributenameResources);
		return new ResponseEntity<>(attributenameResources, HttpStatus.OK);
	}
	
}
