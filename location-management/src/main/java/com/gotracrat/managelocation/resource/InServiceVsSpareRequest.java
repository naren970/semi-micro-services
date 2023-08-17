package com.gotracrat.managelocation.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InServiceVsSpareRequest {
	
	private int companyId;
	private String hp;
	private String rpm;
	private String frame;
	

}
