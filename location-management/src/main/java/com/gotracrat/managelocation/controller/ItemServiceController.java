package com.gotracrat.managelocation.controller;

import com.gotracrat.managelocation.dto.ItemServicesDTO;
import com.gotracrat.managelocation.resource.ItemServiceResource;
import com.gotracrat.managelocation.service.ItemServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "api/v1/itemService")
@Slf4j
public class ItemServiceController {

    @Autowired
    ItemServiceService itemServiceService;

    @Operation(summary = "This is to fetch Item Service by Service ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Item Service found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @GetMapping("{serviceId}")
    public ResponseEntity<ItemServiceResource> getItemService(@Parameter(description = "Id of the service", required = true)
                                                              @PathVariable("serviceId") Integer serviceId) {
        log.info("Get ItemService by Id: " + serviceId);
        return ResponseEntity.ok(itemServiceService.getItemService(serviceId));
    }

    @Operation(summary = "This is to fetch all Services of an Item by Item ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Services for an Item",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @GetMapping(value = "/services/{itemId}")
    public ResponseEntity<ItemServicesDTO> getServices(@Parameter(description = "Id of the Item", required = true)
                                                       @PathVariable("itemId") Integer itemId) {
        log.info("Get ItemServices by Id: " + itemId);
        return ResponseEntity.ok(itemServiceService.getServices(itemId));
    }

    @Operation(summary = "This is to create an Item Service")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Item Service created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<ItemServiceResource> create(@Parameter(description = "ItemServiceResource is use to create item service", required = true)
                                                      @RequestBody ItemServiceResource itemServiceResource) {
        log.info("Item Service Creation Request");
        return ResponseEntity.ok(itemServiceService.create(itemServiceResource));
    }

    @Operation(summary = "This is to update an Item Service by Service ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Item Service updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PutMapping(value = "{serviceId}")
    public ResponseEntity<Void> update(@Parameter(description = "id of the service", required = true)
                                       @PathVariable("serviceId") Integer serviceId,
                                       @Parameter(description = "ItemServiceResource is use to update item service", required = true)
                                       @RequestBody ItemServiceResource itemServiceResource) {
        log.info("Item Service Update Request: " + serviceId);
        itemServiceResource.setServiceId(serviceId);
        itemServiceService.update(itemServiceResource);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Operation(summary = "This is to delete an Item Service by Service ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Item Service deleted successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @DeleteMapping("{serviceId}")
    public ResponseEntity<String> delete(@Parameter(description = "id of the service", required = true)
                                       @PathVariable("serviceId") Integer serviceId) {
        log.info("Item Service deletion Request: " + serviceId);

        return ResponseEntity.ok(itemServiceService.delete(serviceId));
    }

}
