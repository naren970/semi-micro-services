package com.gotracrat.managelocation.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gotracrat.managelocation.resource.AttributeNameXml;

import lombok.Data;

@Data
@Entity
@Table(name = "vwGetItem", schema = "dbo")
public class VwGetItem implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ItemID", nullable = false)
	private Integer itemId;
	@Column(name = "LocationID")
	private Integer locationId;
	@Column(name = "Tag")
	private String tag;
	@Column(name = "Name")
	private String name;
	@Column(name = "Description")
	private String description;
	@Column(name = "StatusID")
	private Integer statusId;
	@Column(name = "WarrantyTypeID")
	private Integer warrantyTypeId;
	@Column(name = "WarrantyExpiration")
	private Date warrantyExpiration;
	@Column(name = "MeanTimeBetweenService")
	private Integer meanTimeBetweenService;
	@Column(name = "InServiceOn")
	private Date inServiceOn;
	@Column(name = "LastModifiedBy")
	private String lastModifiedBy;
	@Column(name = "DesiredSpareRatio")
	private BigDecimal desiredSpareRatio;
	@Column(name = "TypeID")
	private Integer typeId;
	@Column(name = "TypeName")
	private String typeName;
	@Column(name = "LocationPath")
	private String locationPath;
	@Column(name = "LocationName")
	private String locationName;
	@Column(name = "CompanyID")
	private Integer companyId;
	@Column(name = "PurchasePrice")
	private BigDecimal purchasePrice;
	@Column(name = "PurchaseDate")
	private Date purchaseDate;
	@Column(name = "DefaultImageAttachmentID")
	private Integer defaultImageAttachmentId;
	@Column(name = "Status")
	private String status;
	@Column(name="ReplacedTag")
	private String replacedTag;
	@Column(name="GroupNumber")
	private Integer groupNumber;
	@Column(name="AddReasonCode")
	private String addReasonCode;
	@Column(name="ReplacedByTag")
	private String replacedByTag;
	@Column(name="ReservedForDeptNumber")
	private Integer reservedForDeptNumber;
	@Column(name="ReservedOnlyFor")
	private Integer reservedOnlyFor;
	@Column(name="[FILE]")
	private Integer file;
	@Column(name="QCFail")
	private Integer qcFail;
	@Column(name="Critical")
	private Integer critical;
	@Column(name="CriticalLastTestDate")
	private Date criticalLastTestDate;
	@Column(name="ImportedNotReviewed")
	private Integer importedNotReviewed;
	@Column(name="InventoriedDate")
	private Date inventoriedDate;
	@Column(name="DispositionEmployeeID")
	private Integer dispositionEmployeeID;
	@Column(name="CreateDate")
	private Date createDate;
	@Column(name="UpdatedDate")
	private Date updatedDate;
	@Column(name="ScrapDate")
	private Date scrapDate;
	@Column(name="ScrapLocation")
	private String scrapLocation;
	@Column(name="ScrapperName")
	private String scrapperName;
	@Column(name="ScrapValue")
	private BigDecimal scrapValue;
	@Column(name="Comments")
	private String comments;
	@JsonIgnore
	@Column(name = "AttributesXML")
	private String attributesXml;
	@Transient
	private Long daysInService;
	@Transient
	private Date serviceDate;
	@Transient
	private Long daysDueForNextService;
	@Transient
	private List<AttributeNameXml> attributeValues;


	public VwGetItem(Integer itemId, Integer locationId, String tag, String name, Integer statusId, Date inServiceOn,
					 String lastModifiedBy, Integer typeId, String attributesXml) {
		super();
		this.itemId = itemId;
		this.locationId = locationId;
		this.tag = tag;
		this.name = name;
		this.statusId = statusId;
		this.inServiceOn = inServiceOn;
		this.lastModifiedBy = lastModifiedBy;
		this.typeId = typeId;
		this.attributesXml = attributesXml;
	}

	public VwGetItem() {
		super();
	}

}
