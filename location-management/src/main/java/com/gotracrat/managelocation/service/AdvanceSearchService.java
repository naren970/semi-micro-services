package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.dto.ItemAdsRequestDTO;
import com.gotracrat.managelocation.resource.AdvanceSearchResource;
import com.gotracrat.managelocation.resource.AdvSearchPieChartResource;
import com.gotracrat.managelocation.resource.ItemAdsSearchResource;
import com.gotracrat.managelocation.resource.RecentRepairResource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Service interface for Journal.
 *
 * @author prabhakar
 * @since 2018-05-25
 */
public interface AdvanceSearchService {

    AdvanceSearchResource getAdvanceSearchRepairLogNoteByUser(AdvanceSearchResource advanceSearchResource);

    Map<String, List<ItemAdsSearchResource>> itemAdvancedSearch(ItemAdsRequestDTO itemAdsRequestDTO);

    Map<String, BigDecimal> getAdvancedSearchPieChartsData(AdvSearchPieChartResource advSearchPieChartResource);


    Map<String, BigDecimal> getFailureCausesAndRepairCosts(AdvSearchPieChartResource advSearchPieChartResource);


    List<RecentRepairResource> getRepairJobsByFailureCause(AdvSearchPieChartResource advSearchPieChartResource);

}
