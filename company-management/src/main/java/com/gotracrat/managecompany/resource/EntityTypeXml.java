package com.gotracrat.managecompany.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;


@XmlAccessorType(XmlAccessType.FIELD)
public class EntityTypeXml {

	
	
	@XmlAttribute(name = "CompanyID")
	private Integer companyID;
	
	
	@XmlAttribute(name = "TypeID")
	private Integer TypeID;


	public Integer getCompanyID() {
		return companyID;
	}


	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}


	public Integer getTypeID() {
		return TypeID;
	}


	public void setTypeID(Integer typeID) {
		TypeID = typeID;
	}
	
	
	
	
}
