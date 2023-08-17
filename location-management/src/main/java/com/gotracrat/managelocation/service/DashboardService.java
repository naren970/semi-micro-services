package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.dto.AllRepairsDTO;
import com.gotracrat.managelocation.dto.FailureCauseDTO;
import com.gotracrat.managelocation.resource.DashboardResource;
import com.gotracrat.managelocation.resource.RecentRepairResource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Service interface for Dashboard.
 *
 * @author prabhakar
 */
public interface DashboardService {

    DashboardResource getRecentDashboardData(Integer companyId, String isOwnerAdmin, String userId);

    List<RecentRepairResource> getAllRepairsInDashboard(AllRepairsDTO allRepairsDTO);

    Map<String, BigDecimal> getDashboardResultsByRange(FailureCauseDTO failureCauseDTO);

    Map<String, BigDecimal> getFailureCausesAndRepairCosts(FailureCauseDTO failureCauseDTO);

    Map<String, BigDecimal> getFailureCausesInRange(FailureCauseDTO failureCauseDTO);

    List<RecentRepairResource> getRepairJobsByFailureCause(FailureCauseDTO failureCauseDTO);

    List<RecentRepairResource> getRepairJobsByFailureCauseInRange(FailureCauseDTO failureCauseDTO);

    Map<String, BigDecimal> getDashboardResultsByTimeFrame(FailureCauseDTO failureCauseDTO);

}
