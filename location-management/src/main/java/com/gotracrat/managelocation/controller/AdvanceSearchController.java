package com.gotracrat.managelocation.controller;

import com.gotracrat.managelocation.dto.ItemAdsRequestDTO;
import com.gotracrat.managelocation.resource.AdvanceSearchResource;
import com.gotracrat.managelocation.resource.AdvSearchPieChartResource;
import com.gotracrat.managelocation.resource.ItemAdsSearchResource;
import com.gotracrat.managelocation.resource.RecentRepairResource;
import com.gotracrat.managelocation.service.AdvanceSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Rest controller for Item Advance search
 * @author prabhakar
 * @since 2018-05-25
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "/api/v1/advanceSearch")
@Slf4j
public class AdvanceSearchController {

    private static final String BAD_REQUEST = "Note parameter has conflict";

    @Autowired
    private AdvanceSearchService advanceSearchService;

    @Operation(summary = "This is to fetch AdvanceSearch RepairLogNote by User")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "AdvanceSearch RepairLogNote found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping("/repairLogNote")
    public ResponseEntity<AdvanceSearchResource> getAdvanceSearchRepairLogNoteByUser(@Parameter(description = "The AdvanceSearchResource is to get repairLogNote", required = true)
                                                                                     @RequestBody AdvanceSearchResource advanceSearchResource) {
        log.info("AdvanceSearch Get repairLogNote by user");
        if (advanceSearchResource.getCompanyID() == 0) {
            log.info(BAD_REQUEST);
            return new ResponseEntity<AdvanceSearchResource>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(advanceSearchService.getAdvanceSearchRepairLogNoteByUser(advanceSearchResource));
    }

    /**
     * Get Items based on given search criteria(with attributes also) and role of the user
     * @param isOwnerAdmin -To check if the user is owner admin
     * @param userId -Id       of the user who is searching
     */
    @Operation(summary = "This is to fetch Items based on AdvanceSearch criteria")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Items found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Map<String, List<ItemAdsSearchResource>>> advancedSearch(@Parameter(description = "The ItemAdsRequestDTO is to get Items based on search criteria", required = true)
                                                                                   @RequestBody ItemAdsRequestDTO itemAdsRequestDTO) {
        log.info("Advanced item search in company: " + itemAdsRequestDTO.getCompanyId());
        return ResponseEntity.ok(advanceSearchService.itemAdvancedSearch(itemAdsRequestDTO));
    }

    @Operation(summary = "This is to display advancedSearch PieCharts Data")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Failure Types PieChart data found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping("/failureTypesChart")
    public ResponseEntity<Map<String, BigDecimal>> advancedSearchPieChartsData(@Parameter(description = "The AdvSearchPieChartResource is used to retrieve get the Failure Types chart", required = true)
                                                                                   @RequestBody AdvSearchPieChartResource advSearchPieChartResource) {
        log.info("Advance Search::Get Failure Types PieChart data: " + advSearchPieChartResource.getCompanyId());
        return ResponseEntity.ok(advanceSearchService.getAdvancedSearchPieChartsData(advSearchPieChartResource));
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
    @PostMapping(path = "/failureCausesChart", produces = "application/json")
    public ResponseEntity<Map<String, BigDecimal>> getFailureCausesAndRepairCosts(@Parameter(description = "The AdvSearchPieChartResource is used to retrieve failure causes and repair costs", required = true)
                                                                                  @RequestBody AdvSearchPieChartResource advSearchPieChartResource) {
        log.info("Advance Search::Get Causes PieChart data: " + advSearchPieChartResource.getCompanyId());
        return ResponseEntity.ok(advanceSearchService.getFailureCausesAndRepairCosts(advSearchPieChartResource));
    }


    @Operation(summary = "This is to fetch Repair Jobs by Failure Cause")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Repair jobs by Failure Cause found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(path = "/repairJobsByFailureCause", consumes = "application/json")
    public ResponseEntity<List<RecentRepairResource>> getRepairJobsByFailureCause(@Parameter(description = "The AdvSearchPieChartResource is used to retrieve Repair jobs by failure causes", required = true)
                                                                                  @RequestBody AdvSearchPieChartResource advSearchPieChartResource) {
        log.info("Advance Search::Get RepairJobs By FailureCause: " + advSearchPieChartResource.getCompanyId());
        return ResponseEntity.ok(advanceSearchService.getRepairJobsByFailureCause(advSearchPieChartResource));
    }


}
