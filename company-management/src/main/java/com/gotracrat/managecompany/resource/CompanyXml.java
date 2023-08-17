package com.gotracrat.managecompany.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyXml {

	@XmlAttribute(name = "ID")
	private  Integer companyId;
	
	
	@XmlAttribute(name = "Name")
	private  String name;
	
	@XmlAttribute(name = "Address1")
	private  String address1;
	
	
	@XmlAttribute(name = "Address2")
	private String address2;
	
	@XmlAttribute(name = "StatusID")
	private  Integer StatusID;
}