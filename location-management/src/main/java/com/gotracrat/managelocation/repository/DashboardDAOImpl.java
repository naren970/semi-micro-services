package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.dto.AllRepairsDTO;
import com.gotracrat.managelocation.dto.FailureCauseDTO;
import com.gotracrat.managelocation.resource.FailureTypePercentageResource;
import com.gotracrat.managelocation.resource.NotesResource;
import com.gotracrat.managelocation.resource.RecentItemsResource;
import com.gotracrat.managelocation.resource.RecentRepairResource;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DashboardDAOImpl implements DashboardDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PersistenceContext
    public EntityManager entityManager;

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<RecentRepairResource> getRecentRepairs(Integer companyId, String isOwnerAdmin, String userId) {
        return entityManager.createNativeQuery(
                "EXEC spRecentRepairLogGetByCompanyIDForUser @CompanyID=:companyId,@IsOwnerAdmin=:isOwnerAdmin,"
                        + "@UserID=:userId")
                .setParameter("companyId", companyId)
                .setParameter("isOwnerAdmin", isOwnerAdmin)
                .setParameter("userId", userId)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(RecentRepairResource.class)).getResultList();
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<RecentItemsResource> getRecentItems(Integer companyId, String isOwnerAdmin, String userId) {
        return entityManager.createNativeQuery(
                "EXEC spGetRecentlyAddedItemsByCompanyIdByUser @CompanyID=:companyId,@IsOwnerAdmin=:isOwnerAdmin,"
                        + "@UserID=:userId")
                .setParameter("companyId", companyId)
                .setParameter("isOwnerAdmin", isOwnerAdmin)
                .setParameter("userId", userId)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(RecentItemsResource.class)).getResultList();
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<NotesResource> getRecentlyAddedNotes(Integer companyId, String isOwnerAdmin, String userId) {
        return entityManager.createNativeQuery(
                "EXEC spRecentlyAddedNotesGetByCompanyIDByUser @CompanyID=:companyId,@IsOwnerAdmin=:isOwnerAdmin,"
                        + "@UserID=:userId")
                .setParameter("companyId", companyId)
                .setParameter("isOwnerAdmin", isOwnerAdmin)
                .setParameter("userId", userId)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(NotesResource.class)).getResultList();
    }


    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<RecentRepairResource> getAllInCompletedRepairsInDashboard(AllRepairsDTO allRepairsDTO) {
        return entityManager.createNativeQuery(
                "EXEC spGetAllInCompletedRepairsInDashboard @CompanyID=:companyId,@IsOwnerAdmin=:isOwnerAdmin,"
                        + "@UserID=:userId,@StartDate=:startDate,@EndDate=:endDate")
                .setParameter("companyId", allRepairsDTO.getCompanyId())
                .setParameter("isOwnerAdmin", allRepairsDTO.getIsOwnerAdmin())
                .setParameter("userId", allRepairsDTO.getUserId())
                .setParameter("startDate", allRepairsDTO.getStartDate())
                .setParameter("endDate", allRepairsDTO.getEndDate())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(RecentRepairResource.class)).getResultList();
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<RecentRepairResource> getAllCompletedRepairsInDashboard(AllRepairsDTO allRepairsDTO) {
        return entityManager.createNativeQuery(
                "EXEC spGetAllCompletedRepairsInDashboard @CompanyID=:companyId,@IsOwnerAdmin=:isOwnerAdmin,"
                        + "@UserID=:userId,@StartDate=:startDate,@EndDate=:endDate")
                .setParameter("companyId", allRepairsDTO.getCompanyId())
                .setParameter("isOwnerAdmin", allRepairsDTO.getIsOwnerAdmin())
                .setParameter("userId", allRepairsDTO.getUserId())
                .setParameter("startDate", allRepairsDTO.getStartDate())
                .setParameter("endDate", allRepairsDTO.getEndDate())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(RecentRepairResource.class)).getResultList();
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<RecentRepairResource> getRepairJobsByCause(FailureCauseDTO failureCauseDTO) {
        return entityManager.createNativeQuery(
                "EXEC spGetRepairJobs @CompanyId=:companyId,@FailureType=:failureType,"
                        + "@FailureCause=:failureCause,@StartDate=:startDate,@EndDate=:endDate,"
                        + "@IsOwnerAdmin=:isOwnerAdmin,@UserID=:userId,@TypeId=:typeId")
                .setParameter("companyId", failureCauseDTO.getCompanyId())
                .setParameter("failureType", failureCauseDTO.getFailureType())
                .setParameter("failureCause", failureCauseDTO.getFailureCause())
                .setParameter("startDate", failureCauseDTO.getStartDate())
                .setParameter("endDate", failureCauseDTO.getEndDate())
                .setParameter("isOwnerAdmin", failureCauseDTO.getIsOwnerAdmin())
                .setParameter("userId", failureCauseDTO.getUserId())
                .setParameter("typeId", failureCauseDTO.getTypeId())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(RecentRepairResource.class)).getResultList();
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<RecentRepairResource> getRepairJobsByCauseForLocation(FailureCauseDTO failureCauseDTO) {
        return entityManager.createNativeQuery(
                "EXEC spGetRepairJobsByLocation @CompanyId=:companyId,@LocationId=:locationId,@FailureType=:failureType,"
                        + "@FailureCause=:failureCause,@StartDate=:startDate,@EndDate=:endDate,"
                        + "@IsOwnerAdmin=:isOwnerAdmin,@UserID=:userId,@TypeId=:typeId")
                .setParameter("companyId", failureCauseDTO.getCompanyId())
                .setParameter("locationId", failureCauseDTO.getLocationId())
                .setParameter("failureType", failureCauseDTO.getFailureType())
                .setParameter("failureCause", failureCauseDTO.getFailureCause())
                .setParameter("startDate", failureCauseDTO.getStartDate())
                .setParameter("endDate", failureCauseDTO.getEndDate())
                .setParameter("isOwnerAdmin", failureCauseDTO.getIsOwnerAdmin())
                .setParameter("userId", failureCauseDTO.getUserId())
                .setParameter("typeId", failureCauseDTO.getTypeId())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(RecentRepairResource.class)).getResultList();
    }


    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<FailureTypePercentageResource> getFailureTypePercentage(FailureCauseDTO failureCauseDTO) {
        return entityManager.createNativeQuery(
                "EXEC spGetFailureTypePercentageByUserwithCost @CompanyId=:companyId,@StartDate=:startDate,@EndDate=:endDate,"
                        + "@IsOwnerAdmin=:isOwnerAdmin,@IsByRepairCost=:isByRepairCost,@UserID=:userId,@TypeId=:typeId")
                .setParameter("companyId", failureCauseDTO.getCompanyId())
                .setParameter("startDate", failureCauseDTO.getStartDate())
                .setParameter("endDate", failureCauseDTO.getEndDate())
                .setParameter("isOwnerAdmin", failureCauseDTO.getIsOwnerAdmin())
                .setParameter("isByRepairCost", failureCauseDTO.getIsByRepairCost())
                .setParameter("userId", failureCauseDTO.getUserId())
                .setParameter("typeId", failureCauseDTO.getTypeId())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(FailureTypePercentageResource.class)).getResultList();
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<FailureTypePercentageResource> getFailureCausesAndRepairCostsPercentagesByFailureTypes(FailureCauseDTO failureCauseDTO) {
        return entityManager.createNativeQuery(
                "EXEC spGetFailureCausePercentageWithCost @CompanyId=:companyId,@StartDate=:startDate,@EndDate=:endDate,"
                        + "@FailureType=:failureType,@IsOwnerAdmin=:isOwnerAdmin,@IsByRepairCost=:isByRepairCost,@UserID=:userId,@TypeId=:typeId")
                .setParameter("companyId", failureCauseDTO.getCompanyId())
                .setParameter("startDate", failureCauseDTO.getStartDate())
                .setParameter("endDate", failureCauseDTO.getEndDate())
                .setParameter("failureType", failureCauseDTO.getFailureType())
                .setParameter("isOwnerAdmin", failureCauseDTO.getIsOwnerAdmin())
                .setParameter("isByRepairCost", failureCauseDTO.getIsByRepairCost())
                .setParameter("userId", failureCauseDTO.getUserId())
                .setParameter("typeId", failureCauseDTO.getTypeId())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(FailureTypePercentageResource.class)).getResultList();
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<FailureTypePercentageResource> getFailureTypePercentageForLocation(FailureCauseDTO failureCauseDTO) {
        return entityManager.createNativeQuery(
                "EXEC spGetFailureTypePercentageForLocationWithCost @StartDate=:startDate,@EndDate=:endDate,"
                        + "@LocationId=:locationId,@IsByRepairCost=:isByRepairCost,@TypeId=:typeId")
                .setParameter("startDate", failureCauseDTO.getStartDate())
                .setParameter("endDate", failureCauseDTO.getEndDate())
                .setParameter("locationId", failureCauseDTO.getLocationId())
                .setParameter("isByRepairCost", failureCauseDTO.getIsByRepairCost())
                .setParameter("typeId", failureCauseDTO.getTypeId())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(FailureTypePercentageResource.class)).getResultList();
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<FailureTypePercentageResource> getFailureCausesAndRepairCostsPercentagesByFailureTypesForLocation(FailureCauseDTO failureCauseDTO) {
        return entityManager.createNativeQuery(
                "EXEC spGetFailureCausePercentageForLocationWithCost @FailureType=:failureType,@StartDate=:startDate,@EndDate=:endDate,"
                        + "@LocationId=:locationId,@IsByRepairCost=:isByRepairCost,@TypeId=:typeId")
                .setParameter("failureType", failureCauseDTO.getFailureType())
                .setParameter("startDate", failureCauseDTO.getStartDate())
                .setParameter("endDate", failureCauseDTO.getEndDate())
                .setParameter("locationId", failureCauseDTO.getLocationId())
                .setParameter("isByRepairCost", failureCauseDTO.getIsByRepairCost())
                .setParameter("typeId", failureCauseDTO.getTypeId())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(FailureTypePercentageResource.class)).getResultList();
    }
}