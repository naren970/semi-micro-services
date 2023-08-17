package com.gotracrat.managelocation.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gotracrat.managelocation.dto.RepairAttachmentDTO;
import com.gotracrat.managelocation.dto.RepairLogAttachmentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecentRepairResource implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer repairLogId;
	private Integer itemId;
	private String tag;
	private String typeName;
	private String poNumber;
	private String jobNumber;
	private String location;
	private String vendor;
	private BigDecimal repairCost;
	private String actualCompletion;
	private Integer rank;
	private Boolean complete;
	private Date actualCompletionDate;
	private Date dateAdded;
	private String failureType;
	private String failureCause;
	@JsonIgnore
	private String attachmentXml;
	private List<AttachmentNameXml> attachmentListFromXml;
	private List<RepairLogAttachmentDTO> attachmentList;

}
