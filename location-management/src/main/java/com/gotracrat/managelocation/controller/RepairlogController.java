
package com.gotracrat.managelocation.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import com.gotracrat.managelocation.service.RepairLogService;
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
import com.gotracrat.managelocation.resource.RepairLogResource;
import com.gotracrat.managelocation.entity.UserLog;
import com.gotracrat.managelocation.utils.GoTracratConstants;
import com.gotracrat.managelocation.utils.GotracratUtility;
import com.gotracrat.managelocation.utils.ModulesEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * Rest controller
 *
 * @author Prabhakar
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "/api/v1/itemrepair")
@Slf4j
public class RepairlogController {

    private static final String NOT_FOUND = "Repairlog not found";

    @Autowired
    private RepairLogService repairlogService;

    @Operation(summary = "This is to fetch all Completed Item Repairs")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "All Completed Item Repairs found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Completed Item Repairs not found",
                    content = @Content)
    })
    @GetMapping("getAllCompletedItemRepairs/{companyid}/{itemId}")
    public ResponseEntity<List<RepairLogResource>> getAllCompletedItemRepairs(
            @PathVariable("companyid") Integer companyId, @PathVariable("itemId") Integer itemId) {
        log.info("Get Completed ItemRepairs by itemId: " + itemId);
        final List<RepairLogResource> repairlog = repairlogService.getAllItemRepairs(companyId, itemId, "Completed");
        log.info("Completed ItemRepairs found");
        return new ResponseEntity<List<RepairLogResource>>(repairlog, HttpStatus.OK);
    }

    @Operation(summary = "This is to fetch all Incomplete Item Repairs")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Incomplete Item Repairs found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Incomplete Item Repairs not found",
                    content = @Content)
    })
    @GetMapping("getAllInCompletedItemRepairs/{companyid}/{typeid}")
    public ResponseEntity<List<RepairLogResource>> getAllInCompletedItemRepairs(
            @PathVariable("companyid") Integer companyId, @PathVariable("typeid") Integer typeId) {
        log.info("Get InCompleted ItemRepairs by itemId: " + typeId);
        final List<RepairLogResource> repairlog = repairlogService.getAllItemRepairs(companyId, typeId, "InCompleted");
        log.info("Incompleted ItemRepairs found");
        return new ResponseEntity<List<RepairLogResource>>(repairlog, HttpStatus.OK);
    }

    @Operation(summary = "This is to fetch Item Repair by RepairLog ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Item Repair found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Item Repair not found",
                    content = @Content)
    })
    @GetMapping("{repairlogid}")
    public ResponseEntity<RepairLogResource> getItemRepair(@PathVariable("repairlogid") Integer repairlogId) {
        log.info("Get Repairlog by Id: " + repairlogId);
        final RepairLogResource repairlog = repairlogService.getItemRepair(repairlogId);
        if (repairlog == null) {
            log.info(NOT_FOUND);
            return new ResponseEntity<RepairLogResource>((RepairLogResource) null, HttpStatus.NOT_FOUND);
        }
        log.info("Repairlog found");
        return new ResponseEntity<RepairLogResource>(repairlog, HttpStatus.OK);
    }

    @Operation(summary = "This is fetch Item Repair for View by RepairLog ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Item Repair for View found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Item Repair for View not found",
                    content = @Content)
    })
    @GetMapping("getForView/{repairlogid}")
    public ResponseEntity<RepairLogResource> getItemRepairForView(@PathVariable("repairlogid") Integer repairlogId) {
        log.info("Get Repairlog for view by Id: " + repairlogId);
        final RepairLogResource repairlog = repairlogService.getItemRepairForView(repairlogId);
        if (repairlog == null) {
            log.info(NOT_FOUND);
            return new ResponseEntity<RepairLogResource>((RepairLogResource) null, HttpStatus.NOT_FOUND);
        }
        log.info("Repairlog found");
        return new ResponseEntity<RepairLogResource>(repairlog, HttpStatus.OK);
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
    public ResponseEntity<RepairLogResource> createItemRepair(@RequestBody RepairLogResource repairlogResource) {
        log.info("Repairlog creation request");
        String actionComment = "Item Repair " + repairlogResource.getItemtype() + ":" + repairlogResource.getTag()
                + " PO:" + repairlogResource.getPonumber() + " JOB:" + repairlogResource.getJobnumber() + " "
                + GoTracratConstants.CREATED_BY + " " + repairlogResource.getUserName();
        UserLog userLog = getUserLog(repairlogResource.getCompanyId(), repairlogResource.getUserName(),
                ModulesEnum.REPAIRLOG.getModule(), actionComment);
        final RepairLogResource created = repairlogService.createItemRepair(repairlogResource, userLog);
        log.info("Repairlog Created Successfully");
        return new ResponseEntity<RepairLogResource>(created, HttpStatus.OK);

    }

    @Operation(summary = "This is to update Item Repair by RepairLog ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Item Repair updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Item Repair not found",
                    content = @Content)
    })
    @PutMapping(value = "{repairlogid}", consumes = "application/json")
    public ResponseEntity<RepairLogResource> updateItemRepair(@PathVariable("repairlogid") Integer repairlogId,
                                                              @RequestBody RepairLogResource repairlogResource) throws ParseException {
        log.info("Repairlog update request by Id: " + repairlogId);
        String actionComment = "Item Repair " + repairlogResource.getItemtype() + ":" + repairlogResource.getTag()
                + " PO:" + repairlogResource.getPonumber() + " JOB:" + repairlogResource.getJobnumber() + " "
                + GoTracratConstants.UPDATED_BY + " " + repairlogResource.getUserName();
        UserLog userLog = getUserLog(repairlogResource.getCompanyId(), repairlogResource.getUserName(),
                ModulesEnum.REPAIRLOG.getModule(), actionComment);
        repairlogResource.setRepairlogid(repairlogId);
        if (repairlogService.saveItemRepair(repairlogResource, userLog)) {
            log.info("Repairlog updated succesfully");
            return new ResponseEntity<RepairLogResource>(repairlogResource, HttpStatus.OK);
        }
        log.info(NOT_FOUND);
        return new ResponseEntity<RepairLogResource>(repairlogResource, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "This is to fetch FailureType and Causes for Item Repair")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Failure Type and Causes found for Item Repair",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Failure Type and Causes not found",
                    content = @Content)
    })
    @GetMapping("failureTypeAndCauseForItemRepair/{companyid}/{typeid}")
    public ResponseEntity<Map<String, List<String>>> getFailureTypeAndCausesForItemRepair(
            @PathVariable("companyid") Integer companyId, @PathVariable("typeid") Integer typeId) {
        log.info("Repairlog get FailureTypeAndCauses by typeId: " + typeId);
        final Map<String, List<String>> failureTypeAndCause = repairlogService
                .getFailureTypeAndCauseForItemRepair(companyId, typeId);
        log.info("FailureTypeAndCauses found");
        return new ResponseEntity<Map<String, List<String>>>(failureTypeAndCause, HttpStatus.OK);
    }


    @Operation(summary = "This is to delete Item Repair by RepairLog ID and Company ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Item Repair deleted successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Item Repair not found",
                    content = @Content)
    })
    @DeleteMapping("{repairlogid}/{companyid}/{username}/{itemtype}/{tag}/{ponumber}/{jobnumber}")
    public ResponseEntity<String> deleteItemRepair(@PathVariable("repairlogid") Integer repairlogId,
                                                   @PathVariable("companyid") Integer companyid, @PathVariable("username") String username,
                                                   @PathVariable("itemtype") String itemtype, @PathVariable("tag") String tag,
                                                   @PathVariable("ponumber") String ponumber, @PathVariable("jobnumber") String jobnumber) {
        log.info("Repairlog deletion request by Id: " + repairlogId);
        String actionComment = "Item Repair " + itemtype + ":" + tag + " PO:" + ponumber + " JOB:" + jobnumber + " "
                + GoTracratConstants.DELETED_BY + " " + username;
        UserLog userLog = getUserLog(companyid, username, ModulesEnum.REPAIRLOG.getModule(), actionComment);
        return ResponseEntity.ok(repairlogService.deleteItemRepair(repairlogId, userLog));

    }

    private UserLog getUserLog(Integer companyId, String userName, String userAction, String actionComment) {
        UserLog userLog = GotracratUtility.getUserLog(userName, companyId, userAction, actionComment);
        return userLog;
    }
}
