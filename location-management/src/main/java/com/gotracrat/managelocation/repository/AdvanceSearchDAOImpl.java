package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.dto.AdvanceSearchNotesDTO;
import com.gotracrat.managelocation.dto.AdvanceSearchRepairDTO;
import com.gotracrat.managelocation.dto.ItemAdsRequestDTO;
import com.gotracrat.managelocation.resource.*;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class AdvanceSearchDAOImpl implements AdvanceSearchDAO {

    @PersistenceContext
    public EntityManager entityManager;

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<AdvanceSearchRepairDTO> getAdvanceSearchRepairLogByUser(AdvanceSearchResource advanceSearchResource) {

        return entityManager.createNativeQuery(
                "EXEC spGetRepairsByCompanyIdAndUser @CompanyID=:companyId,@TagNumber =:tagNumber,@RFQNumber=:rfqNumber," +
                        "@PONumber=:poNumber,@JobNumber=:jobNumber,@IsOwnerAdmin =:isOwnerAdmin,"
                        + "@UserID =:userId")
                .setParameter("companyId", advanceSearchResource.getCompanyID())
                .setParameter("tagNumber", advanceSearchResource.getExtraTag())
                .setParameter("rfqNumber", advanceSearchResource.getRFQ())
                .setParameter("poNumber", advanceSearchResource.getPO())
                .setParameter("jobNumber", advanceSearchResource.getJob())
                .setParameter("isOwnerAdmin", advanceSearchResource.getIsOwnerAdmin())
                .setParameter("userId", advanceSearchResource.getUserId())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(AdvanceSearchRepairDTO.class)).getResultList();
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<AdvanceSearchNotesDTO> getAdvanceSearchItemNoteByUser(AdvanceSearchResource advanceSearchResource) {

        return entityManager.createNativeQuery(
                "EXEC spGetNotesByCompanyIdAndUser @CompanyID=:companyId,@TagNumber =:tagNumber,@PONumber=:poNumber,"
                        + "@JobNumber=:jobNumber,@IsOwnerAdmin =:isOwnerAdmin,"
                        + "@UserID =:userId")
                .setParameter("companyId", advanceSearchResource.getCompanyID())
                .setParameter("tagNumber", advanceSearchResource.getExtraTag())
                .setParameter("poNumber", advanceSearchResource.getPO())
                .setParameter("jobNumber", advanceSearchResource.getJob())
                .setParameter("isOwnerAdmin", advanceSearchResource.getIsOwnerAdmin())
                .setParameter("userId", advanceSearchResource.getUserId())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(AdvanceSearchNotesDTO.class)).getResultList();
    }


    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<ItemAdsSearchResource> itemAdvancedSearch(ItemAdsRequestDTO itemAdsRequestDTO, String attributesXml) {
        return entityManager.createNativeQuery(
                "EXEC spAdvanceItemSearchByUser @CompanyID=:companyId,@Name=:name,@TagNumber =:tagNumber,@SerialNumber=:serialNumber,"
                        + "@ModelNumber=:modelNumber,@LocationName=:locationName,@StatusID=:statusId,"
                        + "@LocationID=:locationId,@TypeID=:typeId,@Attributes=:attributes,"
                        + "@MaxHitCount=:maxHitCount,@IsOwnerAdmin =:isOwnerAdmin,@UserID =:userId")
                .setParameter("companyId", itemAdsRequestDTO.getCompanyId())
                .setParameter("name", itemAdsRequestDTO.getName())
                .setParameter("tagNumber", itemAdsRequestDTO.getTag())
                .setParameter("serialNumber", null)
                .setParameter("modelNumber", null)
                .setParameter("locationName", itemAdsRequestDTO.getLocationName())
                .setParameter("statusId", itemAdsRequestDTO.getStatusId())
                .setParameter("locationId", itemAdsRequestDTO.getLocationId())
                .setParameter("typeId", itemAdsRequestDTO.getTypeId())
                .setParameter("attributes", attributesXml)
                .setParameter("maxHitCount", itemAdsRequestDTO.getMaxHitCount())
                .setParameter("isOwnerAdmin", itemAdsRequestDTO.isOwnerAdmin())
                .setParameter("userId", itemAdsRequestDTO.getUserId())
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(ItemAdsSearchResource.class)).getResultList();

    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<FailureTypePercentageResource> getFailureCausesAndRepairCostsPercentagesByFailureTypes(AdvSearchPieChartResource advSearchPieChartResource, String itemIdsXml) {
        return entityManager.createNativeQuery(
                "EXEC spGetFailureCausePercentageWithCostForAdvSearch @CompanyId=:companyId,@StartDate=:startDate,@EndDate=:endDate,"
                        + "@FailureType=:failureType,@IsByRepairCost=:isByRepairCost,@ItemIds=:itemIds")
                .setParameter("companyId", advSearchPieChartResource.getCompanyId())
                .setParameter("startDate", advSearchPieChartResource.getStartDate())
                .setParameter("endDate", advSearchPieChartResource.getEndDate())
                .setParameter("failureType", advSearchPieChartResource.getFailureType())
                .setParameter("isByRepairCost", advSearchPieChartResource.getIsByRepairCost())
                .setParameter("itemIds", itemIdsXml)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(FailureTypePercentageResource.class)).getResultList();
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<FailureTypePercentageResource> getFailureTypePercentage(AdvSearchPieChartResource advSearchPieChartResource, String itemIdsXml) {
        return entityManager.createNativeQuery(
                "EXEC spGetFailureTypePercentageByUserwithCostForAdvSearch @CompanyId=:companyId,@StartDate=:startDate,@EndDate=:endDate,"
                        + "@IsByRepairCost=:isByRepairCost,@ItemIds=:itemIds")
                .setParameter("companyId", advSearchPieChartResource.getCompanyId())
                .setParameter("startDate", advSearchPieChartResource.getStartDate())
                .setParameter("endDate", advSearchPieChartResource.getEndDate())
                .setParameter("isByRepairCost", advSearchPieChartResource.getIsByRepairCost())
                .setParameter("itemIds", itemIdsXml)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(FailureTypePercentageResource.class)).getResultList();
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public List<RecentRepairResource> getRepairJobsByCause(AdvSearchPieChartResource advSearchPieChartResource, String itemIdsXml) {
        return entityManager.createNativeQuery(
                "EXEC spGetRepairJobsForAdvSearch @CompanyId=:companyId,@FailureType=:failureType,"
                        + "@FailureCause=:failureCause,@StartDate=:startDate,@EndDate=:endDate,"
                        + "@IsOwnerAdmin=:isOwnerAdmin,@UserID=:userId,@ItemIds=:itemIds")
                .setParameter("companyId", advSearchPieChartResource.getCompanyId())
                .setParameter("failureType", advSearchPieChartResource.getFailureType())
                .setParameter("failureCause", advSearchPieChartResource.getFailureCause())
                .setParameter("startDate", advSearchPieChartResource.getStartDate())
                .setParameter("endDate", advSearchPieChartResource.getEndDate())
                .setParameter("isOwnerAdmin", advSearchPieChartResource.getIsOwnerAdmin())
                .setParameter("userId", advSearchPieChartResource.getUserId())
                .setParameter("itemIds", itemIdsXml)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(RecentRepairResource.class)).getResultList();
    }


}
