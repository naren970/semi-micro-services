package com.gotracrat.managelocation.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceReportRequest {	
	private Integer companyId;
	private String timeSpan;
	private String startDate;
	private String endDate;
}
