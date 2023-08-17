package com.gotracrat.managelocation.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemServiceDTO {

	private Integer itemId;
	private Date serviceDate;
	private String serviceCause;
	
}
