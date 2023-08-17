package com.gotracrat.managecompany.controller;

import com.gotracrat.managecompany.entity.CompaniesView;
import com.gotracrat.managecompany.exception.ResourceNotFoundException;
import com.gotracrat.managecompany.resource.*;
import com.gotracrat.managecompany.service.AnnouncementService;
import com.gotracrat.managecompany.service.CompanyService;
import com.gotracrat.managecompany.service.ManualServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/*
 * Rest controller
 * @since 2018-05-25
 * @author prabhakar
 */
@Log4j2
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "/api/company")
public class CompanyController {

    private static final String NOT_FOUND = "Company not found";

    private static final String INVALID_COMPANY_ID = "Invalid Company Id :";

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private ManualServiceImpl manualService;

    /*
     * Get all companies for dropdown in dashboard
     *  used for owner admin
     * @return ResponseEntity<List<CompaniesView>> returns list of companies
     */
    @Operation(summary = "This is to fetch all the companies in TracRat", description = "Get all Companies")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Companies found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Companies not found",
                    content = @Content)
    })
    @GetMapping("getAllCompanies")
    public ResponseEntity<List<CompaniesView>> getAllCompanies() {
        log.info("Company getAllCompanies");
        return ResponseEntity.ok(companyService.getAllCompanies());
    }


    /*
     * Get all companies by userId for dropdown in dashboard
     *  used for other users than owner admin
     * @return ResponseEntity<List<CompaniesView>> returns list of companies
     */
    @Operation(summary = "This is to fetch all the Companies by User ID", description = "Get all Companies by User")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Companies by User found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Companies/User not found",
                    content = @Content)
    })
    @GetMapping("/getCompaniesByUser/{userId}")
    public ResponseEntity<List<CompaniesView>> getCompaniesByUser(@PathVariable("userId") String userId) {
        log.info("Get companies by user: " + userId);
        return ResponseEntity.ok(companyService.getCompaniesByUser(userId));
    }

    /*
     * Get companies
     *
     * @return ResponseEntity<List<CompanyDTO>> returns list of companies
     */
    @Operation(summary = "This is to fetch all the Companies in TracRat", description = "Get Companies")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "List of all the Companies",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Companies not found",
                    content = @Content)
    })
    @GetMapping("getCompanies")
    public ResponseEntity<List<CompanyDTO>> getCompanies() {
        log.info("Company getCompanies");
        return ResponseEntity.ok(companyService.getCompanies());
    }

    /*
     * Get company by company Id
     *
     * @return ResponseEntity<CompanyResource> returns company resource based on
     * company id
     */
    @Operation(summary = "This is to fetch Company by Company ID", description = "Get Company by Company ID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Company found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Company not found",
                    content = @Content)
    })
    @GetMapping("{companyId}")
    public ResponseEntity<CompanyResource> get(@PathVariable("companyId") Integer companyId) {
        log.info("Company get by Id: " + companyId);
        return ResponseEntity.ok(companyService.get(companyId));
    }

    @Operation(summary = "This is to fetch Company Logo by Company ID", description = "Get Company Logo by Company ID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Company Logo found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Company Logo not found",
                    content = @Content)
    })
    @GetMapping(value = "/logo/{companyId}")
    public ResponseEntity<CompanyLogoDTO> getLogo(@PathVariable("companyId") Integer companyId) {
        log.info("Company get by Id: " + companyId);
        return ResponseEntity.ok(companyService.getLogo(companyId));
    }


    /*
     * create company
     *
     * @RequestBody ComapnyResource is used to create company in database
     *
     * @return created company resource and status ok
     */
    @Operation(summary = "This is to create a Company", description = "Create a Company")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Company has been created",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<CompanyResource> create(@Valid @RequestBody CompanyResource companyResource) {
        log.info("Company creation request");
        return ResponseEntity.ok( companyService.create(companyResource));
    }

    /*
     * Update company
     *
     * @PathVariable used for update company
     *
     * @RequestBody ComapnyResource is used to update company in database
     *
     * @return if company successfully updated returns status ok otherwise throw
     * ResourceNotFoundException
     */
    @Operation(summary = "This is to update Company by Company ID", description = "Update a Company")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Company has been updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Company not found",
                    content = @Content)
    })
    @PutMapping(value = "{companyId}", consumes = "application/json")
    public ResponseEntity<Void> update(@PathVariable("companyId") Integer companyId,
                                       @Valid @RequestBody CompanyResource companyResource) {
        log.info("Company update request by Id: " + companyId);
        companyResource.setCompanyid(companyId);
        if (companyService.update(companyResource)) {
            log.info("Company updated successfully");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        log.info(NOT_FOUND);
        throw new ResourceNotFoundException(INVALID_COMPANY_ID + companyId);
    }

    /*
     * Delete company by company id
     *
     * @return Status NO_CONTENT if deleted else it returns throw
     * ResourceNotFoundException
     */
    @Operation(summary = "This is to delete a Company by Company ID", description = "Delete a Company")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Company has been deleted successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Company not found",
                    content = @Content)
    })
    @DeleteMapping("{companyId}")
    public ResponseEntity<String> delete(@PathVariable("companyId") Integer companyId) {
        log.info("Company deletion request : {}", companyId);
        return ResponseEntity.ok(companyService.delete(companyId));
    }

    /*
     * Update announcments
     *
     * @RequestBody AnnouncementResource used for update announcement
     *
     * @return if announcement update successfully returns status ok otherwise
     * NOT_FOUND
     */
    @Operation(summary = "This is to update TracRat Announcements", description = "Update TracRat Announcements")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "TracRat Announcements has been updated",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Announcements not found",
                    content = @Content)
    })
    @PutMapping(value = "/tracratAnnouncments", consumes = "application/json")
    public ResponseEntity<Void> updateTracratAnnouncements(@RequestBody AnnouncementResource announcementResource) {
        log.info("TracratAnnouncements update request");
        announcementService.update(announcementResource);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
     * Add image for company
     *
     * @return
     */
    @Operation(summary = "This is to create Company Image by Company ID", description = "Create a Company Image")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Company Image has been created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Company not found",
                    content = @Content)
    })
    @PostMapping(value = "/companyImage/{companyId}", consumes = "multipart/form-data")
    public ResponseEntity<CompanyResource> create(@RequestParam("file") MultipartFile file,
                                                  @PathVariable("companyId") Integer companyId) {
        log.info("Company image save request by companyId: " + companyId);
        try {
            if (file.getOriginalFilename() == null) {
                log.info(NOT_FOUND);
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            companyService.saveLogo(file, companyId);
            log.info("Company Image saved successfully");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BeansException exception) {
            log.error("error while saving an image", exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Show image based on company id
     *
     * @return
     */
    @Operation(summary = "This is to fetch Company Image by Company ID", description = "Get Company Image")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Company Image found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Company Image not found",
                    content = @Content)
    })
    @GetMapping("/showCompanyImage/{companyID}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int companyID, HttpServletRequest request) {
        Resource resource = companyService.loadFileAsResource(companyID);
        log.info("Download Company Image by companyId: " + companyID);
        if (resource == null) {
            log.info(NOT_FOUND);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException exception) {
            log.error("Error while getting company Image", exception);
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    /*
     * Get Manual
     *
     * @return status ok if manual found
     */
    @Operation(summary = "This is to fetch Manual from TracRat", description = "Get Manual")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Manual found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Manual not found",
                    content = @Content)
    })
    @GetMapping("getManual")
    public ResponseEntity<ManualResource> getManual() {
        log.info("Get manual");
        return ResponseEntity.ok(manualService.getManual());
    }

    /*
     * Update manual
     *
     * @RequestBody ManualResource used for update manual
     *
     * @return status ok if manual updated
     */
    @Operation(summary = "This is to update Manual from TracRat", description = "Update Manual")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Manual has been updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Manual not found",
                    content = @Content)
    })
    @PutMapping(value = "/updateManual", consumes = "application/json")
    public ResponseEntity<Void> updateManual(@RequestBody ManualResource manualResource) {
        log.info("Manual update request");
        manualService.save(manualResource);
        log.info("Manual updated successfully");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}