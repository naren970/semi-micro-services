package com.gotracrat.managelocation.controller;

import com.gotracrat.managelocation.dto.LocationMergeDTO;
import com.gotracrat.managelocation.entity.Location;
import com.gotracrat.managelocation.entity.UserLog;
import com.gotracrat.managelocation.resource.LocationResource;
import com.gotracrat.managelocation.service.LocationService;
import com.gotracrat.managelocation.utils.GotracratUtility;
import com.gotracrat.managelocation.utils.ModulesEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Rest Controller For Create,Update,Get and Delete Locations
 * @author prabhakar
 * @since 2018-07-15
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "/api/v1/location")
@Slf4j
public class LocationController {

    private static final String NOT_FOUND = "Location not found";

    @Autowired
    private LocationService locationService;

    @Operation(summary = "This is to fetch all Locations")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Locations found successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Locations not found",
                    content = @Content)
    })
    @GetMapping("/getAllLocations/{companyId}")
    public ResponseEntity<List<Location>> getAllLocations(@PathVariable("companyId") Integer companyId) {
        log.info("Get Locations by companyId: " + companyId);
        return ResponseEntity.ok(locationService.getAllLocations(companyId));
    }

    @Operation(summary = "This is to fetch all Locations with Hierarchy")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Locations found successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Locations not found",
                    content = @Content)
    })
    @GetMapping("/getAllLocationsWithHierarchy/{companyId}")
    public ResponseEntity<List<LocationResource>> getAllLocationsWithHierarchy(
            @PathVariable("companyId") Integer companyId) throws SQLException {
        log.info("Get LocationsWithHierarchy by companyId: " + companyId);
        return ResponseEntity.ok(locationService.getAllLocationsWithHierarchy(companyId));
    }

    @Operation(summary = "This is to fetch Locations with Location ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Locations found successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Locations not found",
                    content = @Content)
    })
    @GetMapping("/{locationId}")
    public ResponseEntity<LocationResource> get(@PathVariable("locationId") Integer locationId) {
        log.info("Get Location by Id: " + locationId);
        return ResponseEntity.ok(locationService.get(locationId));
    }

    @Operation(summary = "This is to create Location")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Location created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(consumes = "application/json")
    public ResponseEntity<List<LocationResource>> createLocation(@RequestBody List<LocationResource> location) {
        log.info("Location creation request");
		return ResponseEntity.ok(locationService.createLocation(location));
    }

    @Operation(summary = "This is to merge Locations by Company ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Locations merged successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(value = "/mergeLocations/{companyId}", consumes = "application/json")
    public ResponseEntity<LocationMergeDTO> mergeLocations(@RequestBody LocationMergeDTO location,
                                                           @PathVariable("companyId") Integer companyId) {
        log.info("Merge Locations request");
		return ResponseEntity.ok(locationService.mergeLocation(location, companyId));
    }

    @Operation(summary = "This is to update Location by Location ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Location updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PutMapping(value = "/{locationId}", consumes = "application/json")
    public ResponseEntity<Void> updateLocation(@PathVariable("locationId") Integer locationId,
                                               @RequestBody LocationResource locationResource) {
        log.info("Location update request  by Id: " + locationId);
        locationResource.setLocationid(locationId);
        locationService.updateLocation(locationResource);
        log.info("Location updated successfully");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Operation(summary = "This is to delete Location by Location ID and Company ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Location deleted successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @DeleteMapping("{locationid}/{companyId}/{username}")
    public ResponseEntity<String> delete(@PathVariable("locationid") Integer locationid,
                                       @PathVariable("companyId") Integer companyId, @PathVariable("username") String username) {
        log.info("Location deletion request by Id: " + locationid);
        UserLog userLog = GotracratUtility.getUserLog(username, companyId, ModulesEnum.LOCATION.getModule(), "");
        log.info("Location Deleted Successfully");
        return ResponseEntity.ok(locationService.delete(locationid, companyId, userLog));
    }

    @Operation(summary = "This is to fetch All Locations by User")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Locations found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Location not found",
                    content = @Content)
    })
    @GetMapping("/getAllLocationsByUser/{companyId}/{userid}")
    public ResponseEntity<List<LocationResource>> getAllLocationsByUser(@PathVariable("companyId") Integer companyId,
                                                                        @PathVariable("userid") String userId) throws SQLException {
        log.info("Get Locations By companyId and userId: " + companyId + "," + userId);
        final List<LocationResource> locationResource = locationService.getAllLocationsByUser(companyId, userId);
        if (locationResource == null) {
            log.info(NOT_FOUND);
            return new ResponseEntity<List<LocationResource>>((locationResource), HttpStatus.NOT_FOUND);
        }
        log.info("Locations found by user");
        return new ResponseEntity<List<LocationResource>>(locationResource, HttpStatus.OK);
    }

    @Operation(summary = "This is to fetch Clone Address from Parent Location")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Clone Address from Parent Location found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @GetMapping("/getCloneAddressFromParentLocation/{locationId}/{companyid}")
    public ResponseEntity<LocationResource> getCloneAddressFromParentLocation(
            @PathVariable("locationId") Integer locationId, @PathVariable("companyid") Integer companyId) {
        log.info("Clone Address From ParentLocation by locationId and companyId: " + locationId + "," + companyId);
        LocationResource locationResource = locationService.getCloneAddressFromParentLocation(locationId, companyId);
        if (locationResource == null) {
            log.info(NOT_FOUND);
            return new ResponseEntity<LocationResource>((LocationResource) null, HttpStatus.NOT_FOUND);
        }
        log.info("Clone Address found");
        return new ResponseEntity<LocationResource>(locationResource, HttpStatus.OK);
    }

    @GetMapping("/getParentLocationId/{name}")
    public ResponseEntity<Integer> getParentLocationId(@PathVariable("name") String name) {
        log.info("Get Locations by companyId: " + name);
        return ResponseEntity.ok(locationService.getParentLocationId(name));
    }

}
