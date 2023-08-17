package com.gotracrat.managelocation.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicesDTO {
	
	private Integer itemId;
	private String tag;
	private String typeName;
	private Date serviceDate;
	private String serviceCause;
	private Boolean complete;
	private String completedBy;
	private Date actualCompletion;
}
