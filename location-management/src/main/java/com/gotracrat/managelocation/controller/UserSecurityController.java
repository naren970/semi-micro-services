package com.gotracrat.managelocation.controller;

import com.gotracrat.managelocation.dto.UserLevelsDTO;
import com.gotracrat.managelocation.dto.UserRolesDTO;
import com.gotracrat.managelocation.entity.UserLog;
import com.gotracrat.managelocation.entity.VwUserSecurity;
import com.gotracrat.managelocation.resource.UserSecurityResource;
import com.gotracrat.managelocation.service.UserSecurityService;
import com.gotracrat.managelocation.utils.GoTracratConstants;
import com.gotracrat.managelocation.utils.GotracratUtility;
import com.gotracrat.managelocation.utils.ModulesEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Rest controller
 *
 * @author prabhakar
 * @since 2018-10-27
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "api/v1/userSecurity")
@Slf4j
public class UserSecurityController {

    private static final String NOT_FOUND = "userSecurity not found";

    @Autowired
    private UserSecurityService userSecurityService;

    @Operation(summary = "This is to fetch User Security by User ID, Company ID and Location ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "User Security found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "User Security not found",
                    content = @Content)
    })
    @GetMapping("/getUserSecurity/{userId}/{companyId}/{locationId}")
    public ResponseEntity<UserSecurityResource> getUserSecurity(@PathVariable("userId") String userId,
                                                                @PathVariable("companyId") Integer companyId, @PathVariable("locationId") Integer locationId) {
        log.info("Get UserSecurity");
        return ResponseEntity.ok(userSecurityService.getUserSecurity(userId, companyId, locationId));
    }

    @Operation(summary = "This is to create/update User Security")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "User Security created/updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content)
    })
    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserSecurityResource> createOrUpdateUserSecurity(@RequestBody final UserSecurityResource userSecurityResource) {
        log.info("UserSecurity creation request");
        String actionComment = ModulesEnum.USERSECURITY.getModule()
                .concat(GoTracratConstants.CREATED_BY.concat(userSecurityResource.getLastmodifiedby()));
        UserLog userLog = GotracratUtility.getUserLog(userSecurityResource.getLastmodifiedby(), userSecurityResource.getCompanyid(),
                ModulesEnum.USERSECURITY.getModule(), actionComment);
        return ResponseEntity.ok(userSecurityService.createOrUpdateUserSecurity(userSecurityResource, userLog));
    }

    @Operation(summary = "This is to delete User Security by User ID, Company ID and Location ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "User Security deleted successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content)
    })
    @DeleteMapping("{userId}/{companyId}/{locationId}/{userName}")
    public ResponseEntity<String> delete(@PathVariable("userId") String userId, @PathVariable("companyId") Integer companyId,
                                       @PathVariable("locationId") Integer locationId, @PathVariable("userName") String userName) {
        log.info("UserSecurity deletion request");
        String actionComment = ModulesEnum.USERSECURITY.getModule() + " " + GoTracratConstants.DELETED_BY + " "
                + userName;
        UserLog userLog = GotracratUtility.getUserLog(userName, companyId, ModulesEnum.USERSECURITY.getModule(), actionComment);
        return ResponseEntity.ok(userSecurityService.delete(userId, companyId, locationId, userLog));
    }

    @Operation(summary = "This is to fetch Levels by User Name and Company ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Levels found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Levels not found",
                    content = @Content)
    })
    @GetMapping("{userName}/{companyId}")
    public ResponseEntity<List<UserLevelsDTO>> getLevelsByUserName(@PathVariable("userName") String userName,
                                                                   @PathVariable("companyId") Integer companyId)  {
        log.info("Get levels by userName and companyId" + userName + "," + companyId);
        return ResponseEntity.ok(userSecurityService.getLevels(userName, companyId));
    }

    @Operation(summary = "This is to fetch Roles for User by User ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Roles found for User",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @GetMapping("getAllRolesForUser/{userId}")
    public ResponseEntity<List<VwUserSecurity>> getRolesForUser(@PathVariable("userId") String userId) {
        log.info("Get Roles by userId: " + userId);
        return ResponseEntity.ok(userSecurityService.getAllRolesForUser(userId));
    }

    @Operation(summary = "This is to fetch all Roles for Logged in User by Name and Company ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "All Roles for Logged in User found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @GetMapping("getAllRolesForLoggedInUser/{userName}/{companyId}")
    public ResponseEntity<List<UserRolesDTO>> getAllRolesForLoggedInUser(@PathVariable("userName") String userName,
                                                                         @PathVariable("companyId") Integer companyId) {
        log.info("Get RolesForLoggedInUser userName and companyId" + userName + "," + companyId);
        return ResponseEntity.ok(userSecurityService.getRolesForALoggedInUser(userName, companyId));
    }
}
