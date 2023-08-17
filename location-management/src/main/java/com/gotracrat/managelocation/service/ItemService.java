package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.dto.*;
import com.gotracrat.managelocation.entity.UserLog;
import com.gotracrat.managelocation.entity.VwGetItem;
import com.gotracrat.managelocation.resource.GoFormResource;
import com.gotracrat.managelocation.resource.ItemResource;
import com.gotracrat.managelocation.resource.FailedItemsResource;

import java.util.List;
import java.util.Map;

/**
 * Service interface for Item.
 * 
 * @author prabhakar
 */
public interface ItemService {

	 ItemResource getItem(Integer itemId);

	 String deleteItem(Integer itemId, Integer companyId, UserLog userLog);

	 ItemResource createItem(ItemResource itemResource);

	 void updateItem(ItemResource itemResource);

	 List<String> searchTags(String tagName, Integer companyId);

	 List<String> searchTagsByType(String tagName, Integer typeId);

	 void updateDefaultImageForItem(Integer itemId, Integer defaultImageAttachmentId);

	 List<FindReplacementDTO> getAllItemAttributesForReplacement(Integer itemId);

	 List<ChangeLogDTO> getChangeLog(Integer itemId);

	 Map<String, List<ItemResource>> searchItems(ItemResource itemResource, Integer companyId,
			Boolean isOwnerAdmin, String userId);

	 WarehouseTagDTO getWarehouseTag(Integer itemId);

	 Map<String, Map<String, List<MasterSearchResponseDTO>>> masterSearch(
			MasterSearchRequestDTO masterSearchRequestDTO);

	 Map<String, List<SearchResponseDTO>> searchItems(ItemSearchRequestDTO request);

	 VwGetItem getItemById(Integer itemId);

	 List<MasterSearchWithAttributesResponseDTO> masterSearchWithAttributes(MasterSearchWithAttributesRequestDTO masterSearchWithAttributesRequestDTO);

    String createItemRepairUsingGoForm(GoFormResource goFormResource);

	Map<String,List<FailedItemsResource>> getFailedItemsTwiceInYear(Integer companyId);
}
