package com.gotracrat.managelocation.controller;

import com.gotracrat.managelocation.entity.TransferLog;
import com.gotracrat.managelocation.resource.TransferLogResource;
import com.gotracrat.managelocation.service.TransferLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge=100000L) 
@RequestMapping(value = "api/v1/transferLog")
@Slf4j
public class TransferLogController {
	
	@Autowired
	private TransferLogService transferLogService;

	@Operation(summary = "This is to fetch all Transfers by Item ID")
	@io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Transfers found",
					content = {@Content(mediaType = "application/json")}),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
					description = "Transfers not found",
					content = @Content)
	})
	@GetMapping("getAllTransfers/{itemId}")
	public ResponseEntity<List<TransferLog>> getAllTransfers(@PathVariable("itemId") Integer itemId )  {
		log.info("Get Transfers by itemId: "+itemId);	
			return ResponseEntity.ok(transferLogService.getAllTransfers(itemId));
	}

	@Operation(summary = "This is to fetch Transfer by TransferLog ID")
	@io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Transfer found",
					content = {@Content(mediaType = "application/json")}),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
					description = "Transfer not found",
					content = @Content)
	})
	@GetMapping("getTransfer/{transferLogId}")
	public ResponseEntity<TransferLogResource> getTransfer(@PathVariable("transferLogId") Integer transferLogId )  {
		log.info("Get TransferLog by Id: "+transferLogId);
		return ResponseEntity.ok(transferLogService.getTransfer(transferLogId));
	}

	@Operation(summary = "This is to create Transfer")
	@io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Transfer created successfully",
					content = {@Content(mediaType = "application/json")}),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@PostMapping(consumes = "application/json")
	public ResponseEntity<TransferLogResource> createTransfer(@RequestBody TransferLogResource transferLogResource) {
		log.info("Item Transfer request: "+transferLogResource.getItemID());
		return  ResponseEntity.ok(transferLogService.createTransfer(transferLogResource));
	}

	@Operation(summary = "This is to Delete TransferLog by TransferLog ID")
	@io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Transfer Item found",
					content = {@Content(mediaType = "application/json")}),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
					description = "Transfer Item not found",
					content = @Content)
	})
	@DeleteMapping("{transferLogId}")
	public ResponseEntity<String> deleteTransfer(@PathVariable("transferLogId") Integer transferLogId )  {
		log.info("Delete TransferLog by Id: "+transferLogId);
		return ResponseEntity.ok(transferLogService.delete(transferLogId));
	}
}