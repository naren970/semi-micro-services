package com.gotracrat.managecompany.resource;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "CompanyExport")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyExport {

	
	
	
	@XmlElement(name = "Status")
	private List<StatusXml> statusList;
	
	
	@XmlElement(name = "Company")
	private CompanyXml companyXml;
	
	@XmlElement(name = "ETT")
	private EntityTypeXml entityTypeXml;
	
	
	@XmlElement(name = "Type")
	private List<TypeXml> typeList;


	public List<StatusXml> getStatusList() {
		return statusList;
	}


	public void setStatusList(List<StatusXml> statusList) {
		this.statusList = statusList;
	}


	public CompanyXml getCompanyXml() {
		return companyXml;
	}


	public void setCompanyXml(CompanyXml companyXml) {
		this.companyXml = companyXml;
	}


	public EntityTypeXml getEntityTypeXml() {
		return entityTypeXml;
	}


	public void setEntityTypeXml(EntityTypeXml entityTypeXml) {
		this.entityTypeXml = entityTypeXml;
	}


	public List<TypeXml> getTypeList() {
		return typeList;
	}


	public void setTypeList(List<TypeXml> typeList) {
		this.typeList = typeList;
	}
	
	
	
	
}
