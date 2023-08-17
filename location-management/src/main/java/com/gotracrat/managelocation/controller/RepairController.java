package com.gotracrat.managelocation.controller;

import com.gotracrat.managelocation.resource.RepairResource;
import com.gotracrat.managelocation.service.RepairService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller
 *
 * @author Prabhakar
 * @since 2018-11-30
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "/api/v1/itemRepairItem")
@Slf4j
public class RepairController {

    @Autowired
    private RepairService repairService;

    @Operation(summary = "This is to fetch All Repair Items by Company ID and Type ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Repair Items found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Repair Items not found",
                    content = @Content)
    })
    @GetMapping("getAllRepairItems/{companyId}/{typeId}")
    public ResponseEntity<List<RepairResource>> getAllRepairItems(@Parameter(description = "Id of company", required = true) @PathVariable("companyId") Integer companyId,
                                                                  @Parameter(description = "TypeId to get item repair items", required = true)
                                                                  @PathVariable("typeId") Integer typeId) {
        log.info("Get ItemRepairItems by typeId: " + typeId);
        return ResponseEntity.ok(repairService.getAllRepairItems(companyId, typeId));
    }

    @Operation(summary = "This is to fetch Item Repair by Repair ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Item Repair found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Item Repair not found",
                    content = @Content)
    })
    @GetMapping("{repairId}")
    public ResponseEntity<RepairResource> get(@Parameter(description = "repairId of Item Repair Item", required = true)
                                              @PathVariable("repairId") Integer repairId) {
        log.info("Get ItemRepairItem by Id: " + repairId);
        return ResponseEntity.ok(repairService.get(repairId));
    }

    @Operation(summary = "This is to create Item Repair")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Item Repair created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Item Repair not found",
                    content = @Content)
    })
    @PostMapping(consumes = "application/json")
    public ResponseEntity<RepairResource> create(@Parameter(description = "The RepairResource is to create Item Repair Item", required = true)
                                                 @RequestBody RepairResource repairResource) {
        log.info("ItemRepairItem creation request");
        return ResponseEntity.ok(repairService.create(repairResource));

    }

    @Operation(summary = "This is to update Item Repair by Repair ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Item Repair updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Item Repair not found",
                    content = @Content)
    })
    @PutMapping(value = "{repairId}", consumes = "application/json")
    public ResponseEntity<Void> update(@Parameter(description = "repairId of Item Repair Item", required = true)
                                       @PathVariable("repairId") Integer repairId,
                                       @Parameter(description = "The RepairResource is to update Item Repair Item", required = true)
                                       @RequestBody RepairResource repairResource) {
        log.info("ItemRepairItem update request by Id: " + repairId);
        repairService.update(repairResource);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Operation(summary = "This is to delete Item Repair by Repair ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Item Repair deleted successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Item Repair not found",
                    content = @Content)
    })
    @DeleteMapping(value="{repairId}")
    public ResponseEntity<String> delete(@Parameter(description = "repairId of Item Repair Item", required = true)
                                       @PathVariable("repairId") Integer repairId) {
        log.info("ItemRepairItem deletion request by Id: " + repairId);

        return ResponseEntity.ok(repairService.delete(repairId));
    }
}
