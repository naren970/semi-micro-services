package com.gotracrat.managelocation.resource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Hateoas resource associated with Item.
 *
 * @author prabhakar
 */
@Setter
@Getter
public class ItemResource {

	private Integer itemid;
	@NotNull(message = "Location must not be empty")
	private Integer locationid;
	private Integer companyid;
	private String companyName;
	@NotBlank(message = "Tag must not be empty")
	private String tag;
	private String name;
	private String description;
	@NotNull(message = "Status must not be empty")
	private Integer statusid;
	private String statusname;
	private Integer warrantytypeid;
	private Date warrantyexpiration;
	private String WarrantyExpirationStr;
	private String serialnumber;
	private String modelnumber;
	private Integer meantimebetweenservice;
	private Date inserviceon;
	private String lastmodifiedby;
	private Boolean isinrepair;
	private BigDecimal desiredspareratio;
	private Integer manufacturerid;
	private Integer repairqual;
	private BigDecimal purchaseprice;
	private Long daysinservice;
	private Date purchasedate;
	private Integer defaultimageattachmentid;
	@NotNull(message = "Type must not be empty")
	private Integer typeId;
	private Boolean isstale;
	private String locationName;
	private String locationPath;
	private int entityTypeId;
	private String roleid;
	private String userid;
	private String roleName;
	private Date updatedDate;
	private Date createdDate;
	private String typeName;
	private Integer itemRank;
	private Date serviceDate;
	private Integer serviceDuration;
	private String serviceCause;
	private List<AttributeName> attributeNameList;
	private List<AttachmentResource> attachmentList;
	private List<AttributeValueResource> attributevalues;

	public ItemResource() {
		// use this constructor for serialization
	}

	public ItemResource(Integer itemid, Integer locationid, String tag, String typeName, Integer statusid, Integer typeId, Integer warrantytypeid,
						List<AttributeValueResource> attributevalues, List<AttachmentResource> attachmentList) {
		super();
		this.itemid = itemid;
		this.locationid = locationid;
		this.tag = tag;
		this.typeName = typeName;
		this.statusid = statusid;
		this.typeId = typeId;
		this.warrantytypeid=warrantytypeid;
		this.attributevalues = attributevalues;
		this.attachmentList = attachmentList;
	}

}
