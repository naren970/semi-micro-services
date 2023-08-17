package com.gotracrat.managelocation.resource;

import java.util.Date;

/**
 * Hateoas resource associated with RepairLogMapping.
 * 
 * @author Manikanta
 */
public class RepairLogMappingResource {

	private Integer repairLogMappingId;
	private Integer repairLogId;
	private String failuretype;
	private String failurecause;
	private Date addedDate;
	private Date updatedDate;
	private Boolean isActive;

	public Integer getRepairLogMappingId() {
		return repairLogMappingId;
	}

	public void setRepairLogMappingId(Integer repairLogMappingId) {
		this.repairLogMappingId = repairLogMappingId;
	}

	public Integer getRepairLogId() {
		return repairLogId;
	}

	public void setRepairLogId(Integer repairLogId) {
		this.repairLogId = repairLogId;
	}

	public String getFailuretype() {
		return failuretype;
	}

	public void setFailuretype(String failuretype) {
		this.failuretype = failuretype;
	}

	public String getFailurecause() {
		return failurecause;
	}

	public void setFailurecause(String failurecause) {
		this.failurecause = failurecause;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
