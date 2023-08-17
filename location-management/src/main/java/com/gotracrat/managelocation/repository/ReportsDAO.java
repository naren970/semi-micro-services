package com.gotracrat.managelocation.repository;

import java.util.List;
import java.util.Map;

import com.gotracrat.managelocation.dto.ServicesDTO;
import com.gotracrat.managelocation.resource.InServiceVsSpareRequest;

public interface ReportsDAO {

	List<ServicesDTO> getServiceReports(Integer companyId, String startDate, String endDate);

	Map<String, Object> getInServiceVsSpareReports(InServiceVsSpareRequest inServiceVsSpareRequest);

}
