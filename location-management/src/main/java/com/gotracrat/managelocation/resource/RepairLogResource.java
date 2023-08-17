package com.gotracrat.managelocation.resource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.gotracrat.managelocation.dto.RepairLogAttachmentDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * Hateoas resource associated with Repairlog.
 * 
 * @author Prabhakar
 * @since 2018-11-29
 */
@Getter
@Setter
public class RepairLogResource {

	private Integer repairlogid;
	private Integer itemid;
	private String itemtype;
	private Integer typeId;
	private String rfqnumber;
	private String ponumber;
	private String repairvendornumber;
	private String jobnumber;
	private String repairjobstatus;
	private Date dateinitiated;
	private Date dateacknowledged;
	private Date estimatedcompletion;
	private Date actualcompletion;
	private String dateInitiated;
	private String dateAcknowledged;
	private String estimatedCompletion;
	private String actualCompletion;
	private BigDecimal repaircost;
	private Boolean iswarranty;
	private Boolean isactive;
	private String warrantytype;
	private Integer warrantytypeid;
	private String failuretype;
	private String failurecause;
	private Date failuredate;
	private Date warrantyexpiration;
	private String failureDate;
	private String tag;
	private String repairVendorName;
	private String repairLocationName;
    private String lastmodifiedby;
	private Integer transferlogid;
	private String repairnotes;
	private Integer repairlocationid;
	private Integer repaircompanyid;
	private String title;
	private Boolean complete;
	private String completedby;
	private List<RepairLogAttachmentDTO> attachmentList;
	private String userName;
	private List<RepairLogMappingResource> secondaryTypeAndCauses;
	private Integer companyId;

	
	public RepairLogResource() {
	}
}
	
	
	