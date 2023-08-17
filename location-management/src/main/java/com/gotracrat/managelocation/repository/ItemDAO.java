
package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.dto.*;
import com.gotracrat.managelocation.resource.ItemResource;
import com.gotracrat.managelocation.resource.FailedItemsResource;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author prabhakar
 */
public interface ItemDAO {

     ItemResource getItem(Integer itemId);

     String getLocationName(Integer locationId);

     List<FindReplacementDTO> getAllItemAttributesForReplacement(Integer itemId);

     Map<String, Object> getAllLocationByHierarchy(Integer companyId) throws SQLException;

     List<ItemResource> searchItems(ItemResource itemResource, Integer companyId, Boolean isOwnerAdmin,
                                          String userId);

     List<MasterSearchResponseDTO> masterSearch(MasterSearchRequestDTO masterSearchRequestDTO);

     List<MasterSearchWithAttributesResponseDTO> masterSearchWithAttributes(MasterSearchWithAttributesRequestDTO masterSearchWithAttributesRequestDTO);

     List<SearchResponseDTO> searchItems(ItemSearchRequestDTO request);

    List<FailedItemsResource> getFailedItemsTwiceInYear(Integer companyId, String startDate, String endDate);
}