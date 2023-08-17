package com.gotracrat.managelocation.controller;

import com.gotracrat.managelocation.dto.InserviceVsSpareResponseDTO;
import com.gotracrat.managelocation.entity.VwSpareMotor;
import com.gotracrat.managelocation.resource.InServiceVsSpareRequest;
import com.gotracrat.managelocation.resource.ItemServicesResource;
import com.gotracrat.managelocation.resource.ServiceReportRequest;
import com.gotracrat.managelocation.service.ReportsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller
 *
 * @author Manikanta
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "api/v1/reports")
@Slf4j
public class ReportsController {

    @Autowired
    private ReportsService reportsService;


    /**
     * Get Spare motor with given Id .
     *
     * @param itemId Id of spare motor
     * @return ResponseEntity<VwSpareMotor> returns the Spare motor with That ID
     */
    @Operation(summary = "This is to fetch Spare Motor by Item ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Spare Motor found successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Spare Motor not found",
                    content = @Content)
    })
    @GetMapping(path = "spare/{itemId}")
    public ResponseEntity<VwSpareMotor> getSpareMotor(@PathVariable(value = "itemId") Integer itemId) {
        log.info("Get Spare motor by Id: " + itemId);
        return ResponseEntity.ok(reportsService.getSpareMotor(itemId));
    }

    /*
     * Get service reports between given dates
     *
     * @return ResponseEntity<List<ServiceReportDTO>> returns the list of
     * Service Reports for Items
     */
    @Operation(summary = "This is to fetch Service Reports")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Service Reports found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Service Reports not found",
                    content = @Content)
    })
    @PostMapping(path = "/serviceReport", produces = "application/json")
    public ResponseEntity<ItemServicesResource> getServiceReports(
            @RequestBody ServiceReportRequest serviceReportRequest) {
        log.info("Service Report in company: " + serviceReportRequest.getCompanyId());
        return ResponseEntity.ok(reportsService.getServiceReports(serviceReportRequest));
    }

    /*
     * Get InServiceVsSpareReports
     *
     * @return ResponceEntityInserviceVsSpareResponseDTO> returns
     * InServiceVsSpareReports
     */
    @Operation(summary = "This is to fetch InService Vs Spare Reports")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "InService Vs Spare Reports found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "InService Vs Spare Reports not found",
                    content = @Content)
    })
    @PostMapping(path = "/inServiceVsSpare", produces = "application/json")
    public ResponseEntity<InserviceVsSpareResponseDTO> getInServiceVsSpareReports(
            @RequestBody InServiceVsSpareRequest inServiceVsSpareRequest) {
        log.info("InServiceVsSpare Report by companyId: " + inServiceVsSpareRequest.getCompanyId());
        return ResponseEntity.ok(reportsService.getInServiceVsSpareReports(inServiceVsSpareRequest));
    }
}
