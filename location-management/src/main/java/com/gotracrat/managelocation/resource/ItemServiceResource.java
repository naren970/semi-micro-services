package com.gotracrat.managelocation.resource;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemServiceResource {
	
	private Integer serviceId;
	private Integer itemId;
	private Date serviceDate;
	private String serviceCause;
	private Boolean complete;
	private Date actualCompletion;
	private String completedBy;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;

}
