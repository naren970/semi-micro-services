package com.gotracrat.attributesandtypes.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gotracrat.attributesandtypes.controller.resource.TypeDTO;
import com.gotracrat.attributesandtypes.controller.resource.TypeResource;
import com.gotracrat.attributesandtypes.entity.Type;
import com.gotracrat.attributesandtypes.entity.UserLog;
import com.gotracrat.attributesandtypes.repository.TypeRepository;
import com.gotracrat.attributesandtypes.service.TypeService;
import com.gotracrat.attributesandtypes.utils.GotracratUtility;
import com.gotracrat.attributesandtypes.utils.ModulesEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * Rest controller
 *
 * @author prabhakar
 * @since  2018-06-18
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "/api/type")
@Slf4j
public class TypeController {

	private static final String NOT_FOUND = "Type not found";

	private static final String TYPE_FOUND = "Type found";

	@Autowired
	private TypeService typeService;

	@Autowired
	private TypeRepository typeRepository;

	/**
	 *
	 * @param companyId  companyId of the required type
	 * @param moduleType used to differentiate between different entities
	 * @return returns list of type resources
	 */
	@Operation(summary = "This is to fetch all Types by Company ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Types found",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping("/getAllType/{moduleType}/{companyId}")
	public ResponseEntity<List<TypeDTO>> getAllType(@PathVariable("companyId") Integer companyId,
			@PathVariable("moduleType") String moduleType) {
		log.info("Get "+moduleType+" Types by companyId "+companyId);
		final List<TypeDTO> types = typeService.getAllTypes(companyId, moduleType);
		log.info(moduleType+" Types Found");
		return new ResponseEntity<>(types, HttpStatus.OK);
	}

	/**
	 *
	 * @param companyId  companyId of the required type
	 * @param moduleType used to differentiate between different entities
	 * @return returns list of type resources with Hierarchy
	 */
	@Operation(summary = "This is to fetch all Types with Hierarchy by Company ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Type with Hierarchy found",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping("/getAllTypeWithHierarchy/{moduleType}/{companyId}")
	public ResponseEntity<List<TypeResource>> getAllTypeWithHierarchy(@PathVariable("companyId") Integer companyId,
			@PathVariable("moduleType") String moduleType) {
		log.info("Get "+moduleType+" typeWithHierarchy by companyId: "+companyId);
		final List<TypeResource> typeList = typeService.getAllTypeWithHierarchy(companyId, moduleType);
		log.info("Type With Hierarchy Found");
		return new ResponseEntity<>(typeList, HttpStatus.OK);
	}

	/**
	 *
	 * @param typeId
	 * @return Type Resource
	 */
	@Operation(summary = "This is to fetch Type by Type ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Type found",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping("{typeId}")
	public ResponseEntity<TypeResource> getType(@PathVariable("typeId") Integer typeId) {
		log.info("Get Type by Id: "+typeId);
		final TypeResource type = typeService.get(typeId);
		if (type == null) {
			log.info(NOT_FOUND);
			return new ResponseEntity<>((TypeResource) null, HttpStatus.NOT_FOUND);
		}
		log.info(TYPE_FOUND);
		return new ResponseEntity<>(type, HttpStatus.OK);
	}

	/**
	 *
	 * @param type
	 * @return
	 */
	@Operation(summary = "This is to create Type")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Type created successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@PostMapping(consumes = "application/json")
	public ResponseEntity<Type> createType(@RequestBody TypeResource type) {
		log.info("Type creation request");
		String category = GotracratUtility.getCategoryByModuleType(type.getModuleType(), 0);
		String actionComment = category + "Type Added with Name:" + type.getName() + " By " + type.getLastmodifiedby();
		UserLog userLog = getUserLog(type.getCompany().getCompanyid(), type.getLastmodifiedby(),
				ModulesEnum.TYPE.getModule(), actionComment);
		final Type created = typeService.create(type, userLog);
		if (created == null) {
			log.info(NOT_FOUND);
			return new ResponseEntity<>((Type) null, HttpStatus.CONFLICT);
		}
		log.info("Type Created");
		return new ResponseEntity<>(created, HttpStatus.OK);
	}

	@Operation(summary = "This is to update Type by Type ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Type updated successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@PutMapping(value = "{typeId}", consumes = "application/json")
	public ResponseEntity<Void> updateType(@PathVariable("typeId") Integer typeId, @RequestBody TypeResource type) {
		log.info("Type update request by Id: "+typeId);
		type.setTypeid(typeId);
		String actionComment = GotracratUtility.getCategoryByModuleType(type.getModuleType(), 0) + "Type: "
				+ type.getName() + " Updated By " + type.getLastmodifiedby();
		UserLog userLog = GotracratUtility.getUserLog(type.getLastmodifiedby(), type.getCompany().getCompanyid(),
				ModulesEnum.TYPE.getModule(), actionComment);
		if (typeService.save(type, userLog)) {
			log.info("Type updated succesfully");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		log.info(NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "This is to delete Type")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Type deleted successfully",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@DeleteMapping("{typeid}/{userName}")
	public ResponseEntity<Void> deleteType(@PathVariable("typeid") Integer typeId,
			@PathVariable("userName") String userName) {
		log.info("Type deletion request by Id: "+typeId);
		Type type = typeRepository.findById(typeId).get();
		UserLog userLog = null;
		if (type != null) {
			String actionComment = GotracratUtility.getCategoryByModuleType(null, type.getEntitytypeid()) + "Type: "
					+ type.getName() + " Deleted By " + userName;
			userLog = GotracratUtility.getUserLog(userName, type.getCompany().getCompanyid(),
					ModulesEnum.TYPE.getModule(), actionComment);
		}
		if (typeService.delete(type, userLog)) {
			log.info("Type deleted succesfully");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		log.info(NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "This is to fetch Attributes for searchDisplay by Company ID")
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
					description = "Attributes for searchDisplay found",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Page not found",
					content = @Content)
	})
	@GetMapping(path = "/getattributesforsearchdisplay/{companyid}", produces = "application/json")
	public ResponseEntity<Map<String, Integer>> getAttributesForSearchDisplay(
			@PathVariable("companyid") Integer companyId) {
		log.info("Get attributes for searchdisplay by companyId: "+companyId);
		Map<String, Integer> attributesMap = typeService.getAttributesForSearchDisplay(companyId);
		log.info("Attributes found for searchdisplay");
		return new ResponseEntity<>(attributesMap, HttpStatus.OK);
	}

	private UserLog getUserLog(Integer companyId, String userName, String userAction, String actionComment) {
		return GotracratUtility.getUserLog(userName, companyId, userAction, actionComment);

	}
}
