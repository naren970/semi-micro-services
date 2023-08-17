package com.gotracrat.managecompany.resource.resource;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class TypeXml {

	
	@XmlAttribute(name = "Name")
	private String name;
	
	@XmlAttribute(name = "ID")
	private Integer typeId;
	
	@XmlAttribute(name = "EntityTypeID")
	private Integer entityTypeId;
	
	@XmlElement(name = "Type")
	private List<TypeXml> childTypeList;
	
	
	@XmlElement(name = "Attribute")
	private List<AttributeXMl> attributeList;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getTypeId() {
		return typeId;
	}


	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}


	public Integer getEntityTypeId() {
		return entityTypeId;
	}


	public void setEntityTypeId(Integer entityTypeId) {
		this.entityTypeId = entityTypeId;
	}


	public List<TypeXml> getChildTypeList() {
		return childTypeList;
	}


	public void setChildTypeList(List<TypeXml> childTypeList) {
		this.childTypeList = childTypeList;
	}


	public List<AttributeXMl> getAttributeList() {
		return attributeList;
	}


	public void setAttributeList(List<AttributeXMl> attributeList) {
		this.attributeList = attributeList;
	}
	
	
	
	
}
