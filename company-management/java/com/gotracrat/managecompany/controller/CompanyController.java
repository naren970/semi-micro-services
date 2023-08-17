package com.gotracrat.managecompany.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gotracrat.managecompany.resource.resource.AnnouncementResource;
import com.gotracrat.managecompany.resource.resource.CompanyDTO;
import com.gotracrat.managecompany.resource.resource.CompanyResource;
import com.gotracrat.managecompany.resource.resource.ManualResource;
import com.gotracrat.managecompany.entity.CompaniesView;
import com.gotracrat.managecompany.exception.ResourceNotFoundException;
import com.gotracrat.managecompany.service.AnnouncementService;
import com.gotracrat.managecompany.service.CompanyService;
import com.gotracrat.managecompany.service.ManualServiceImpl;

/*
 * Rest controller
 * since:2018-05-25
 * @author prabhakar
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "/api/v1/company")
public class CompanyController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	private static final String NOT_FOUND = "Company not found";
	
	private static final String INVALID_COMPANY_ID = "Invalid Company Id :";

	@Autowired
	private CompanyService companyService;

	@Autowired
	private AnnouncementService announcementService;

	@Autowired
	private ManualServiceImpl manualService;

	@GetMapping("getAllCompanies")
	public ResponseEntity<List<CompaniesView>> getAllCompanies() {
		logger.info("Company getAllCompanies");
		List<CompaniesView> companies = companyService.getAllCompanies();
		logger.info("Company found : {}", companies);
		return new ResponseEntity<>(companies, HttpStatus.OK);
	}

	@GetMapping("getCompanies")
	public ResponseEntity<List<CompanyDTO>> getCompanies() {
		logger.info("Company getCompanies");
		List<CompanyDTO> companies = companyService.getCompanies();
		logger.info("Company found : {}", companies);
		return new ResponseEntity<>(companies, HttpStatus.OK);
	}

	@GetMapping("{companyId}")
	public ResponseEntity<CompanyResource> get(@PathVariable("companyId") Integer companyId) throws IOException {
		logger.info("Company get with id {}", companyId);
		CompanyResource companyResource = companyService.get(companyId);
		logger.info("Company resource : {}", companyResource);
		return new ResponseEntity<>(companyResource, HttpStatus.OK);
	}

	@PostMapping(value = "/add", consumes = "application/json")
	public ResponseEntity<CompanyResource> create(@Valid @RequestBody Optional<CompanyResource> companyResource)
			throws IOException {
		logger.info("Company creation request : {}", companyResource);
		CompanyResource createdCompany = companyService.create(companyResource);
		return new ResponseEntity<>(createdCompany, HttpStatus.OK);
	}

	@PutMapping(value = "{companyId}", consumes = "application/json")
	public ResponseEntity<Void> update(@PathVariable("companyId") Integer companyId,
			@Valid @RequestBody Optional<CompanyResource> companyResource) throws IOException, SQLException {
		logger.info("Company update request id={} | company={}}", companyId, companyResource);
		companyResource.get().setCompanyid(companyId);
		Boolean updatedCompany = companyService.update(companyResource);
		if (Boolean.TRUE.equals(updatedCompany)) {
			logger.info("Company succesfully updated");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		logger.info(NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "/tracratAnnouncments", consumes = "application/json")
	public ResponseEntity<Void> updateTracratAnnouncments(@RequestBody AnnouncementResource announcementResource) {
		logger.info("tracratAnnouncments update request");
		boolean isUpdated = announcementService.update(announcementResource);
		if (isUpdated) {
			logger.info("tracratAnnouncments updated succesfully ");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		logger.info(NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("{companyId}")
	public ResponseEntity<String> delete(@PathVariable("companyId") Integer companyId) {
		logger.info("Company deletion request : {}", companyId);
		return ResponseEntity.ok(companyService.delete(companyId));
	}

	@PostMapping(value = "/companyImage/{companyId}", consumes = "multipart/form-data")
	public ResponseEntity<CompanyResource> create(@RequestParam("file") MultipartFile file,
			@PathVariable("companyId") Integer companyId) throws IOException {
		logger.info("Company image save request:");
		try {
			if (file.getOriginalFilename() == null) {
				logger.info(NOT_FOUND);
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			final CompanyResource created = companyService.saveLogo(file, companyId);
			if (created == null) {
				logger.info(NOT_FOUND);
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			logger.info("Company Image saved{}", created);
			return new ResponseEntity<>(created, HttpStatus.OK);
		} catch (BeansException exception) {
			logger.error("error while saving an image", exception);
			return new ResponseEntity<>((CompanyResource) null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/showCompanyImage/{companyID}")
	public ResponseEntity<Resource> downloadFile(@PathVariable int companyID, HttpServletRequest request) {
		Resource resource = companyService.loadFileAsResource(companyID);
		if (resource == null) {
			logger.info(NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException exception) {
			logger.error("Error while getting company Image", exception);
		}
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@GetMapping("getManual")
	public ResponseEntity<ManualResource> getManual() {
		final ManualResource manualResource = manualService.getManual();
		logger.info("Manual found : {}", manualResource);
		return new ResponseEntity<>(manualResource, HttpStatus.OK);
	}

	@PutMapping(value = "/updateManual", consumes = "application/json")
	public ResponseEntity<Void> updateManual(@RequestBody ManualResource manualResource) throws IOException {
		logger.info(" Upload New Manual");
	         manualService.save(manualResource);
			logger.info("Manual succesfully updated");
			return new ResponseEntity<>(HttpStatus.OK);
		}
}
