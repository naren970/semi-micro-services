package com.gotracrat.managecompany.controller;

import java.util.List;

import com.gotracrat.managecompany.service.TemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gotracrat.managecompany.resource.TemplateDto;
import com.gotracrat.managecompany.resource.TemplateResource;
import com.gotracrat.managecompany.resource.TemplateWithOutXml;
import com.gotracrat.managecompany.entity.Company;
import com.gotracrat.managecompany.entity.UserLog;
import com.gotracrat.managecompany.exception.ResourceNotFoundException;
import com.gotracrat.managecompany.service.TemplateServiceImpl;
import com.gotracrat.managecompany.util.GoTracratContants;
import com.gotracrat.managecompany.util.GotracratUtility;
import com.gotracrat.managecompany.util.ModulesEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * Rest controller
 *
 * @author manikanta
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "/api/v1/template")
@Slf4j
public class TemplateController {

    private static final String NOT_FOUND = "Template not found";

    private static final String INVALID_TEMPLATE_ID = "Invalid Template Id :";

    @Autowired
    private TemplateService templateService;


    @Operation(summary = "This is to fetch all Templates by Company ID", description = "Get All Templates")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Templates found",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Templates not found",
                    content = @Content)
    })
    @GetMapping("getAllTemplates/{companyId}")
    public ResponseEntity<List<TemplateWithOutXml>> getAllTemplates(@PathVariable("companyId") Integer companyId) {
        log.info("Get Templates withoutXml By companyId: " + companyId);
        return ResponseEntity.ok(templateService.getAllTemplateWithOutXml(companyId));
    }

    @Operation(summary = "This is to create Company from Template", description = "Create Company from Template")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Company has been created from Template",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(value = "/createCompanyFromTemplate", consumes = "application/json")
    public ResponseEntity<Company> createCompanyFromTemplate(@RequestBody TemplateDto templateDto) {
        log.info("Company creation request from Template: " + templateDto.getTemplateId());
        Company company = templateService.createCompanyFromTemplate(templateDto);
        log.info("Company created from Template");
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }

    @Operation(summary = "This is to create a Template", description = "Create Template")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Template Created Successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(value = "/createTemplate", consumes = "application/json")
    public ResponseEntity<TemplateResource> createTemplate(@RequestBody TemplateDto templateDto) {
        log.info("Template creation request");
        String actionComment = ModulesEnum.TEMPLATE.getModule() + " " + templateDto.getTemplateName() + " "
                + GoTracratContants.CREATED_BY + " " + templateDto.getUserName();
        UserLog userLog = GotracratUtility.getUserLog(templateDto.getUserName(), templateDto.getCompanyId(), ModulesEnum.TEMPLATE.getModule(), actionComment);
        TemplateResource createdTemplateResource = templateService.createTemplate(templateDto, userLog);
        log.info("Template created successfully");
        return new ResponseEntity<TemplateResource>(createdTemplateResource, HttpStatus.OK);
    }

    @Operation(summary = "This is to delete a Template by Company ID", description = "Delete Template")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Template deleted Successfully",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @DeleteMapping("{templateId}/{companyid}/{userName}/{templateName}")
    public ResponseEntity<String> delete(@PathVariable("templateId") Integer templateId,
                                         @PathVariable("companyid") Integer companyid, @PathVariable("userName") String userName,
                                         @PathVariable("templateName") String templateName) {
        log.info("Template deletion request ");
        String actionComment = ModulesEnum.TEMPLATE.getModule() + ":" + templateName + " "
                + GoTracratContants.DELETED_BY + " " + userName;
        UserLog userLog = GotracratUtility.getUserLog(userName, companyid,ModulesEnum.TEMPLATE.getModule(), actionComment);
        return ResponseEntity.ok(templateService.delete(templateId, userLog));
    }
}
