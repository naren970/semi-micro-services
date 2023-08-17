/*
 * Created on 2018-07-15 ( Date ISO 2018-07-15 - Time 13:09:30 )
 * prabhakar
*/
package com.gotracrat.managelocation.resource;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Hateoas resource associated with Location.
 * 
 * @author prabhakar
 */
@AllArgsConstructor
public class LocationResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer locationid;
	private CompanyResource company;
	private LocationResource parentLocation;
	private String name;
	private String description;
	private Integer statusid;
	private String lastmodifiedby;
	private BigDecimal desiredspareratio;
	private Boolean criticalflag;
	private Boolean isvendor;
	private CompanyResource vendorCompany;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String postalcode;
	private Integer companyID;
	private Integer parentID;
	private Integer vendorCompanyId;
	private List<LocationResource> parentLocationResourceList;

	private List<AttributeValueResource> attributevalues;
	
	private int entityTypeId; 
	
	private Integer typeId;

	// Constructor
	public LocationResource() {
		// Needed empty constructor for serialization
	}

	public Integer getLocationid() {
		return this.locationid;
	}

	public void setLocationid(Integer locationid) {
		this.locationid = locationid;
	}

	public CompanyResource getCompany() {
		return company;
	}

	public void setCompany(CompanyResource company) {
		this.company = company;
	}

	public LocationResource getParentLocation() {
		return parentLocation;
	}

	public void setParentLocation(LocationResource parentLocation) {
		this.parentLocation = parentLocation;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatusid() {
		return this.statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public String getLastmodifiedby() {
		return this.lastmodifiedby;
	}

	public void setLastmodifiedby(String lastmodifiedby) {
		this.lastmodifiedby = lastmodifiedby;
	}

	public BigDecimal getDesiredspareratio() {
		return this.desiredspareratio;
	}

	public void setDesiredspareratio(BigDecimal desiredspareratio) {
		this.desiredspareratio = desiredspareratio;
	}

	public Boolean getCriticalflag() {
		return this.criticalflag;
	}

	public void setCriticalflag(Boolean criticalflag) {
		this.criticalflag = criticalflag;
	}

	public Boolean getIsvendor() {
		return this.isvendor;
	}

	public void setIsvendor(Boolean isvendor) {
		this.isvendor = isvendor;
	}

	public CompanyResource getVendorCompany() {
		return vendorCompany;
	}

	public void setVendorCompany(CompanyResource vendorCompany) {
		this.vendorCompany = vendorCompany;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public List<AttributeValueResource> getAttributevalues() {
		return attributevalues;
	}

	public void setAttributevalues(List<AttributeValueResource> attributevalues) {
		this.attributevalues = attributevalues;
	}

	public List<LocationResource> getParentLocationResourceList() {
		return parentLocationResourceList;
	}

	public void setParentLocationResourceList(List<LocationResource> parentLocationResourceList) {
		this.parentLocationResourceList = parentLocationResourceList;
	}

	public int getEntityTypeId() {
		return entityTypeId;
	}

	public void setEntityTypeId(int entityTypeId) {
		this.entityTypeId = entityTypeId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public Integer getVendorCompanyId() {
		return vendorCompanyId;
	}

	public void setVendorCompanyId(Integer vendorCompanyId) {
		this.vendorCompanyId = vendorCompanyId;
	}	

	
}
