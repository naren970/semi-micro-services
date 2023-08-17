package com.gotracrat.managelocation.controller;

import com.gotracrat.managelocation.entity.FailureType;
import com.gotracrat.managelocation.entity.UserLog;
import com.gotracrat.managelocation.service.FailureTypeService;
import com.gotracrat.managelocation.utils.GoTracratConstants;
import com.gotracrat.managelocation.utils.GotracratUtility;
import com.gotracrat.managelocation.utils.ModulesEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Rest controller
 *
 * @author ram
 * @since 2021-08-12
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "/api/v1/failureType")
@Slf4j
public class FailureTypeController {

    @Autowired
    private FailureTypeService failureTypeService;

    @Operation(summary = "This is to fetch Failure Type and Causes")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Failure Type and Causes found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @GetMapping("{companyId}/{typeId}")
    public ResponseEntity<Map<String, List<String>>> getFailureTypeAndCauses(@Parameter(description = "Id of the company", required = true)
                                                                             @PathVariable("companyId") Integer companyId,
                                                                             @Parameter(description = "Id of the Type", required = true)
                                                                             @PathVariable("typeId") Integer typeId) {
        log.info("FailureType get by typeId: " + typeId);
        return ResponseEntity.ok(failureTypeService.getFailureTypeAndCause(companyId, typeId));
    }

    @Operation(summary = "This is to create Failure Type")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Failure Type created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(consumes = "application/json")
    public ResponseEntity<FailureType> createFailureType(@Parameter(description = "The FailureType is used to create FailureType", required = true)
                                                         @RequestBody FailureType failureType) {
        log.info("FailureType creation request");
        return ResponseEntity.ok(failureTypeService.createFailureType(failureType));
    }

    @Operation(summary = "This is to update Failure Type by failureType ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Failure Type updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PutMapping(value = "{failureTypeId}", consumes = "application/json")
    public ResponseEntity<Void> updateFailureType(@Parameter(description = "Id of the failureType", required = true)
                                                    @PathVariable("failureTypeId") Integer failureTypeId,
                                                    @Parameter(description = "The FailureType is used to update FailureType", required = true)
                                                    @RequestBody FailureType failureType) {
        log.info("failureType update request by Id: " + failureTypeId);
        failureTypeService.updateFailureType(failureType);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "This is to delete Failure Type")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Failure Type deleted successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @DeleteMapping("{failureTypeId}/{companyId}/{userName}")
    public ResponseEntity<String> deleteFailureType(@Parameter(description = "Id of the failureType", required = true)
                                                  @PathVariable("failureTypeId") Integer failureTypeId,
                                                  @Parameter(description = "Id of the company", required = true)
                                                  @PathVariable("companyId") Integer companyId,
                                                  @Parameter(description = "Name of the User", required = true)
                                                  @PathVariable("userName") String userName) {
        log.info("FailureType deletion request by: " + failureTypeId);
        String actionComment = ModulesEnum.FAILURETYPE.getModule()
                .concat(GoTracratConstants.DELETED_BY.concat(userName));
        UserLog userLog = GotracratUtility.getUserLog(userName, companyId, ModulesEnum.FAILURETYPE.getModule(), actionComment);
        log.info("FailureType deleted successfully");
        return ResponseEntity.ok(failureTypeService.deleteFailureType(failureTypeId, userLog));
    }
}
