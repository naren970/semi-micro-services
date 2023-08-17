package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.dto.AllRepairsDTO;
import com.gotracrat.managelocation.dto.FailureCauseDTO;
import com.gotracrat.managelocation.resource.FailureTypePercentageResource;
import com.gotracrat.managelocation.resource.NotesResource;
import com.gotracrat.managelocation.resource.RecentItemsResource;
import com.gotracrat.managelocation.resource.RecentRepairResource;

import java.util.List;

public interface DashboardDAO {

    List<RecentRepairResource> getRecentRepairs(Integer companyId, String isOwnerAdmin, String userId);

    List<RecentItemsResource> getRecentItems(Integer companyId, String isOwnerAdmin, String userId);

    List<NotesResource> getRecentlyAddedNotes(Integer companyId, String isOwnerAdmin, String userId);

    List<RecentRepairResource> getAllInCompletedRepairsInDashboard(AllRepairsDTO allRepairsDTO);

    List<RecentRepairResource> getAllCompletedRepairsInDashboard(AllRepairsDTO allRepairsDTO);

    List<RecentRepairResource> getRepairJobsByCause(FailureCauseDTO failureCauseDTO);

    List<RecentRepairResource> getRepairJobsByCauseForLocation(FailureCauseDTO failureCauseDTO);

    List<FailureTypePercentageResource> getFailureCausesAndRepairCostsPercentagesByFailureTypes(FailureCauseDTO failureCauseDTO);

    List<FailureTypePercentageResource> getFailureCausesAndRepairCostsPercentagesByFailureTypesForLocation(FailureCauseDTO failureCauseDTO);

    List<FailureTypePercentageResource> getFailureTypePercentage(FailureCauseDTO failureCauseDTO);

    List<FailureTypePercentageResource> getFailureTypePercentageForLocation(FailureCauseDTO failureCauseDTO);

}
