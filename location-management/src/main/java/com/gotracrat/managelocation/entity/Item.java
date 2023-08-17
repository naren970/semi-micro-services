package com.gotracrat.managelocation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Persistent class for Item entity stored in table Item. This class is a
 * "record entity" without JPA links.
 *
 * @author prabhakar
 */

@Getter
@Setter
@Entity
@Table(name = "Item", schema = "dbo")
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ItemID", nullable = false)
	private Integer itemid;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LocationID", nullable = false)
	@JsonBackReference
	private Location location;
	@Column(name = "Tag", nullable = false)
	private String tag;
	@Column(name = "Name", length = 100)
	private String name;
	@Column(name = "Description", length = 2000)
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StatusID", nullable = false)
	@JsonBackReference
	private Status status;
	@ManyToOne(optional = true,fetch = FetchType.LAZY)
	@JoinColumn(name = "WarrantyTypeID")
	@JsonBackReference
	@NotFound(action = NotFoundAction.IGNORE)
	private WarrantyType warrantytype;
	@Temporal(value =TemporalType.DATE)
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
	@Column(name = "itemTypeId")
	private Integer itemTypeId;
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "UpdatedDate")
	private Date updatedDate;
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "CreatedDate")
	private Date createdDate;
	@Transient
	private String roleid ;
	@Transient
	private String userid ;
	@Transient
	private Integer companyId;
	@Transient
	private String typeName;

	public Item() {
		super();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(itemid);
		sb.append(location);
		sb.append("|");
		sb.append(tag);
		sb.append("|");
		sb.append(name);
		sb.append("|");
		sb.append(description);
		sb.append("|");
		sb.append(status);
		sb.append("|");
		sb.append(warrantytype);
		sb.append("|");
		sb.append(warrantyexpiration);
		sb.append("|");
		sb.append(serialnumber);
		sb.append("|");
		sb.append(modelnumber);
		sb.append("|");
		sb.append(meantimebetweenservice);
		sb.append("|");
		sb.append(inserviceon);
		sb.append("|");
		sb.append(lastmodifiedby);
		sb.append("|");
		sb.append(isinrepair);
		sb.append("|");
		sb.append(desiredspareratio);
		sb.append("|");
		sb.append(manufacturerid);
		sb.append("|");
		sb.append(repairqual);
		sb.append("|");
		sb.append(purchaseprice);
		sb.append("|");
		sb.append(purchasedate);
		sb.append("|");
		sb.append(defaultimageattachmentid);
		sb.append("|");
		sb.append(updatedDate);
		sb.append("|");
		sb.append(isstale);
		return sb.toString();
	}

}