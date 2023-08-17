package com.gotracrat.managecompany.resource.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;


@XmlAccessorType(XmlAccessType.FIELD)
public class StatusXml {


	@XmlAttribute(name = "StatusID")
	private Integer statusId;
	
	@XmlAttribute(name = "EntityTypeID")
	private Integer entityTypeId;
	
	@XmlAttribute(name = "Status")
	private String status;
	
	
	@XmlAttribute(name = "CompanyID")
	private Integer companyId;
	
	@XmlAttribute(name = "InService")
	private Boolean inService;
	
	@XmlAttribute(name = "UnderRepair")
	private Boolean underRepair;
	
	@XmlAttribute(name = "Spare")
	private Boolean spare;
	
	@XmlAttribute(name = "Destroyed")
	private Boolean destroyed;
	
	
	public StatusXml() {
		super();
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getEntityTypeId() {
		return entityTypeId;
	}

	public void setEntityTypeId(Integer entityTypeId) {
		this.entityTypeId = entityTypeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Boolean getInService() {
		return inService;
	}

	public void setInService(Boolean inService) {
		this.inService = inService;
	}

	public Boolean getUnderRepair() {
		return underRepair;
	}

	public void setUnderRepair(Boolean underRepair) {
		this.underRepair = underRepair;
	}

	public Boolean getSpare() {
		return spare;
	}

	public void setSpare(Boolean spare) {
		this.spare = spare;
	}

	public Boolean getDestroyed() {
		return destroyed;
	}

	public void setDestroyed(Boolean destroyed) {
		this.destroyed = destroyed;
	}

	
	
	

}
