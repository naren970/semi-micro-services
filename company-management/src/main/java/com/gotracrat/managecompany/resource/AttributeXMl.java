package com.gotracrat.managecompany.resource;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;


@XmlAccessorType(XmlAccessType.FIELD)
public class AttributeXMl {

	
	@XmlAttribute(name = "ID")
	private  Integer attributeNameId;
	
	@XmlAttribute(name = "Name")
	private String name;
	
	@XmlAttribute(name = "TypeID")
	private  Integer attributeTypeId;;
	
	@XmlAttribute(name = "IsRequired")
	private  Boolean isRequired;
	
	@XmlAttribute(name = "ToolTip")
	private  String tooltip;
	 
	@XmlAttribute(name = "SearchTypeID")
	private Integer attributeSearchTypeId;
	
	@XmlAttribute(name = "SearchModifier")
	private String searchModifier;
	
	@XmlAttribute(name = "DisplayOrder")
	private Integer displayOrder;
	
	@XmlAttribute(name = "IsRequiredForMatch")
	private Boolean isRequiredForMatch;
	
	@XmlAttribute(name = "IsManufacturer")
	private Boolean isManufacturer;

	
	
	

	
	
	
	
	
	
	public Integer getAttributeNameId() {
		return attributeNameId;
	}

	public void setAttributeNameId(Integer attributeNameId) {
		this.attributeNameId = attributeNameId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAttributeTypeId() {
		return attributeTypeId;
	}

	public void setAttributeTypeId(Integer attributeTypeId) {
		this.attributeTypeId = attributeTypeId;
	}

	public Boolean getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

	public Integer getAttributeSearchTypeId() {
		return attributeSearchTypeId;
	}

	public void setAttributeSearchTypeId(Integer attributeSearchTypeId) {
		this.attributeSearchTypeId = attributeSearchTypeId;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	
	public String getSearchModifier() {
		return searchModifier;
	}

	public void setSearchModifier(String searchModifier) {
		this.searchModifier = searchModifier;
	}

	public Boolean getIsRequiredForMatch() {
		return isRequiredForMatch;
	}

	public void setIsRequiredForMatch(Boolean isRequiredForMatch) {
		this.isRequiredForMatch = isRequiredForMatch;
	}

	public Boolean getIsManufacturer() {
		return isManufacturer;
	}

	public void setIsManufacturer(Boolean isManufacturer) {
		this.isManufacturer = isManufacturer;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	
	

}
