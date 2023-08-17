package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.dto.AdvanceSearchNotesDTO;
import com.gotracrat.managelocation.dto.AdvanceSearchRepairDTO;
import com.gotracrat.managelocation.dto.ItemAdsRequestDTO;
import com.gotracrat.managelocation.resource.*;

import java.util.List;


public interface AdvanceSearchDAO {

    public List<AdvanceSearchNotesDTO> getAdvanceSearchItemNoteByUser(AdvanceSearchResource advanceSearchResource);

    public List<AdvanceSearchRepairDTO> getAdvanceSearchRepairLogByUser(AdvanceSearchResource advanceSearchResource);

    public List<ItemAdsSearchResource> itemAdvancedSearch(ItemAdsRequestDTO itemAdsRequestDTO, String attributesXml);

    List<FailureTypePercentageResource> getFailureTypePercentage(AdvSearchPieChartResource advSearchPieChartResource, String itemIdsXml);

    List<FailureTypePercentageResource> getFailureCausesAndRepairCostsPercentagesByFailureTypes(AdvSearchPieChartResource advSearchPieChartResource, String itemIdsXml);

    List<RecentRepairResource> getRepairJobsByCause(AdvSearchPieChartResource advSearchPieChartResource, String itemIdsXml);

}
