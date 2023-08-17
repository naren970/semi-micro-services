/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:57 )
 * 
 */
package com.gotracrat.managecompany.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gotracrat.managecompany.resource.CompanyResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
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

import com.gotracrat.managecompany.entity.Company;
import com.gotracrat.managecompany.entity.UserLog;
import com.gotracrat.managecompany.service.CompanyServiceImpl;
import com.gotracrat.managecompany.util.GoTracratContants;
import com.gotracrat.managecompany.util.GotracratUtility;

/**
 * Rest controller
 *
 * @author prabhakar
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge=100000L) 
@RequestMapping(value = "/api/vendor")
public class VendorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(VendorController.class);

	private static final String NOT_FOUND = "Company not found";

	@Autowired
	private CompanyServiceImpl companyService;

	@Autowired
	private PagedResourcesAssembler<Company> pageAssember;

	// private CompanyResourceAssembler companyResourceAssembler = new
	// CompanyResourceAssembler();

	/*
	 * @GetMapping public ResponseEntity<PagedResources<CompanyResource>>
	 * search(Pageable pageable, CompanyCriteria criteria) {
	 * LOGGER.info("Companys search [pageable={}|CompanyCriteria={}", pageable,
	 * criteria); final Page<Company> result = companyService.search(pageable,
	 * criteria); LOGGER.info("Found {} companys !", result.getSize()); return new
	 * ResponseEntity<>(pageAssember.toResource(result, companyResourceAssembler),
	 * HttpStatus.OK); }
	 */

	/*
	 * @GetMapping(value = "/getAllCompanies") public ResponseEntity<List<Company>>
	 * getAllCompanies() { LOGGER.info("Company get with id {}"); final
	 * List<Company> company = companyService.getAllCompanies(); if (company ==
	 * null) { LOGGER.info(NOT_FOUND); //return new
	 * ResponseEntity<>((CompanyResource) null, HttpStatus.NOT_FOUND); }
	 * LOGGER.info("Company found : {}", company); //final CompanyResource
	 * companyResource = companyResourceAssembler.toResource(company); return new
	 * ResponseEntity<>(company, HttpStatus.OK); }
	 */

	@Operation(summary = "This is to fetch all Companies for User by Company ID", description = "Get All Companies for User")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Companies Found Successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Company not found",
					content = @Content)
	})
	@GetMapping("getAllCompaniesForUser/{companyid}")
	public ResponseEntity<List<CompanyResource>> getAllCompanies(@PathVariable("companyid") Integer companyid)
			throws IOException {
		LOGGER.info("Vendors getAllCompanies");
		CompanyResource companyResource = companyService.get(companyid);
		List<CompanyResource> vendorsList = new ArrayList<>();
		List<CompanyResource> companiesList = companyService.getAllVendors(GoTracratContants.VENDOR_FLAG, companyid);
		vendorsList.add(companyResource);
		if(companiesList!=null)
		vendorsList.addAll(companiesList);
		LOGGER.info("Vendors found : {}", vendorsList);
		return new ResponseEntity<>(vendorsList, HttpStatus.OK);
	}

	@Operation(summary = "This is to fetch all Vendors by Company ID", description = "Get All Vendors")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Vendors found successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Vendors not found",
					content = @Content)
	})
	@GetMapping("getAllVendors/{companyid}")
	public ResponseEntity<List<CompanyResource>> getAllVendors(@PathVariable("companyid") Integer companyid)
			throws IOException {
		LOGGER.info("Vendors getAllCompanies");
		List<CompanyResource> companiesList = companyService.getAllVendors(GoTracratContants.VENDOR_FLAG, companyid);
		LOGGER.info("Vendors found : {}", companiesList);
		return new ResponseEntity<>(companiesList, HttpStatus.OK);
	}

	@Operation(summary = "This is to fetch a Vendor by Company ID", description = "Get Vendor for the Company")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Vendor found for the Company Successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Vendor not found",
					content = @Content)
	})
	@GetMapping("{companyid}")
	public ResponseEntity<CompanyResource> getVendor(@PathVariable("companyid") Integer companyid) throws IOException {
		LOGGER.info("Vendors get with id {}", resolveIdForLogger(companyid));
		CompanyResource companyResource = companyService.get(companyid);
		if (companyResource == null) {
			LOGGER.info(NOT_FOUND);
			return new ResponseEntity<CompanyResource>(companyResource, HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Vendors resource : {}", companyResource);
		return new ResponseEntity<CompanyResource>(companyResource, HttpStatus.OK);
	}

	@Operation(summary = "This is add Vendors for a Company", description = "Add Vendors for a Company")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Vendor added successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@PostMapping(value = "/addVendor", consumes = "application/json")
	public ResponseEntity<CompanyResource> createVendor(@RequestBody CompanyResource company) throws IOException {
		LOGGER.info("Vendors creation request : {}", company);
		CompanyResource createdCompany = companyService.create(company);
		return new ResponseEntity<CompanyResource>(createdCompany, HttpStatus.OK);
	}

	@Operation(summary = "This is to update Vendor by Company ID", description = "Update Vendor details")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Vendor details updated successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Vendor not found",
					content = @Content)
	})
	@PutMapping(value = "{companyid}", consumes = "application/json")
	public ResponseEntity<Void> updateVendor(@PathVariable("companyid") Integer companyid,
			@RequestBody CompanyResource company) throws IOException, SQLException {
		LOGGER.info("Vendors update request [id={} | company={}}", resolveIdForLogger(companyid), company);
		// force the id (use the id provided by the URL)
		company.setCompanyid(companyid);
		if (companyService.update(company)) {
			LOGGER.info("Vendors succesfully updated");
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			LOGGER.info(NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "This is to delete Vendors for a Company", description = "Delete Vendors")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Vendors deleted successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Vendor not found",
					content = @Content)
	})
	@DeleteMapping("{companyid}")
	public ResponseEntity<String> deleteVendor(@PathVariable("companyid") Integer companyid) throws IOException {
		LOGGER.info("Vendors deletion request : {}", resolveIdForLogger(companyid));
		return ResponseEntity.ok(companyService.delete(companyid));
	}

	private String resolveIdForLogger(Integer companyid) {
		final StringBuilder ids = new StringBuilder();
		ids.append(String.valueOf(companyid));
		return ids.toString();
	}

	private UserLog getUserLog(Integer companyId,String userName,String userAction,String actionComment) {
		UserLog userLog = GotracratUtility.getUserLog(userName, companyId, userAction, actionComment);
		return userLog;
	}
}
