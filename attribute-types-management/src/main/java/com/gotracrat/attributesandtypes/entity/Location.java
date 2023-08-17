/*
 * Created on 2018-07-15 ( Date ISO 2018-07-15 - Time 13:09:29 )
 * prabhakar
*/
package com.gotracrat.attributesandtypes.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Persistent class for Location entity stored in table Location. This class is
 * a "record entity" without JPA links.
 * 
 * @author prabhakar
 */
@Entity
@Table(name = "Location", schema = "dbo")
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LocationID", nullable = false)
	private Integer locationid;
	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@ManyToOne()
	@JoinColumn(name = "CompanyID", nullable = false)
	@JsonBackReference
	private Company company;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "ParentID", referencedColumnName = "LocationID", nullable = false)
	@JsonIgnore
	@NotFound(action = NotFoundAction.IGNORE)
	private Location parentlocation;

	@OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "ParentID")
    private List<Location> parentLocationList;
	
	@Column(name = "Name", nullable = false, length = 100)
	private String name;
	@Column(name = "Description", length = 2000)
	private String description;
	@Column(name = "StatusID", nullable = false)
	private Integer statusid;
	@Column(name = "LastModifiedBy", nullable = false)
	private String lastmodifiedby;
	@Column(name = "DesiredSpareRatio")
	private BigDecimal desiredspareratio;
	@Column(name = "CriticalFlag", nullable = false)
	private Boolean criticalflag;
	@Column(name = "IsVendor", nullable = false)
	private Boolean isvendor;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "VendorCompanyID", nullable = true)
	@JsonBackReference
	private Company vendorCompany;
	
	@Column(name = "Address1", length = 100)
	private String address1;
	@Column(name = "Address2", length = 100)
	private String address2;
	@Column(name = "City", length = 50)
	private String city;
	@Column(name = "State", length = 2)
	private String state;
	@Column(name = "PostalCode", length = 10)
	private String postalcode;


	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public Location() {
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------
	public void setLocationid(Integer locationid) {
		this.locationid = locationid;
	}

	public Integer getLocationid() {
		return this.locationid;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------
	// --- DATABASE MAPPING : CompanyID (int)

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	// --- DATABASE MAPPING : ParentID (int)

	public Location getParentlocation() {
		return parentlocation;
	}

	public void setParentlocation(Location parentlocation) {
		this.parentlocation = parentlocation;
	}

	// --- DATABASE MAPPING : Name (varchar)
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	// --- DATABASE MAPPING : Description (varchar)
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	// --- DATABASE MAPPING : StatusID (int)
	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public Integer getStatusid() {
		return this.statusid;
	}

	// --- DATABASE MAPPING : LastModifiedBy (nvarchar)
	public void setLastmodifiedby(String lastmodifiedby) {
		this.lastmodifiedby = lastmodifiedby;
	}

	public String getLastmodifiedby() {
		return this.lastmodifiedby;
	}

	// --- DATABASE MAPPING : DesiredSpareRatio (decimal)
	public void setDesiredspareratio(BigDecimal desiredspareratio) {
		this.desiredspareratio = desiredspareratio;
	}

	public BigDecimal getDesiredspareratio() {
		return this.desiredspareratio;
	}

	// --- DATABASE MAPPING : CriticalFlag (bit)
	public void setCriticalflag(Boolean criticalflag) {
		this.criticalflag = criticalflag;
	}

	public Boolean getCriticalflag() {
		return this.criticalflag;
	}

	// --- DATABASE MAPPING : IsVendor (bit)
	public void setIsvendor(Boolean isvendor) {
		this.isvendor = isvendor;
	}

	public Boolean getIsvendor() {
		return this.isvendor;
	}

	// --- DATABASE MAPPING : VendorCompanyID (int)
		
	public Company getVendorCompany() {
		return vendorCompany;
	}

	public void setVendorCompany(Company vendorCompany) {
		this.vendorCompany = vendorCompany;
	}

	// --- DATABASE MAPPING : Address1 (varchar)
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress1() {
		return this.address1;
	}

	// --- DATABASE MAPPING : Address2 (varchar)
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress2() {
		return this.address2;
	}

	// --- DATABASE MAPPING : City (varchar)
	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return this.city;
	}

	// --- DATABASE MAPPING : State (char)
	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}

	// --- DATABASE MAPPING : PostalCode (varchar)
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getPostalcode() {
		return this.postalcode;
	}
	
	public List<Location> getParentLocationList() {
		return parentLocationList;
	}

	public void setParentLocationList(List<Location> parentLocationList) {
		this.parentLocationList = parentLocationList;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(locationid);
		sb.append(company.getCompanyid());
		sb.append("|");
		sb.append(parentlocation.getLocationid());
		sb.append("|");
		sb.append(name);
		sb.append("|");
		sb.append(description);
		sb.append("|");
		sb.append(statusid);
		sb.append("|");
		sb.append(lastmodifiedby);
		sb.append("|");
		sb.append(desiredspareratio);
		sb.append("|");
		sb.append(criticalflag);
		sb.append("|");
		sb.append(isvendor);
		sb.append("|");
		sb.append(vendorCompany.getCompanyid());
		sb.append("|");
		sb.append(address1);
		sb.append("|");
		sb.append(address2);
		sb.append("|");
		sb.append(city);
		sb.append("|");
		sb.append(state);
		sb.append("|");
		sb.append(postalcode);
		return sb.toString();
	}
}