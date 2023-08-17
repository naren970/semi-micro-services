package com.gotracrat.managelocation.controller;

import com.gotracrat.managelocation.dto.AllRepairsDTO;
import com.gotracrat.managelocation.dto.FailureCauseDTO;
import com.gotracrat.managelocation.resource.DashboardResource;
import com.gotracrat.managelocation.resource.RecentRepairResource;
import com.gotracrat.managelocation.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Rest controller
 *
 * @author prabhakar
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "/api/v1/dashboard")
@Slf4j
public class DashBoardController {

    @Autowired
    DashboardService dashboardService;

    @Operation(summary = "This is to fetch Recent Dashboard Data")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Recent Dashboard Data found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @GetMapping("/getRecentData/{companyId}/{isOwnerAdmin}/{userId}")
    public ResponseEntity<DashboardResource> getRecentDashboardData(@Parameter(description = "Id of the company", required = true)
                                                 @PathVariable("companyId") Integer companyId,
                                                 @Parameter(description = "Indicates isOwnerAdmin", required = true)
                                                 @PathVariable("isOwnerAdmin") String isOwnerAdmin,
                                                 @Parameter(description = "Id of the user", required = true)
                                                 @PathVariable("userId") String userId) {
        log.info("Dashboard Get RecentData by companyId and userId: " + companyId + "," + userId);
        return ResponseEntity.ok(dashboardService.getRecentDashboardData(companyId, isOwnerAdmin, userId));
    }

    @Operation(summary = "This is to fetch All Repairs in Dashboard")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Dashboard All Repairs found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping("/getAllRepairs")
    public ResponseEntity<List<RecentRepairResource>> getAllRepairsInDashboard(@Parameter(description = "The AllRepairsDTO is to retrieve all repairs", required = true)
                                                                               @RequestBody AllRepairsDTO allRepairsDTO) {
        log.info("Dashboard Get AllRepairs");
        return ResponseEntity.ok(dashboardService.getAllRepairsInDashboard(allRepairsDTO));
    }

    @Operation(summary = "This is to fetch Dashboard Results")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Dashboard Results found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(path = "/primaryFindingsChart", produces = "application/json")
    public ResponseEntity<Map<String, BigDecimal>> getDashboardResults(@Parameter(description = "The FailureCauseDTO is to retrieve get the primary findings chart", required = true)
                                                                           @RequestBody FailureCauseDTO failureCauseDTO) {
        log.info("Dashboard Get primaryFindings pieChart data: " + failureCauseDTO.getCompanyId());
        return ResponseEntity.ok(dashboardService.getDashboardResultsByTimeFrame(failureCauseDTO));
    }

    @Operation(summary = "This is to fetch Failure Causes and Repair Costs")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Failure Causes and Repair Costs found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(path = "/causesChart", produces = "application/json")
    public ResponseEntity<Map<String, BigDecimal>> getFailureCausesAndRepairCosts(@Parameter(description = "The FailureCauseDTO is to retrieve failure causes and repair costs", required = true)
                                                                                  @RequestBody FailureCauseDTO failureCauseDTO) {
        log.info("dashboard Get causes pieChart data: " + failureCauseDTO.getCompanyId());
        return ResponseEntity.ok(dashboardService.getFailureCausesAndRepairCosts(failureCauseDTO));
    }

    @Operation(summary = "This is to fetch Dashboard Results in certain Range")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Dashboard Results found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(path = "primaryFindingsChartInRange", produces = "application/json")
    public ResponseEntity<Map<String, BigDecimal>> getDashboardResultsInRange(@Parameter(description = "The FailureCauseDTO is to retrieve primary findings chart by range", required = true)
                                                                                  @RequestBody FailureCauseDTO failureCauseDTO) {
        log.info("Dashboard Get primaryFindingsChartInRange: " + failureCauseDTO.getCompanyId());
        return ResponseEntity.ok(dashboardService.getDashboardResultsByRange(failureCauseDTO));
    }

    @Operation(summary = "This is to fetch Failure Causes in certain Range")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Failure Causes found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(path = "/causesChartInRange", produces = "application/json")
    public ResponseEntity<Map<String, BigDecimal>> getFailureCausesInRange(@Parameter(description = "The FailureCauseDTO is to retrieve failure causes by range", required = true)
                                                                           @RequestBody FailureCauseDTO failureCauseDTO) {
        log.info("Dashboard Get causesChartInRange: " + failureCauseDTO.getCompanyId());
        return ResponseEntity.ok(dashboardService.getFailureCausesInRange(failureCauseDTO));
    }

    @Operation(summary = "This is to fetch Repair Jobs by Failure Cause")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Repair Jobs by Failure Cause found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(path = "repairJobsByFailureCause", consumes = "application/json")
    public ResponseEntity<List<RecentRepairResource>> getRepairJobsByFailureCause(@Parameter(description = "The FailureCauseDTO is to retrieve Repair jobs by failure causes", required = true)
                                                                                  @RequestBody FailureCauseDTO failureCauseDTO) {
        log.info("Dashboard Get repairJobsByFailureCause: " + failureCauseDTO.getCompanyId());
        return ResponseEntity.ok(dashboardService.getRepairJobsByFailureCause(failureCauseDTO));
    }

    @Operation(summary = "This is to fetch Repair Jobs by Failure Cause in certain Range")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Repair Jobs by Failure Cause found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(path = "repairJobsByFailureCauseInRange", consumes = "application/json")
    public ResponseEntity<List<RecentRepairResource>> getRepairJobsByFailureCauseInRange(@Parameter(description = "The FailureCauseDTO is to retrieve Repair jobs by failure causes and range", required = true)
                                                                                         @RequestBody FailureCauseDTO failureCauseDTO) {
        log.info("Dashboard Get repairJobsByFailureCauseInRange: " + failureCauseDTO.getCompanyId());
        return ResponseEntity.ok(dashboardService.getRepairJobsByFailureCauseInRange(failureCauseDTO));
    }
}
