package com.gotracrat.managelocation.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllRepairsDTO {
	private Integer companyId;
	private String isOwnerAdmin;
	private String userId;
	private Integer statusFlag;
	private String timeFrame;
	private String startDate;
	private String endDate;
}



