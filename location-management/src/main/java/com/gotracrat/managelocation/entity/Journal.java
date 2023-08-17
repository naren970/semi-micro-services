/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:58 )
 * 
*/
package com.gotracrat.managelocation.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Persistent class for Journal entity stored in table Journal. This class is a
 * "record entity" without JPA links.
 * 
 * @author prabhakar
 */
@Entity
@Table(name = "Journal", schema = "dbo")
public class Journal implements Serializable {

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "JournalID", nullable = false)
	private Integer journalid;
	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@Column(name = "EntityID", nullable = false)
	private Integer entityid;
	@Column(name = "EntityTypeID", nullable = false, updatable = false)
	private Integer entitytypeid;
	@Column(name = "Entry", nullable = false, length = 5000)
	private String entry;
	@Column(name = "EnteredBy", nullable = false, length = 20)
	private String enteredby;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EnteredOn", nullable = false)
	private Date enteredon;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EffectiveOn", nullable = false)
	private Date effectiveon;
	@Column(name = "EntityXML")
	private String entityxml;
	@Column(name = "EntityName", length = 255)
	private String entityname;
	@Column(name = "LocationID")
	private Integer locationid;
	@Column(name = "LocationName", length = 255)
	private String locationname;
	@Column(name = "CompanyID", nullable = false)
	private Integer companyid;
	@Column(name = "JournalTypeID", nullable = false, updatable = false)
	private Integer journaltypeid;
	@Column(name = "JobNumber")
	private String jobnumber;
	@Column(name = "PONumber")
	private String ponumber;
	@Column(name = "ShippingNumber")
	private String shippingnumber;
	@Column(name = "TrackingNumber")
	private String trackingnumber;
	@Transient
	private String moduleType;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public Journal() {
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------
	public void setJournalid(Integer journalid) {
		this.journalid = journalid;
	}

	public Integer getJournalid() {
		return this.journalid;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------
	// --- DATABASE MAPPING : EntityID (int)
	public void setEntityid(Integer entityid) {
		this.entityid = entityid;
	}

	public Integer getEntityid() {
		return this.entityid;
	}

	// --- DATABASE MAPPING : EntityTypeID (int)
	public void setEntitytypeid(Integer entitytypeid) {
		this.entitytypeid = entitytypeid;
	}

	public Integer getEntitytypeid() {
		return this.entitytypeid;
	}

	// --- DATABASE MAPPING : Entry (varchar)
	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getEntry() {
		return this.entry;
	}

	// --- DATABASE MAPPING : EnteredBy (varchar)
	public void setEnteredby(String enteredby) {
		this.enteredby = enteredby;
	}

	public String getEnteredby() {
		return this.enteredby;
	}

	// --- DATABASE MAPPING : EnteredOn (datetime)
	public void setEnteredon(Date enteredon) {
		this.enteredon = enteredon;
	}

	public Date getEnteredon() {
		return this.enteredon;
	}

	// --- DATABASE MAPPING : EffectiveOn (datetime)
	public void setEffectiveon(Date effectiveon) {
		this.effectiveon = effectiveon;
	}

	public Date getEffectiveon() {
		return this.effectiveon;
	}

	// --- DATABASE MAPPING : EntityXML (xml)
	public void setEntityxml(String entityxml) {
		this.entityxml = entityxml;
	}

	public String getEntityxml() {
		return this.entityxml;
	}

	// --- DATABASE MAPPING : EntityName (varchar)
	public void setEntityname(String entityname) {
		this.entityname = entityname;
	}

	public String getEntityname() {
		return this.entityname;
	}

	// --- DATABASE MAPPING : LocationID (int)
	public void setLocationid(Integer locationid) {
		this.locationid = locationid;
	}

	public Integer getLocationid() {
		return this.locationid;
	}

	// --- DATABASE MAPPING : LocationName (varchar)
	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}

	public String getLocationname() {
		return this.locationname;
	}

	// --- DATABASE MAPPING : CompanyID (int)
	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public Integer getCompanyid() {
		return this.companyid;
	}

	// --- DATABASE MAPPING : JournalTypeID (int)
	public void setJournaltypeid(Integer journaltypeid) {
		this.journaltypeid = journaltypeid;
	}

	public Integer getJournaltypeid() {
		return this.journaltypeid;
	}

	// --- DATABASE MAPPING : JobNumber (nvarchar)
	public void setJobnumber(String jobnumber) {
		this.jobnumber = jobnumber;
	}

	public String getJobnumber() {
		return this.jobnumber;
	}

	// --- DATABASE MAPPING : PONumber (nvarchar)
	public void setPonumber(String ponumber) {
		this.ponumber = ponumber;
	}

	public String getPonumber() {
		return this.ponumber;
	}

	// --- DATABASE MAPPING : ShippingNumber (nvarchar)
	public void setShippingnumber(String shippingnumber) {
		this.shippingnumber = shippingnumber;
	}

	public String getShippingnumber() {
		return this.shippingnumber;
	}

	// --- DATABASE MAPPING : TrackingNumber (nvarchar)
	public void setTrackingnumber(String trackingnumber) {
		this.trackingnumber = trackingnumber;
	}

	public String getTrackingnumber() {
		return this.trackingnumber;
	}
	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(journalid);
		sb.append(entityid);
		sb.append("|");
		sb.append(entitytypeid);
		sb.append("|");
		sb.append(entry);
		sb.append("|");
		sb.append(enteredby);
		sb.append("|");
		sb.append(enteredon);
		sb.append("|");
		sb.append(effectiveon);
		sb.append("|");
		sb.append(entityxml);
		sb.append("|");
		sb.append(entityname);
		sb.append("|");
		sb.append(locationid);
		sb.append("|");
		sb.append(locationname);
		sb.append("|");
		sb.append(companyid);
		sb.append("|");
		sb.append(journaltypeid);
		sb.append("|");
		sb.append(jobnumber);
		sb.append("|");
		sb.append(ponumber);
		sb.append("|");
		sb.append(shippingnumber);
		sb.append("|");
		sb.append(trackingnumber);
		return sb.toString();
	}
}