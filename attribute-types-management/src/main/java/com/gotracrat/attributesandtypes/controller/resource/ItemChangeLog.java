package com.gotracrat.attributesandtypes.controller.resource;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ItemChange")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemChangeLog {

	@XmlAttribute(name = "ItemId")
	private Integer itemId;
	@XmlAttribute(name = "Previous_LocationId")
	private Integer previous_LocationId;
	@XmlAttribute(name = "LocationId")
	private Integer locationId;

	@XmlAttribute(name = "Location_Name")
	private String location_Name;
	@XmlAttribute(name = "Previous_LocationName")
	private String previous_LocationName;
	
	@XmlAttribute(name = "Previous_Tag")
	private String previous_Tag;
	@XmlAttribute(name = "Tag")
	private String tag;
	@XmlAttribute(name = "Previous_Name")
	private String previous_Name;

	@XmlAttribute(name = "Name")
	private String name;
	@XmlAttribute(name = "Previous_Description")
	private String previous_Description;
	@XmlAttribute(name = "Description")
	private String description;

	
	
	@XmlAttribute(name = "Status_Name")
	private String status_Name;
	@XmlAttribute(name = "PreviousStatus_Name")
	private String previousStatus_Name;
	
	
	@XmlAttribute(name = "Previous_StatusId")
	private Integer previous_StatusId;
	@XmlAttribute(name = "StatusId")
	private Integer statusId;
	@XmlAttribute(name = "Previous_WarrantyTypeId")
	private Integer previous_WarrantyTypeId;

	@XmlAttribute(name = "WarrantyTypeId")
	private Integer warrantyTypeId;
	@XmlAttribute(name = "Previous_WarrantyExpiration")
	private Date previous_WarrantyExpiration;
	@XmlAttribute(name = "WarrantyExpiration")
	private Date warrantyExpiration;

	@XmlAttribute(name = "Previous_SerialNumber")
	private String previous_SerialNumber;
	@XmlAttribute(name = "SerialNumber")
	private String serialNumber;
	@XmlAttribute(name = "Previous_ModelNumber")
	private String previous_ModelNumber;

	@XmlAttribute(name = "ModelNumber")
	private String modelNumber;
	@XmlAttribute(name = "Previous_MeanTimeBetweenService")
	private Integer previous_MeanTimeBetweenService;
	@XmlAttribute(name = "MeanTimeBetweenService")
	private Integer meanTimeBetweenService;

	@XmlElement(name = "Attribute")
	private List<AttributeChangeLog> attributeChangeLog;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getPrevious_LocationId() {
		return previous_LocationId;
	}

	public void setPrevious_LocationId(Integer previous_LocationId) {
		this.previous_LocationId = previous_LocationId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getPrevious_Tag() {
		return previous_Tag;
	}

	public void setPrevious_Tag(String previous_Tag) {
		this.previous_Tag = previous_Tag;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getPrevious_Name() {
		return previous_Name;
	}

	public void setPrevious_Name(String previous_Name) {
		this.previous_Name = previous_Name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrevious_Description() {
		return previous_Description;
	}

	public void setPrevious_Description(String previous_Description) {
		this.previous_Description = previous_Description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrevious_StatusId() {
		return previous_StatusId;
	}

	public void setPrevious_StatusId(Integer previous_StatusId) {
		this.previous_StatusId = previous_StatusId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getPrevious_WarrantyTypeId() {
		return previous_WarrantyTypeId;
	}

	public void setPrevious_WarrantyTypeId(Integer previous_WarrantyTypeId) {
		this.previous_WarrantyTypeId = previous_WarrantyTypeId;
	}

	public Integer getWarrantyTypeId() {
		return warrantyTypeId;
	}

	public void setWarrantyTypeId(Integer warrantyTypeId) {
		this.warrantyTypeId = warrantyTypeId;
	}

	public Date getPrevious_WarrantyExpiration() {
		return previous_WarrantyExpiration;
	}

	public void setPrevious_WarrantyExpiration(Date previous_WarrantyExpiration) {
		this.previous_WarrantyExpiration = previous_WarrantyExpiration;
	}

	public Date getWarrantyExpiration() {
		return warrantyExpiration;
	}

	public void setWarrantyExpiration(Date warrantyExpiration) {
		this.warrantyExpiration = warrantyExpiration;
	}

	public String getPrevious_SerialNumber() {
		return previous_SerialNumber;
	}

	public void setPrevious_SerialNumber(String previous_SerialNumber) {
		this.previous_SerialNumber = previous_SerialNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getPrevious_ModelNumber() {
		return previous_ModelNumber;
	}

	public void setPrevious_ModelNumber(String previous_ModelNumber) {
		this.previous_ModelNumber = previous_ModelNumber;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public Integer getPrevious_MeanTimeBetweenService() {
		return previous_MeanTimeBetweenService;
	}

	public void setPrevious_MeanTimeBetweenService(Integer previous_MeanTimeBetweenService) {
		this.previous_MeanTimeBetweenService = previous_MeanTimeBetweenService;
	}

	public Integer getMeanTimeBetweenService() {
		return meanTimeBetweenService;
	}

	public void setMeanTimeBetweenService(Integer meanTimeBetweenService) {
		this.meanTimeBetweenService = meanTimeBetweenService;
	}

	public List<AttributeChangeLog> getAttributeChangeLog() {
		return attributeChangeLog;
	}

	public void setAttributeChangeLog(List<AttributeChangeLog> attributeChangeLog) {
		this.attributeChangeLog = attributeChangeLog;
	}
	public String getLocation_Name() {
		return location_Name;
	}

	public void setLocation_Name(String location_Name) {
		this.location_Name = location_Name;
	}

	public String getPrevious_LocationName() {
		return previous_LocationName;
	}

	public void setPrevious_LocationName(String previous_LocationName) {
		this.previous_LocationName = previous_LocationName;
	}

	public String getStatus_Name() {
		return status_Name;
	}

	public void setStatus_Name(String status_Name) {
		this.status_Name = status_Name;
	}

	public String getPreviousStatus_Name() {
		return previousStatus_Name;
	}

	public void setPreviousStatus_Name(String previousStatus_Name) {
		this.previousStatus_Name = previousStatus_Name;
	}

}
