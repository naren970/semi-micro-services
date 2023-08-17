package com.gotracrat.managelocation.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseTagDTO {

	
	private Date actualCompletion;
	private String repairVendorName;
	
	public WarehouseTagDTO(Date actualCompletion, String repairVendorName) {
		super();
		this.actualCompletion = actualCompletion;
		this.repairVendorName = repairVendorName;
	}

	public WarehouseTagDTO() {
		
	}

	
	
}
