package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.dto.*;
import com.gotracrat.managelocation.entity.VwSpareMotor;
import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import com.gotracrat.managelocation.repository.ReportsDAO;
import com.gotracrat.managelocation.repository.VwSpareMotorRepository;
import com.gotracrat.managelocation.resource.InServiceVsSpareRequest;
import com.gotracrat.managelocation.resource.ItemServicesResource;
import com.gotracrat.managelocation.resource.ServiceReportRequest;
import com.gotracrat.managelocation.resource.SparesXml;
import com.gotracrat.managelocation.utils.GoTracratConstants;
import com.gotracrat.managelocation.utils.TimeFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReportsServiceImpl implements ReportsService {

    @Autowired
    private VwSpareMotorRepository vwSpareMotorRepository;

    @Autowired
    private ReportsDAO reportsDao;

    private String itemID = "ItemID";

    private String typeName = "TypeName";

    private String frame = "Frame";

    @Override
    public VwSpareMotor getSpareMotor(Integer itemId) {
        return vwSpareMotorRepository.findById(itemId).orElseThrow(() ->
                new ResourceNotFoundException(GoTracratConstants.ITEM_NOT_FOUND));
    }

    @Override
    public ItemServicesResource getServiceReports(ServiceReportRequest serviceReportRequest) {
        List<ServicesDTO> serviceReport = new ArrayList<>();
        if (serviceReportRequest.getTimeSpan().equalsIgnoreCase(TimeFrame.MONTH.getTimeType())) {
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.plusMonths(1);
            serviceReport = reportsDao.getServiceReports(serviceReportRequest.getCompanyId(), startDate.toString(),
                    endDate.toString());
        } else if (serviceReportRequest.getTimeSpan().equalsIgnoreCase(TimeFrame.QUARTER.getTimeType())) {
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.plusMonths(3);
            serviceReport = reportsDao.getServiceReports(serviceReportRequest.getCompanyId(), startDate.toString(),
                    endDate.toString());
        } else if (serviceReportRequest.getTimeSpan().equalsIgnoreCase(TimeFrame.RANGE.getTimeType())) {
            serviceReport = reportsDao.getServiceReports(serviceReportRequest.getCompanyId(),
                    serviceReportRequest.getStartDate(), serviceReportRequest.getEndDate());
        }
        ItemServicesResource itemServicesResource = new ItemServicesResource();
        List<ServicesDTO> completedServices = serviceReport.stream().filter(ServicesDTO::getComplete)
                .collect(Collectors.toList());
        List<ServicesDTO> inCompletedServices = serviceReport.stream()
                .filter(inCompleted -> !inCompleted.getComplete()).collect(Collectors.toList());
        itemServicesResource.setCompletedServices(completedServices);
        itemServicesResource.setInCompletedServices(inCompletedServices);
        return itemServicesResource;

    }

    @Override
    public InserviceVsSpareResponseDTO getInServiceVsSpareReports(InServiceVsSpareRequest inServiceVsSpareRequest) {
        Map<String, Object> report = reportsDao.getInServiceVsSpareReports(inServiceVsSpareRequest);
        if (report != null && !report.isEmpty()) {
            InserviceVsSpareResponseDTO finalReport = new InserviceVsSpareResponseDTO();
            finalReport.setInServiceAndSpareMotors(getInServiceAndMatchedSpares(report));
            finalReport.setUnmatchedServiceMotors(getUnMatchedInServiceMotors(report));
            finalReport.setUnmatchedSpareMotors(getUnUsedSpareMotors(report));
            return finalReport;
        }
        return null;
    }


    private List<InServiceMotorsDTO> getUnUsedSpareMotors(Map<String, Object> report) {
        List<Map<String, Object>> itemList = (List) report.get("#result-set-3");
        Stream<Map<String, Object>> itemMapStream = itemList.stream().map(Map.class::cast);
        return itemMapStream.map(itemMap -> InServiceMotorsDTO.builder()
                .itemId((Integer) itemMap.get(itemID))
                .typeName((String) itemMap.get(typeName))
                .tag((String) itemMap.get("Tag"))
                .hp((String) itemMap.get("Hp"))
                .rpm((String) itemMap.get("Rpm"))
                .frame((String) itemMap.get(frame)).build()
        ).collect(Collectors.toList());
    }

    private List<InServiceMotorsDTO> getUnMatchedInServiceMotors(Map<String, Object> report) {
        List<Map<String, Object>> itemList = (List) report.get("#result-set-2");
        Stream<Map<String, Object>> itemMapStream = itemList.stream().map(Map.class::cast);
        return itemMapStream.map(itemMap -> InServiceMotorsDTO.builder()
                        .itemId((Integer) itemMap.get(itemID))
                        .typeName((String) itemMap.get(typeName))
                        .tag((String) itemMap.get("Tag"))
                        .hp((String) itemMap.get("Hp"))
                        .rpm((String) itemMap.get("Rpm"))
                        .frame((String) itemMap.get(frame)).build())
                .collect(Collectors.toList());
    }

    private List<InServiceVsSpareDTO> getInServiceAndMatchedSpares(Map<String, Object> report) {
        List<Map<String, Object>> itemList = (List) report.get("#result-set-1");
        Stream<Map<String, Object>> itemMapStream = itemList.stream().map(Map.class::cast);
        return itemMapStream.map(itemMap -> InServiceVsSpareDTO.builder()
                .itemId((Integer) itemMap.get(itemID))
                .typeName((String) itemMap.get(typeName))
                .tag((String) itemMap.get("Tag"))
                .hp((String) itemMap.get("Hp"))
                .rpm((String) itemMap.get("Rpm"))
                .frame((String) itemMap.get(frame))
                .spares(buildSparesFromXml((String) itemMap.get("Spares"))).build()
        ).collect(Collectors.toList());
    }

    private List<SparesDTO> buildSparesFromXml(String sparesXml) {
        if (sparesXml != null) {
            JAXBContext jaxbContext;
            try {
                jaxbContext = JAXBContext.newInstance(SparesXml.class);
                Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
                SparesXml spares = (SparesXml) jaxbUnMarshaller.unmarshal(new StringReader(sparesXml));
                return spares.getSpares();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();

    }


}