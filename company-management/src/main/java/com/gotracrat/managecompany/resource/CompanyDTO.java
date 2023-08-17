package com.gotracrat.managecompany.resource;

import lombok.Getter;
import lombok.Setter;
/*
 *DTO for drop down companies 
 *
 *@author anudeep
 */
@Setter
@Getter
public class CompanyDTO {
	
	private Integer companyid;
	private String name;
	
	public CompanyDTO(Integer companyid, String name) {
		super();
		this.companyid = companyid;
		this.name = name;
	}	
}
