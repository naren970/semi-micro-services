package com.gotracrat.managelocation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gotracrat.managelocation.resource.ProfileResource;
import com.gotracrat.managelocation.entity.Profile;
import com.gotracrat.managelocation.service.ProfileServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * Rest controller
 * 
 * @author Prabhakar
 * @since  2018-10-27
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "api/v1/profile")
@Slf4j
public class ProfileController {

	private static final String NOT_FOUND = "Profile not found";

	@Autowired
	private ProfileServiceImpl profileService;

	@Operation(summary = "This is to fetch Profile by Profile ID")
	@io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Profile found",
					content = {@Content(mediaType = "application/json")}),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
					description = "Profile not found",
					content = @Content)
	})
	@GetMapping("{profileid}")
	public ResponseEntity<ProfileResource> get(@PathVariable("profileid") Integer profileId) {
		log.info("Get profile by Id: "+profileId);
		final Profile profile = profileService.get(profileId);
		if (profile == null) {
			log.info(NOT_FOUND);
			return new ResponseEntity<ProfileResource>((ProfileResource) null, HttpStatus.NOT_FOUND);
		}
		log.info("Profile Found");
		final ProfileResource profileResource = new ProfileResource();
		BeanUtils.copyProperties(profile, profileResource);
		return new ResponseEntity<ProfileResource>(profileResource, HttpStatus.OK);
	}

	@Operation(summary = "This is to fetch Profile by User ID")
	@io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Profile found",
					content = {@Content(mediaType = "application/json")}),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
					description = "Profile not found",
					content = @Content)
	})
	@GetMapping("user/{userid}")
	public ResponseEntity<ProfileResource> getProfileWithUserId(@PathVariable("userid") String userId) {
		log.info("Get profile by userId: "+userId);
		final ProfileResource profile = profileService.getProfileWithUserId(userId);
		if (profile == null) {
			log.info(NOT_FOUND);
			return new ResponseEntity<ProfileResource>((ProfileResource) null, HttpStatus.NOT_FOUND);
		}
		log.info("Profile Found");
		final ProfileResource profileResource = new ProfileResource();
		BeanUtils.copyProperties(profile, profileResource);
		return new ResponseEntity<ProfileResource>(profileResource, HttpStatus.OK);
	}

	@Operation(summary = "This is to fetch Profile by User ID and Company ID")
	@io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Profile found",
					content = {@Content(mediaType = "application/json")}),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
					description = "Profile not found",
					content = @Content)
	})
	@GetMapping("user/{userid}/{companyId}")
	public ResponseEntity<ProfileResource> getProfileWithUserIdAndCompanyId(@PathVariable("userid") String userId,
			@PathVariable("companyId") String companyId) {
		log.info("Get profile by userId and companyId: "+userId+","+companyId);
		ProfileResource profile = profileService.getProfileWithUserId(userId, companyId);
		if (profile == null) {
			log.info(NOT_FOUND);
			return new ResponseEntity<ProfileResource>((ProfileResource) null, HttpStatus.NOT_FOUND);
		}
		log.info("Profile found");
		final ProfileResource profileResource = new ProfileResource();
		BeanUtils.copyProperties(profile, profileResource);
		return new ResponseEntity<ProfileResource>(profileResource, HttpStatus.OK);
	}

	@Operation(summary = "This is to update Profile by Profile ID and Company ID")
	@io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Profile updated successfully",
					content = {@Content(mediaType = "application/json")}),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
					description = "Profile not found",
					content = @Content)
	})
	@PutMapping(value = "{profileid}/{companyid}", consumes = "application/json")
	public ResponseEntity<ProfileResource> update(@PathVariable("profileid") Integer profileid,
			@RequestBody ProfileResource profile, @PathVariable("companyid") Integer companyid) {
		log.info("Profile update request: "+profileid);
		ProfileResource profileResource = profileService.save(profile, profileid, companyid);
		if (profileResource != null) {
			log.info("Profile updated successfully");
			return new ResponseEntity<ProfileResource>(profileResource, HttpStatus.OK);
		} 
		log.info(NOT_FOUND);
		return new ResponseEntity<ProfileResource>(HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "This is to save profile by Profile ID and Company ID")
	@io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Profile saved",
					content = {@Content(mediaType = "application/json")}),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
					description = "Profile not found",
					content = @Content)
	})
	@PutMapping(value = "saveProfile/{profileid}/{companyid}", consumes = "application/json")
	public ResponseEntity<ProfileResource> updateProfile(@PathVariable("profileid") Integer profileid,
			@RequestBody ProfileResource profile, @PathVariable("companyid") Integer companyid) {
		log.info("Profile update request from user security: "+profileid);
		ProfileResource profileResource = profileService.saveProfile(profile, profileid, companyid);
		if (profileResource != null) {
			log.info("Profile updated successfully");
			return new ResponseEntity<ProfileResource>(profileResource, HttpStatus.OK);
		} 
		log.info(NOT_FOUND);
		return new ResponseEntity<ProfileResource>(HttpStatus.NOT_FOUND);
	}
}
