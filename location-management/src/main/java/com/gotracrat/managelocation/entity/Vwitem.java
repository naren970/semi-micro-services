package com.gotracrat.managelocation.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "VwItem", schema = "dbo")
public class Vwitem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ItemID", nullable = false)
	private Integer itemid;

	@Column(name = "LocationID", nullable = false)
	private Integer LocationID;

	@Column(name = "Tag", nullable = false)
	private String tag;
	@Column(name = "Name", length = 100)
	private String name;
	@Column(name = "Description", length = 2000)
	private String description;

	@Column(name = "StatusID")
	private Integer StatusID;

	
	@Column(name = "WarrantyTypeID")
	private Integer WarrantyTypeID;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "WarrantyExpiration")
	private Date warrantyexpiration;
	@Column(name = "SerialNumber", length = 100)
	private String serialnumber;
	@Column(name = "ModelNumber", length = 100)
	private String modelnumber;
	@Column(name = "MeanTimeBetweenService")
	private Integer meantimebetweenservice;
	@Temporal(value = TemporalType.DATE)
	@Column(name = "InServiceOn")
	private Date inserviceon;
	@Column(name = "LastModifiedBy", nullable = false)
	private String lastmodifiedby;
	@Column(name = "IsInRepair", nullable = false)
	private Boolean isinrepair;
	@Column(name = "DesiredSpareRatio")
	private BigDecimal desiredspareratio;
	@Column(name = "ManufacturerID")
	private Integer manufacturerid;
	@Column(name = "RepairQual", nullable = false)
	private Integer repairqual;
	@Column(name = "PurchasePrice")
	private BigDecimal purchaseprice;
	@Temporal(value = TemporalType.DATE)
	@Column(name = "PurchaseDate")
	private Date purchasedate;
	@Column(name = "DefaultImageAttachmentID")
	private Integer defaultimageattachmentid;
	@Column(name = "IsStale", nullable = false)
	private Boolean isstale;

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

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "CreatedDate")
	private Date createdDate;

	@Column(name = "CriticalFlag")
	private Boolean criticalFlag;

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getWarrantyexpiration() {
		return warrantyexpiration;
	}

	public void setWarrantyexpiration(Date warrantyexpiration) {
		this.warrantyexpiration = warrantyexpiration;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getModelnumber() {
		return modelnumber;
	}

	public void setModelnumber(String modelnumber) {
		this.modelnumber = modelnumber;
	}

	public Integer getMeantimebetweenservice() {
		return meantimebetweenservice;
	}

	public void setMeantimebetweenservice(Integer meantimebetweenservice) {
		this.meantimebetweenservice = meantimebetweenservice;
	}

	public Date getInserviceon() {
		return inserviceon;
	}

	public void setInserviceon(Date inserviceon) {
		this.inserviceon = inserviceon;
	}

	public String getLastmodifiedby() {
		return lastmodifiedby;
	}

	public void setLastmodifiedby(String lastmodifiedby) {
		this.lastmodifiedby = lastmodifiedby;
	}

	public Boolean getIsinrepair() {
		return isinrepair;
	}

	public void setIsinrepair(Boolean isinrepair) {
		this.isinrepair = isinrepair;
	}

	public BigDecimal getDesiredspareratio() {
		return desiredspareratio;
	}

	public void setDesiredspareratio(BigDecimal desiredspareratio) {
		this.desiredspareratio = desiredspareratio;
	}

	public Integer getManufacturerid() {
		return manufacturerid;
	}

	public void setManufacturerid(Integer manufacturerid) {
		this.manufacturerid = manufacturerid;
	}

	public Integer getRepairqual() {
		return repairqual;
	}

	public void setRepairqual(Integer repairqual) {
		this.repairqual = repairqual;
	}

	public BigDecimal getPurchaseprice() {
		return purchaseprice;
	}

	public void setPurchaseprice(BigDecimal purchaseprice) {
		this.purchaseprice = purchaseprice;
	}

	public Date getPurchasedate() {
		return purchasedate;
	}

	public void setPurchasedate(Date purchasedate) {
		this.purchasedate = purchasedate;
	}

	public Integer getDefaultimageattachmentid() {
		return defaultimageattachmentid;
	}

	public void setDefaultimageattachmentid(Integer defaultimageattachmentid) {
		this.defaultimageattachmentid = defaultimageattachmentid;
	}

	public Boolean getIsstale() {
		return isstale;
	}

	public void setIsstale(Boolean isstale) {
		this.isstale = isstale;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getLocationPath() {
		return locationPath;
	}

	public void setLocationPath(String locationPath) {
		this.locationPath = locationPath;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getCriticalFlag() {
		return criticalFlag;
	}

	public void setCriticalFlag(Boolean criticalFlag) {
		this.criticalFlag = criticalFlag;
	}

	public Integer getLocationID() {
		return LocationID;
	}

	public void setLocationID(Integer locationID) {
		LocationID = locationID;
	}

	public Integer getStatusID() {
		return StatusID;
	}

	public void setStatusID(Integer statusID) {
		StatusID = statusID;
	}

	public Integer getWarrantyTypeID() {
		return WarrantyTypeID;
	}

	public void setWarrantyTypeID(Integer warrantyTypeID) {
		WarrantyTypeID = warrantyTypeID;
	}

}
