/*
 *
 * Generated by prabhakar
 */
package com.gotracrat.managelocation.controller;

import com.gotracrat.managelocation.dto.*;
import com.gotracrat.managelocation.entity.UserLog;
import com.gotracrat.managelocation.entity.VwGetItem;
import com.gotracrat.managelocation.resource.GoFormResource;
import com.gotracrat.managelocation.resource.ItemResource;
import com.gotracrat.managelocation.resource.FailedItemsResource;
import com.gotracrat.managelocation.service.ItemService;
import com.gotracrat.managelocation.utils.GoTracratConstants;
import com.gotracrat.managelocation.utils.GotracratUtility;
import com.gotracrat.managelocation.utils.ModulesEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Rest Controller
 *
 * @author Prabhakar
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 100000L)
@RequestMapping(value = "api/v1/item")
@Slf4j
public class ItemController {

    private static final String NOT_FOUND = "Item not found";

    @Autowired
    private ItemService itemService;

    /*
     * Get Item For Tablet
     *
     * @param ItemID
     *
     * @return ItemResource
     */
    @Operation(summary = "This is to fetch an Item by Item ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Item found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Item not found",
                    content = @Content)
    })
    @GetMapping("{itemId}")
    public ResponseEntity<ItemResource> get(@PathVariable("itemId") Integer itemId) {
        log.info("Get Item by Id: " + itemId);
        final ItemResource item = itemService.getItem(itemId);
        if (item == null) {
            log.info(NOT_FOUND);
            return new ResponseEntity<ItemResource>(HttpStatus.NOT_FOUND);
        }
        log.info("Item Found");
        return new ResponseEntity<ItemResource>(item, HttpStatus.OK);
    }


    /*
     * Get Item For Web App
     *
     * @param ItemID
     *
     * @return ItemResource
     */
    @Operation(summary = "This is to fetch Item by Item ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Item found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Item not found",
                    content = @Content)
    })
    @GetMapping("getItem/{itemId}")
    public ResponseEntity<VwGetItem> getItem(@PathVariable("itemId") Integer itemId) {
        log.info("Get Item by itemId: " + itemId);
        return ResponseEntity.ok(itemService.getItemById(itemId));
    }


    /**
     * Create an Item
     *
     * @param itemResource
     * @return ResponseEntity<ItemResource>
     */
    @Operation(summary = "This is to create an Item")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Item created successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ItemResource> createItem(@Valid @RequestBody ItemResource itemResource) {
        log.info("Item creation request");
        return ResponseEntity.ok(itemService.createItem(itemResource));
    }

    @Operation(summary = "This is to update an Item")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Item updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PutMapping(value = "{itemId}", consumes = "application/json")
    public ResponseEntity<Void> update(@PathVariable("itemId") Integer itemId, @Valid @RequestBody ItemResource itemResource) {
        log.info("Item update request by Id: " + itemId);
        itemService.updateItem(itemResource);
        log.info("Item updated successfully");
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @Operation(summary = "This is to delete an Item")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Item deleted successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @DeleteMapping("{itemId}/{companyId}/{username}/{tag}/{typeName}")
    public ResponseEntity<String> delete(@PathVariable("itemId") Integer itemId,
                                       @PathVariable("companyId") Integer companyId, @PathVariable("username") String username,
                                       @PathVariable("tag") String tag, @PathVariable("typeName") String typeName) {
        log.info("Item deletion request by Id: " + itemId);
        String actionComment = ModulesEnum.ITEM.getModule() + " " + typeName + ":" + tag + " "
                + GoTracratConstants.DELETED_BY + " " + username;
        UserLog userLog = GotracratUtility.getUserLog(username, companyId, ModulesEnum.ITEM.getModule(), actionComment);
        log.info("Item deleted successfully");
        return  ResponseEntity.ok(itemService.deleteItem(itemId, companyId, userLog));
    }

    /*
     * get All Items based on the search criteria given. we will return list of
     * items along with rank(used in UI to restrict access) based on user role.
     *
     * @return Map<typename,List<ItemResources>
     */
    @Operation(summary = "This is to fetch Items")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Items found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(value = "/search", consumes = "application/json")
    public ResponseEntity<Map<String, List<SearchResponseDTO>>> searchItems(@RequestBody ItemSearchRequestDTO request) {
        log.info("Search Items in Company: " + request.getCompanyId());
        return ResponseEntity.ok(itemService.searchItems(request));
    }

    /*
     *  get All Items based on the search criteria given. we will return list of
     * items along with rank(used in UI to restrict access) based on user role
     *
     * @return Map<typename,List<ItemResources>
     */
    @Operation(summary = "This is to fetch Items by Company ID and User ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Items found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(value = "/search/{companyId}/{isOwnerAdmin}/{userId}", consumes = "application/json")
    public ResponseEntity<Map<String, List<ItemResource>>> searchItems(@RequestBody ItemResource itemResource,
                                                                       @PathVariable("companyId") Integer companyId, @PathVariable("isOwnerAdmin") Boolean isOwnerAdmin,
                                                                       @PathVariable("userId") String userId) {
        log.info("Search Items");
        final Map<String, List<ItemResource>> itemsMap = itemService.searchItems(itemResource, companyId, isOwnerAdmin,
                userId);
        log.info("Items found ");
        return new ResponseEntity<Map<String, List<ItemResource>>>(itemsMap, HttpStatus.OK);
    }

    /*
     * get Items across all companies based on the search criteria given. This
     * feature is only for ownerAdmin role
     *
     * @return Map<companyname,<typename,List<MasterSearchResponseDTO>>>
     */
    @Operation(summary = "This is to fetch Items from Master Search")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Items found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(value = "/masterSearch", consumes = "application/json")
    public ResponseEntity<Map<String, Map<String, List<MasterSearchResponseDTO>>>> masterSearch(
            @Valid @RequestBody MasterSearchRequestDTO masterSearchRequestDTO) {
        log.info("Master Search");
        return ResponseEntity.ok(itemService.masterSearch(masterSearchRequestDTO));
    }


    /*
     * search Items or attributes across all companies based on the search criteria given. This
     * feature is only for ownerAdmin role
     *
     * @return List<MasterSearchWithAttributesResponseDTO>
     */
    @Operation(summary = "This is to fetch Items with Master Search Attributes")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Items found with Attributes",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @PostMapping(value = "/masterSearchAttributes", consumes = "application/json")
    public ResponseEntity<List<MasterSearchWithAttributesResponseDTO>> masterSearchWithAttributes(
            @Valid @RequestBody MasterSearchWithAttributesRequestDTO masterSearchWithAttributesRequestDTO) {
        log.info("Master Search With Attributes");
        return ResponseEntity.ok(itemService.masterSearchWithAttributes(masterSearchWithAttributesRequestDTO));
    }

    /*
     * Used for auto suggestion in UI
     *
     * @param tagname -tag entered in UI
     *
     * @param Id of the company user selected
     *
     * @return List<tags> that match the given tag in company given.
     */
    @Operation(summary = "This is to fetch Tags by Company ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Tags found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Tags not found",
                    content = @Content)
    })
    @GetMapping(value = "/suggestions/{tagName}/{companyId}")
    public ResponseEntity<List<String>> searchTagsByCompanyId(@PathVariable("tagName") String tagName,
                                                              @PathVariable("companyId") Integer companyId) {
        log.info("Get tag suggestions: " + tagName + "," + companyId);
        final List<String> tagList = itemService.searchTags(tagName, companyId);
        log.info("suggestions found");
        return new ResponseEntity<List<String>>(tagList, HttpStatus.OK);
    }

    /*
     * used to check tag availabiltity in add item UI. used to check if the tag
     * already exists in that type.
     *
     * @param tagname -tag entered in UI
     *
     * @param typeid -Id of the type user selected in add item,edit item,clone item
     *
     * @return List<tags> that match the given tag in type given
     */
    @Operation(summary = "This is check Tag Availability")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Tag Available",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Tag not found",
                    content = @Content)
    })
    @GetMapping(value = "/checkTagAvailability/{tagName}/{typeId}")
    public ResponseEntity<List<String>> checkTagAvailability(@PathVariable("tagName") String tagName,
                                                             @PathVariable("typeId") Integer typeId) {
        log.info("Check Tag Availability: " + tagName + "," + typeId);
        return ResponseEntity.ok(itemService.searchTagsByType(tagName, typeId));
    }

    /*
     * Used to Update default image of an item
     *
     * @param itemid-id of the item
     *
     * @param defaultImageID-AttachmentID
     */
    @Operation(summary = "This is to update Default Image for Item")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Default Image for Item updated successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @GetMapping(value = "{itemId}/{defaultImageId}", produces = "application/json")
    public ResponseEntity<Void> updateDefaultImageForItem(@PathVariable("itemId") Integer itemId,
                                                          @PathVariable("defaultImageId") Integer attachmentId) {
        log.info("Update item default image  by itemId and attachmentId: " + itemId + "," + attachmentId);
        itemService.updateDefaultImageForItem(itemId, attachmentId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /*
     * returns attributes which are used in find replacement for this item in UI.
     *
     * @param itemId-ItemId
     */
    @Operation(summary = "This is to fetch Attributes for Item Replacement")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Attributes for Item Replacement found successfully",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })
    @GetMapping(value = "/findReplacement/{itemId}")
    public ResponseEntity<List<FindReplacementDTO>> getAllItemAttributesForReplacement(
            @PathVariable("itemId") Integer itemId) {
        log.info("Get Attributes For findReplacement of item: " + itemId);
        return ResponseEntity.ok(itemService.getAllItemAttributesForReplacement(itemId));
    }

    /*
     * returns journal data(top 3) of an item ,displayed in view Item screen.under
     * changeLog
     *
     * @param itemId-ItemId
     */
    @Operation(summary = "This is to fetch Journal by Item ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Journal found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Journal not found",
                    content = @Content)
    })
    @GetMapping(value = "/journal/{itemId}")
    public ResponseEntity<List<ChangeLogDTO>> getJournalByItemId(@PathVariable("itemId") Integer itemId) {
        log.info("get Top three changeLog Data of item: " + itemId);
        return ResponseEntity.ok(itemService.getChangeLog(itemId));
    }

    /*
     * returns last repair date and vendor for warehouse tag
     *
     * @param itemId-ItemId
     */
    @Operation(summary = "This is to fetch Warehouse Tag")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "Warehouse Tag found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "Warehouse Tag not found",
                    content = @Content)
    })
    @GetMapping(value = "/warehouseTag/{itemId}")
    public ResponseEntity<WarehouseTagDTO> getWarehouseTag(@PathVariable("itemId") Integer itemId) throws IOException {
        log.info("Get WarehouseTag by itemId: " + itemId);
        return ResponseEntity.ok(itemService.getWarehouseTag(itemId));
    }

    @Operation(summary = "This is to fetch GoForm Data")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "GoForm Data found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "GoForm Data not found",
                    content = @Content)
    })
    @PostMapping(value = "/getGoFormData",consumes = "application/json")
    public ResponseEntity<String> getGoFormData(@RequestBody GoFormResource goFormResource) {
        log.info("Get goFormz data");
        return  ResponseEntity.ok(itemService.createItemRepairUsingGoForm(goFormResource));
    }

    @Operation(summary = "This is to fetch GoForm Data")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    description = "GoForm Data found",
                    content = {@Content(mediaType = "application/json")}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404",
                    description = "GoForm Data not found",
                    content = @Content)
    })
    @GetMapping("/getFailedItemsTwiceInYear/{companyId}")
    public ResponseEntity<Map<String,List<FailedItemsResource>>> getFailedItemsTwiceInYear(@PathVariable("companyId") Integer companyId) {
        log.info("Get Failed Items Twice In a Year");
        return  ResponseEntity.ok(itemService.getFailedItemsTwiceInYear(companyId));
    }
}