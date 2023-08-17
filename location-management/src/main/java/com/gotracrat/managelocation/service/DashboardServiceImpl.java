package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.dto.AllRepairsDTO;
import com.gotracrat.managelocation.dto.FailureCauseDTO;
import com.gotracrat.managelocation.repository.AnnouncementRepository;
import com.gotracrat.managelocation.repository.DashboardDAO;
import com.gotracrat.managelocation.resource.*;
import com.gotracrat.managelocation.utils.DateUtils;
import com.gotracrat.managelocation.utils.GoTracratConstants;
import com.gotracrat.managelocation.utils.TimeFrame;
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
 * Service implementation for Dashboard.
 *
 * @author prabhakar
 */
@Slf4j
@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    DashboardDAO dashboardDAO;

    @Autowired
    AnnouncementRepository announcementRepository;

    /**
     * This method is used for Get the recent data in Dashboard
     *
     * @return DashboardResource
     */
    @Override
    public DashboardResource getRecentDashboardData(Integer companyId, String isOwnerAdmin, String userId) {
        DashboardResource dashboardResource = new DashboardResource();
        dashboardResource.setCompanyId(companyId);
        List<RecentRepairResource> repairResources = dashboardDAO.getRecentRepairs(companyId, isOwnerAdmin, userId);
        if (!repairResources.isEmpty()) {
            dashboardResource.setRepairResource(buildAttachmentsFromXmlForRepairs(repairResources));
        }
        List<RecentItemsResource> itemResource = dashboardDAO.getRecentItems(companyId, isOwnerAdmin, userId);
        if (!itemResource.isEmpty()) {
            dashboardResource.setRecentItemResource (buildMap(buildAttributesFromXml(itemResource)));
        }
        List<NotesResource> notesResources = dashboardDAO.getRecentlyAddedNotes(companyId, isOwnerAdmin, userId);
        if (!notesResources.isEmpty()) {
            dashboardResource.setNotesResource(buildAttachmentsFromXmlForNotes(notesResources));
        }
        dashboardResource.setAnnouncementList(announcementRepository.findByAnnouncementText(companyId));
        return dashboardResource;
    }


    private List<NotesResource> buildAttachmentsFromXmlForNotes(List<NotesResource> notesResources) {
        notesResources.forEach(repair -> {
            if (repair.getAttachmentXml() != null) {
                JAXBContext jaxbContext;
                try {
                    jaxbContext = JAXBContext.newInstance(AttachmentXml.class);
                    Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
                    AttachmentXml attachments = (AttachmentXml) jaxbUnMarshaller
                            .unmarshal(new StringReader(repair.getAttachmentXml()));
                    repair.setAttachmentListFromXml(attachments.getAttachmentsList());
                } catch (JAXBException exception) {
                    log.error(GoTracratConstants.ERROR, exception);
                }
            }
        });
        return notesResources;
    }

    private Map<String, List<RecentItemsResource>> buildMap(List<RecentItemsResource> recentItemsResources) {
        return recentItemsResources.stream().collect(Collectors.groupingBy(RecentItemsResource::getTypeName));
    }

    /**
     * This method is used for to get all the Repairs in Dashboard
     *
     * @return List of RecentRepairResource
     */
    @Override
    public List<RecentRepairResource> getAllRepairsInDashboard(AllRepairsDTO allRepairsDTO) {
        List<RecentRepairResource> repairs = new ArrayList<>();
        if (allRepairsDTO.getTimeFrame() != null && allRepairsDTO.getTimeFrame().contains("YEAR")) {
            if (allRepairsDTO.getTimeFrame().equalsIgnoreCase(TimeFrame.LASTYEAR.getTimeType())) {
                allRepairsDTO.setStartDate(DateUtils.getLastYearDateFromToday());
                allRepairsDTO.setEndDate(DateUtils.getTodayDate());
            } else {
                allRepairsDTO.setStartDate(DateUtils.getLastTwoYearDateFromToday());
                allRepairsDTO.setEndDate(DateUtils.getTodayDate());
            }
        } else if (allRepairsDTO.getTimeFrame() != null && allRepairsDTO.getTimeFrame().equalsIgnoreCase(TimeFrame.LASTQUARTER.getTimeType())) {
            allRepairsDTO.setStartDate(DateUtils.getLastThirdMonthFromToday());
            allRepairsDTO.setEndDate(DateUtils.getTodayDate());
        } else if (allRepairsDTO.getTimeFrame() != null && allRepairsDTO.getTimeFrame().equalsIgnoreCase(TimeFrame.LASTMONTH.getTimeType())) {
            allRepairsDTO.setStartDate(DateUtils.getLastMonth());
            allRepairsDTO.setEndDate(DateUtils.getTodayDate());
        }
        if (allRepairsDTO.getStatusFlag() == 0) {
            return dashboardDAO.getAllInCompletedRepairsInDashboard(allRepairsDTO);
        } else if (allRepairsDTO.getStatusFlag() == 1) {
            return dashboardDAO.getAllCompletedRepairsInDashboard(allRepairsDTO);
        }
        if (!repairs.isEmpty()) {
            return buildAttachmentsFromXmlForRepairs(repairs);
        }
        return repairs;
    }

    private List<RecentItemsResource> buildAttributesFromXml(List<RecentItemsResource> items) {
        items.forEach(item -> {
            if (item.getAttributeXml() != null) {
                JAXBContext jaxbContext;
                try {
                    jaxbContext = JAXBContext.newInstance(AttributesXml.class);
                    Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
                    AttributesXml attributes = (AttributesXml) jaxbUnMarshaller
                            .unmarshal(new StringReader(item.getAttributeXml()));
                    item.setAttributeList(attributes.getAttributesList());
                } catch (JAXBException exception) {
                    log.error(GoTracratConstants.ERROR, exception);
                }
            }
        });
        return items;
    }

    private List<RecentRepairResource> buildAttachmentsFromXmlForRepairs(List<RecentRepairResource> repairResources) {
        repairResources.forEach(repair -> {
            if (repair.getAttachmentXml() != null) {
                JAXBContext jaxbContext;
                try {
                    jaxbContext = JAXBContext.newInstance(AttachmentXml.class);
                    Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
                    AttachmentXml attachments = (AttachmentXml) jaxbUnMarshaller
                            .unmarshal(new StringReader(repair.getAttachmentXml()));
                    repair.setAttachmentListFromXml(attachments.getAttachmentsList());
                } catch (JAXBException exception) {
                    log.error(GoTracratConstants.ERROR, exception);
                }
            }
        });
        return repairResources;
    }

    /**
     * This method is used for to get Dashboard results by Time Frame
     *
     * @return Map<String, BigDecimal>
     */
    @Override
    public Map<String, BigDecimal> getDashboardResultsByTimeFrame(FailureCauseDTO failureCauseDTO) {
        failureCauseDTO.setFailureType(null);
        Map<String, BigDecimal> percentageMap = new LinkedHashMap<>();
        if (failureCauseDTO.getTimeFrame() != null && failureCauseDTO.getTimeFrame().contains("YEAR")) {
            if (failureCauseDTO.getTimeFrame().equalsIgnoreCase(TimeFrame.LASTYEAR.getTimeType())) {
                failureCauseDTO.setYear(1);
                percentageMap = getOldYearsData(failureCauseDTO);
            } else {
                failureCauseDTO.setYear(2);
                percentageMap = getOldYearsData(failureCauseDTO);
            }
        } else if (failureCauseDTO.getTimeFrame() != null && failureCauseDTO.getTimeFrame().equalsIgnoreCase(TimeFrame.LASTQUARTER.getTimeType())) {
            percentageMap = getQuarterDates(failureCauseDTO);
        } else if (failureCauseDTO.getTimeFrame() != null && failureCauseDTO.getTimeFrame().equalsIgnoreCase(TimeFrame.LASTMONTH.getTimeType())) {
            percentageMap = getDatesOverMonth(failureCauseDTO);
        }
        return percentageMap;
    }

    /**
     * This method is used for to get Dashboard results by Range
     *
     * @return Map<String, BigDecimal>
     */
    @Override
    public Map<String, BigDecimal> getDashboardResultsByRange(FailureCauseDTO failureCauseDTO) {
        Map<String, BigDecimal> percentageMap;
        if (failureCauseDTO.getLocationId() == 0) {
            percentageMap = buildPercentageMapFailureType(dashboardDAO.getFailureTypePercentage(failureCauseDTO), failureCauseDTO.getIsByRepairCost());
        } else {
            percentageMap = buildFailureTypePercentageForLocationMap(dashboardDAO.getFailureTypePercentageForLocation(failureCauseDTO), failureCauseDTO.getIsByRepairCost());
        }
        return percentageMap;
    }

    private Map<String, BigDecimal> getOldYearsData(FailureCauseDTO failureCauseDTO) {
        Map<String, BigDecimal> percentageMap;
        String startDate;
        if (failureCauseDTO.getYear() == 1) {
            startDate = DateUtils.getLastYearDateFromToday();
        } else {
            startDate = DateUtils.getLastTwoYearDateFromToday();
        }
        failureCauseDTO.setStartDate(startDate);
        failureCauseDTO.setEndDate(DateUtils.getTodayDate());
        if (failureCauseDTO.getLocationId() == 0) {
            if (failureCauseDTO.getFailureType() == null) {
                percentageMap = buildPercentageMapFailureType(dashboardDAO.getFailureTypePercentage(failureCauseDTO), failureCauseDTO.getIsByRepairCost());
            } else {
                percentageMap = buildPercentageMapForFailureCausesAndRepairCosts(dashboardDAO.getFailureCausesAndRepairCostsPercentagesByFailureTypes(failureCauseDTO), failureCauseDTO.getIsByRepairCost());
            }
        } else {
            if (failureCauseDTO.getFailureType() == null) {
                percentageMap = buildFailureTypePercentageForLocationMap(dashboardDAO.getFailureTypePercentageForLocation(failureCauseDTO), failureCauseDTO.getIsByRepairCost());
            } else {
                percentageMap = buildFailureCausesAndRepairCostsPercentagesByFailureTypesForLocationMap(dashboardDAO.getFailureCausesAndRepairCostsPercentagesByFailureTypesForLocation(failureCauseDTO), failureCauseDTO.getIsByRepairCost());
            }
        }
        return percentageMap;
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

    private Map<String, BigDecimal> buildFailureTypePercentageForLocationMap(List<FailureTypePercentageResource> failureTypePercentages, String isByRepairCost) {
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

    private Map<String, BigDecimal> buildFailureCausesAndRepairCostsPercentagesByFailureTypesForLocationMap(List<FailureTypePercentageResource> failureTypePercentages, String isByRepairCost) {
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


    private Map<String, BigDecimal> getDatesOverMonth(FailureCauseDTO failureCauseDTO) {
        Map<String, BigDecimal> percentageMap;
        failureCauseDTO.setStartDate(DateUtils.getLastMonth());
        failureCauseDTO.setEndDate(DateUtils.getTodayDate());
        if (failureCauseDTO.getLocationId() == 0) {
            if (failureCauseDTO.getFailureType() == null) {
                percentageMap = buildPercentageMapFailureType(dashboardDAO.getFailureTypePercentage(failureCauseDTO), failureCauseDTO.getIsByRepairCost());
            } else {
                percentageMap = buildPercentageMapForFailureCausesAndRepairCosts(dashboardDAO.getFailureCausesAndRepairCostsPercentagesByFailureTypes(failureCauseDTO), failureCauseDTO.getIsByRepairCost());
            }
        } else {
            if (failureCauseDTO.getFailureType() == null) {
                percentageMap = buildFailureTypePercentageForLocationMap(dashboardDAO.getFailureTypePercentageForLocation(failureCauseDTO), failureCauseDTO.getIsByRepairCost());
            } else {
                percentageMap = buildFailureCausesAndRepairCostsPercentagesByFailureTypesForLocationMap(dashboardDAO.getFailureCausesAndRepairCostsPercentagesByFailureTypesForLocation(
                        failureCauseDTO), failureCauseDTO.getIsByRepairCost());
            }
        }
        return percentageMap;
    }

    private Map<String, BigDecimal> getQuarterDates(FailureCauseDTO failureCauseDTO) {
        Map<String, BigDecimal> percentageMap;
        failureCauseDTO.setStartDate(DateUtils.getLastThirdMonthFromToday());
        failureCauseDTO.setEndDate(DateUtils.getTodayDate());
        if (failureCauseDTO.getLocationId() == 0) {
            if (failureCauseDTO.getFailureType() == null) {
                percentageMap = buildPercentageMapFailureType(dashboardDAO.getFailureTypePercentage(failureCauseDTO), failureCauseDTO.getIsByRepairCost());
            } else {
                percentageMap = buildPercentageMapForFailureCausesAndRepairCosts(dashboardDAO.getFailureCausesAndRepairCostsPercentagesByFailureTypes(failureCauseDTO), failureCauseDTO.getIsByRepairCost());
            }
        } else {
            if (failureCauseDTO.getFailureType() == null) {
                percentageMap = buildFailureTypePercentageForLocationMap(dashboardDAO.getFailureTypePercentageForLocation(failureCauseDTO), failureCauseDTO.getIsByRepairCost());
            } else {
                percentageMap = buildFailureCausesAndRepairCostsPercentagesByFailureTypesForLocationMap(dashboardDAO.getFailureCausesAndRepairCostsPercentagesByFailureTypesForLocation(
                        failureCauseDTO), failureCauseDTO.getIsByRepairCost());
            }
        }
        return percentageMap;
    }

    /**
     * This method is used for to get Failure Causes and Repair Costs
     *
     * @return Map<String, BigDecimal>
     */
    @Override
    public Map<String, BigDecimal> getFailureCausesAndRepairCosts(FailureCauseDTO failureCauseDTO) {
        Map<String, BigDecimal> percentageMap = new LinkedHashMap<>();
        if (failureCauseDTO.getTimeFrame() != null && failureCauseDTO.getTimeFrame().contains("YEAR")) {
            if (failureCauseDTO.getTimeFrame().equalsIgnoreCase(TimeFrame.LASTYEAR.getTimeType())) {
                failureCauseDTO.setYear(1);
                percentageMap = getOldYearsData(failureCauseDTO);
            } else {
                failureCauseDTO.setYear(2);
                percentageMap = getOldYearsData(failureCauseDTO);
            }
        } else if (failureCauseDTO.getTimeFrame() != null && failureCauseDTO.getTimeFrame().equalsIgnoreCase(TimeFrame.LASTQUARTER.getTimeType())) {
            percentageMap = getQuarterDates(failureCauseDTO);
        } else if (failureCauseDTO.getTimeFrame() != null && failureCauseDTO.getTimeFrame().equalsIgnoreCase(TimeFrame.LASTMONTH.getTimeType())) {
            percentageMap = getDatesOverMonth(failureCauseDTO);
        }
        return percentageMap;
    }

    /**
     * This method is used for to get Failure Causes in Range
     *
     * @return Map<String, BigDecimal>
     */
    @Override
    public Map<String, BigDecimal> getFailureCausesInRange(FailureCauseDTO failureCauseDTO) {
        Map<String, BigDecimal> percentageMap;
        if (failureCauseDTO.getLocationId() == 0) {
            percentageMap = buildPercentageMapForFailureCausesAndRepairCosts(dashboardDAO.getFailureCausesAndRepairCostsPercentagesByFailureTypes(failureCauseDTO), failureCauseDTO.getIsByRepairCost());
        } else {
            percentageMap = buildFailureCausesAndRepairCostsPercentagesByFailureTypesForLocationMap(dashboardDAO.getFailureCausesAndRepairCostsPercentagesByFailureTypesForLocation(failureCauseDTO), failureCauseDTO.getIsByRepairCost());
        }
        return percentageMap;
    }

    /**
     * This method is used for to get Repair Jobs by Failure Cause
     *
     * @return List of RecentRepairResource
     */
    @Override
    public List<RecentRepairResource> getRepairJobsByFailureCause(FailureCauseDTO failureCauseDTO) {
        List<RecentRepairResource> recentRepairs = new ArrayList<>();
        if (failureCauseDTO.getTimeFrame() != null && failureCauseDTO.getTimeFrame().contains("YEAR")) {
            if (failureCauseDTO.getTimeFrame().equalsIgnoreCase(TimeFrame.LASTYEAR.getTimeType())) {
                failureCauseDTO.setYear(1);
                recentRepairs = this.getRepairJobsOverYear(failureCauseDTO);
            } else {
                failureCauseDTO.setYear(2);
                recentRepairs = this.getRepairJobsOverYear(failureCauseDTO);
            }
        } else if (failureCauseDTO.getTimeFrame() != null && failureCauseDTO.getTimeFrame().equalsIgnoreCase(TimeFrame.LASTQUARTER.getTimeType())) {
            recentRepairs = this.getRepairJobsOverQuarter(failureCauseDTO);
        } else if (failureCauseDTO.getTimeFrame() != null && failureCauseDTO.getTimeFrame().equalsIgnoreCase(TimeFrame.LASTMONTH.getTimeType())) {
            recentRepairs = this.getRepairJobsOverMonth(failureCauseDTO);
        }
        return recentRepairs;
    }

    private List<RecentRepairResource> getRepairJobsOverMonth(FailureCauseDTO failureCauseDTO) {
        List<RecentRepairResource> recentRepairs;
        failureCauseDTO.setStartDate(DateUtils.getLastMonth());
        failureCauseDTO.setEndDate(DateUtils.getTodayDate());
        if (failureCauseDTO.getLocationId() == 0) {
            recentRepairs = dashboardDAO.getRepairJobsByCause(failureCauseDTO);
        } else {
            recentRepairs = dashboardDAO.getRepairJobsByCauseForLocation(failureCauseDTO);
        }
        return recentRepairs;
    }

    private List<RecentRepairResource> getRepairJobsOverQuarter(FailureCauseDTO failureCauseDTO) {
        List<RecentRepairResource> recentRepairs;
        failureCauseDTO.setStartDate(DateUtils.getLastThirdMonthFromToday());
        failureCauseDTO.setEndDate(DateUtils.getTodayDate());
        if (failureCauseDTO.getLocationId() == 0) {
            recentRepairs = dashboardDAO.getRepairJobsByCause(failureCauseDTO);
        } else {
            recentRepairs = dashboardDAO.getRepairJobsByCauseForLocation(failureCauseDTO);
        }
        return recentRepairs;
    }

    private List<RecentRepairResource> getRepairJobsOverYear(FailureCauseDTO failureCauseDTO) {
        List<RecentRepairResource> recentRepairs;
        if (failureCauseDTO.getYear() == 1) {
            failureCauseDTO.setStartDate(DateUtils.getLastYearDateFromToday());
            failureCauseDTO.setEndDate(DateUtils.getTodayDate());
        }
        if (failureCauseDTO.getYear() == 2) {
            failureCauseDTO.setStartDate(DateUtils.getLastTwoYearDateFromToday());
            failureCauseDTO.setEndDate(DateUtils.getTodayDate());
        }
        if (failureCauseDTO.getLocationId() == 0) {
            recentRepairs = dashboardDAO.getRepairJobsByCause(failureCauseDTO);
        } else {
            recentRepairs = dashboardDAO.getRepairJobsByCauseForLocation(failureCauseDTO);
        }
        return recentRepairs;
    }

    /**
     * This method is used for to get Repair Jobs by Failure Causes in Range
     *
     * @return List of RecentRepairResource
     */
    @Override
    public List<RecentRepairResource> getRepairJobsByFailureCauseInRange(FailureCauseDTO failureCauseDTO) {
        List<RecentRepairResource> recentRepairs;
        if (failureCauseDTO.getLocationId() == 0) {
            recentRepairs = dashboardDAO.getRepairJobsByCause(failureCauseDTO);
        } else {
            recentRepairs = dashboardDAO.getRepairJobsByCauseForLocation(failureCauseDTO);
        }
        return recentRepairs;
    }
}