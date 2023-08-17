package com.gotracrat.managelocation.resource;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the TransferLog database table.
 * 
 */
@Getter
@Setter
public class TransferLogResource implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer transferLogID;
	private Integer daysinOldStatus;
	private String details;
	private String fromLocation;
	private Integer fromLocationID;
	private Integer itemID;
	private String jobNumber;
	private Integer newStatus;
	private String newStatusName;
	private String oldStatus;
	private String PONumber;
	private String shippingNumber;
	private String toLocation;
	private Integer toLocationID;
	private String trackingNumber;
	private Date transferDate;
	private String transfeDate;
	private Integer companyID;
	private Integer statusID;
	private Long daysbacktracked;
	private String transferredBy;
}