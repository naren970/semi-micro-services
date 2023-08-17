package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.dto.AdvanceSearchNotesDTO;
import com.gotracrat.managelocation.dto.AdvanceSearchRepairDTO;
import com.gotracrat.managelocation.dto.ItemAdsRequestDTO;
import com.gotracrat.managelocation.repository.AdvanceSearchDAO;
import com.gotracrat.managelocation.resource.*;
import com.gotracrat.managelocation.utils.GoTracratConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Service implementation for Item Advance Search.
 *
 * @author prabhakar
 * @since 2018-05-25
 */
@Slf4j
@Service
public class AdvanceSearchServiceImpl implements AdvanceSearchService {

    @Autowired
    private AdvanceSearchDAO advanceSearchDAO;

    /**
     * This method is used for to get RepairLog Note
     *
     * @return AdvanceSearchResource
     */
    @Override
    public AdvanceSearchResource getAdvanceSearchRepairLogNoteByUser(AdvanceSearchResource advanceSearchResource) {
        if (advanceSearchResource.isNoteFlag()) {
            List<AdvanceSearchNotesDTO> journalResourceList = advanceSearchDAO
                    .getAdvanceSearchItemNoteByUser(advanceSearchResource);
            advanceSearchResource.setItemNotes(journalResourceList);
        }
        if (advanceSearchResource.isRepairFlag()) {
            List<AdvanceSearchRepairDTO> repairLogResourceList = advanceSearchDAO
                    .getAdvanceSearchRepairLogByUser(advanceSearchResource);
            advanceSearchResource.setRepairlogList(repairLogResourceList);
        }
        return advanceSearchResource;
    }

    /**
     * This method is used for to get Items Based on search criteria
     *
     * @return Map<String, List < ItemAdsSearchResource>
     */
    @Override
    public Map<String, List<ItemAdsSearchResource>> itemAdvancedSearch(ItemAdsRequestDTO itemAdsRequestDTO) {
        List<ItemAdsSearchResource> searchResults = advanceSearchDAO.itemAdvancedSearch(itemAdsRequestDTO,
                buildXmlString(itemAdsRequestDTO.getAttributeNameList()));
        searchResults.forEach(item -> {
            if (item.getAttributesXml() != null) {
                JAXBContext jaxbContext;
                try {
                    jaxbContext = JAXBContext.newInstance(AttributesXml.class);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    AttributesXml attributes = (AttributesXml) jaxbUnmarshaller
                            .unmarshal(new StringReader(item.getAttributesXml()));
                    item.setAttributeNameList(attributes.getAttributesList());
                } catch (JAXBException exception) {
                    log.error(GoTracratConstants.ERROR, exception);
                }
            }
        });
        Map<String, List<ItemAdsSearchResource>> resultsMap = searchResults.stream()
                .collect(Collectors.groupingBy(ItemAdsSearchResource::getTypeName));
        return new TreeMap<>(resultsMap);
    }

    @Override
    public Map<String, BigDecimal> getAdvancedSearchPieChartsData(AdvSearchPieChartResource advSearchPieChartResource) {
        advSearchPieChartResource.setFailureType(null);
        return buildPercentageMapFailureType(advanceSearchDAO.getFailureTypePercentage(advSearchPieChartResource,buildItemsIdsXml(advSearchPieChartResource.getItemIds())),
                advSearchPieChartResource.getIsByRepairCost());
    }

    @Override
    public Map<String, BigDecimal> getFailureCausesAndRepairCosts(AdvSearchPieChartResource advSearchPieChartResource) {
      return buildPercentageMapForFailureCausesAndRepairCosts(advanceSearchDAO.getFailureCausesAndRepairCostsPercentagesByFailureTypes(advSearchPieChartResource,buildItemsIdsXml(advSearchPieChartResource.getItemIds())),
      advSearchPieChartResource.getIsByRepairCost());
    }

    @Override
    public List<RecentRepairResource> getRepairJobsByFailureCause(AdvSearchPieChartResource advSearchPieChartResource) {
        return advanceSearchDAO.getRepairJobsByCause(advSearchPieChartResource,buildItemsIdsXml(advSearchPieChartResource.getItemIds()));
    }

    private String buildItemsIdsXml(List<Integer> itemsIds) {
        StringBuilder xmlBuilder = null;
        xmlBuilder = new StringBuilder("<ItemsList>");
        StringBuilder finalXmlBuilder = xmlBuilder;
        itemsIds.forEach(id -> finalXmlBuilder.append("<Item><ItemID>").append(id).append("</ItemID></Item>"));
        xmlBuilder.append("</ItemsList>");
        return new String(xmlBuilder);
    }

    private Map<String, BigDecimal> buildPercentageMapFailureType(List<FailureTypePercentageResource> failureTypePercentages, String isByRepairCost) {
        Map<String, BigDecimal> percentageMap = new HashMap<>();
        failureTypePercentages.forEach(failureType -> {
            if (isByRepairCost.equalsIgnoreCase("true")) {
                percentageMap.put(failureType.getFailureType(), failureType.getCost());
            } else {
                percentageMap.put(failureType.getFailureType(), failureType.getTotalPercentage());
            }
        });
        return percentageMap;
    }

    private Map<String, BigDecimal> buildPercentageMapForFailureCausesAndRepairCosts(List<FailureTypePercentageResource> failureTypePercentages, String isByRepairCost) {
        Map<String, BigDecimal> percentageMap = new HashMap<>();
        failureTypePercentages.forEach(failureType -> {
            if (isByRepairCost.equalsIgnoreCase("true")) {
                percentageMap.put(failureType.getFailureCause(), failureType.getCost());
            } else {
                percentageMap.put(failureType.getFailureCause(), failureType.getTotalPercentage());
            }
        });
        return percentageMap;
    }

    private String buildXmlString(List<AttributeName> attributeNameList) {
        String attributeString = null;
        StringBuilder xmlBuilder = null;
        if (attributeNameList != null && !attributeNameList.isEmpty()) {
            xmlBuilder = new StringBuilder("<AttributeList>");
            for (AttributeName attributeName : attributeNameList) {
                xmlBuilder.append("<Attribute>");
                xmlBuilder.append("<AttributeNameID>" + attributeName.getAttributeNameID() + "</AttributeNameID>");
                xmlBuilder.append("<AttributeValue>" + attributeName.getValue() + "</AttributeValue>");
                xmlBuilder.append("<SupplementalValue>" + attributeName.getValue() + "</SupplementalValue>");
                xmlBuilder.append("</Attribute>");
            }
            xmlBuilder.append("</AttributeList>");
        }
        if (xmlBuilder != null)
            attributeString = new String(xmlBuilder);
        return attributeString;
    }

}