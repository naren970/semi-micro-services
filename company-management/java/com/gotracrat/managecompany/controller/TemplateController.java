package com.gotracrat.managecompany.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.gotracrat.managecompany.resource.resource.TemplateDto;
import com.gotracrat.managecompany.resource.resource.TemplateResource;
import com.gotracrat.managecompany.resource.resource.TemplateWithOutXml;
import com.gotracrat.managecompany.entity.Company;
import com.gotracrat.managecompany.entity.Template;
import com.gotracrat.managecompany.entity.UserLog;
import com.gotracrat.managecompany.exception.ResourceNotFoundException;
import com.gotracrat.managecompany.service.TemplateServiceImpl;
import com.gotracrat.managecompany.util.GoTracratContants;
import com.gotracrat.managecompany.util.GotracratUtility;
import com.gotracrat.managecompany.util.ModulesEnum;

/**
 * Rest controller
 *
 * @author manikanta
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "/api/v1/template")
public class TemplateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateController.class);

    private static final String NOT_FOUND = "Template not found";

    private static final String INVALID_TEMPLATE_ID = "Invalid Template Id :";

    @Autowired
    private TemplateServiceImpl templateService;

    @GetMapping("getAllTemplatesByCompanyId/{companyid}")
    public ResponseEntity<List<TemplateResource>> getAllTemplateByCompanyId(
            @PathVariable("companyId") Integer companyId) throws IOException {
        LOGGER.info("Template getAllTemplateByCompanyId");
        List<TemplateResource> templateResourceList = templateService.getAllTemplateByCompanyId(companyId);
        if (templateResourceList == null) {
            LOGGER.info(NOT_FOUND);
            return new ResponseEntity<>(templateResourceList, HttpStatus.NOT_FOUND);
        }
        LOGGER.info("Template found : {}", templateResourceList);
        return new ResponseEntity<>(templateResourceList, HttpStatus.OK);
    }

    @GetMapping("getAllTemplates/{companyId}")
    public ResponseEntity<List<TemplateWithOutXml>> getAllTemplates(@PathVariable("companyId") Integer companyId)
            throws IOException {
        LOGGER.info("Template getAllTemplateByCompanyId withoutXml");
        List<TemplateWithOutXml> templateResourceList = templateService.getAllTemplateWithOutXml(companyId);
        if (templateResourceList == null) {
            LOGGER.info(NOT_FOUND);
            return new ResponseEntity<>(templateResourceList, HttpStatus.NOT_FOUND);
        }
        LOGGER.info("Template found : {}", templateResourceList);
        return new ResponseEntity<>(templateResourceList, HttpStatus.OK);
    }

    @PostMapping(value = "/createCompanyFromTemplate", consumes = "application/json")
    public ResponseEntity<Company> createCompanyFromTemplate(@RequestBody TemplateDto templateDto)
            throws IOException, SQLException {
        LOGGER.info("Company creation request from Template");
        Company company = templateService.createCompanyFromTemplate(templateDto);
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }

    @PostMapping(value = "/createTemplate", consumes = "application/json")
    public ResponseEntity<TemplateResource> createTemplate(@RequestBody TemplateDto templateDto)
            throws IOException, SQLException {
        LOGGER.info("Template creation request");
        String actionComment = ModulesEnum.TEMPLATE.getModule() + " " + templateDto.getTemplateName() + " "
                + GoTracratContants.CREATED_BY + " " + templateDto.getUserName();
        UserLog userLog = getUserLog(templateDto.getCompanyId(), templateDto.getUserName(),
                ModulesEnum.TEMPLATE.getModule(), actionComment);
        TemplateResource createdTemplateResource = templateService.createTemplate(templateDto, userLog);
        return new ResponseEntity<TemplateResource>(createdTemplateResource, HttpStatus.OK);
    }

    @DeleteMapping("{templateId}/{companyid}/{userName}/{templateName}")
    public ResponseEntity<String> delete(@PathVariable("templateId") Integer templateId,
                                           @PathVariable("companyid") Integer companyid, @PathVariable("userName") String userName,
                                           @PathVariable("templateName") String templateName) {
        LOGGER.info("Template deletion request ");
        String actionComment = ModulesEnum.TEMPLATE.getModule() + ":" + templateName + " "
                + GoTracratContants.DELETED_BY + " " + userName;
        UserLog userLog = getUserLog(companyid, userName, ModulesEnum.TEMPLATE.getModule(), actionComment);
        return ResponseEntity.ok(templateService.delete(templateId, userLog));
    }

    private UserLog getUserLog(Integer companyId, String userName, String userAction, String actionComment) {
        UserLog userLog = GotracratUtility.getUserLog(userName, companyId, userAction, actionComment);
        return userLog;
    }

}
