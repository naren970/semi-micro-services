package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.dto.InserviceVsSpareResponseDTO;
import com.gotracrat.managelocation.entity.VwSpareMotor;
import com.gotracrat.managelocation.resource.InServiceVsSpareRequest;
import com.gotracrat.managelocation.resource.ItemServicesResource;
import com.gotracrat.managelocation.resource.ServiceReportRequest;

public interface ReportsService {


    VwSpareMotor getSpareMotor(Integer itemId);

    ItemServicesResource getServiceReports(ServiceReportRequest serviceReportRequest);

    InserviceVsSpareResponseDTO getInServiceVsSpareReports(InServiceVsSpareRequest inServiceVsSpareRequest);

}
