/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:54 )
 * 
*/
package com.gotracrat.managelocation.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Persistent class for Attachment entity stored in table Attachment. This class
 * is a "record entity" without JPA links.
 * 
 * @author prabhakar
 */
@Entity
@Table(name = "Attachment", schema = "dbo")
public class Attachment implements Serializable {

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AttachmentID", nullable = false)
	private Integer attachmentid;
	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@Column(name = "EntityID", nullable = false,updatable = false)
	private Integer entityid;
	@Column(name = "EntityTypeID", nullable = false,updatable = false)
	private Integer entitytypeid;
	@Column(name = "FileName", nullable = false,updatable = false)
	private String filename;
	@Column(name = "ContentType", nullable = false,updatable = false)
	private String contenttype;
	@Lob
	@Column(name = "attachment",updatable = false)
	private byte[] attachment;
	@Column(name = "Description", length = 2000)
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DateAdded", nullable = false)
	private Date dateadded;
	@Column(name = "AddedBy", nullable = false)
	private String addedby;
	@Column(name = "isNew", nullable = false,updatable = false)
	private Boolean isNew;
	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public Attachment() {
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------
	public void setAttachmentid(Integer attachmentid) {
		this.attachmentid = attachmentid;
	}

	public Integer getAttachmentid() {
		return this.attachmentid;
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

	// --- DATABASE MAPPING : FileName (nvarchar)
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return this.filename;
	}

	// --- DATABASE MAPPING : ContentType (nvarchar)
	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public String getContenttype() {
		return this.contenttype;
	}

	// --- DATABASE MAPPING : Attachment (image)
	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	public byte[] getAttachment() {
		return this.attachment;
	}

	// --- DATABASE MAPPING : Description (varchar)
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	// --- DATABASE MAPPING : DateAdded (datetime)
	public void setDateadded(Date dateadded) {
		this.dateadded = dateadded;
	}

	public Date getDateadded() {
		return this.dateadded;
	}

	// --- DATABASE MAPPING : AddedBy (nvarchar)
	public void setAddedby(String addedby) {
		this.addedby = addedby;
	}

	public String getAddedby() {
		return this.addedby;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}
	
	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(attachmentid);
		sb.append(entityid);
		sb.append("|");
		sb.append(entitytypeid);
		sb.append("|");
		sb.append(filename);
		sb.append("|");
		sb.append(contenttype);
		sb.append("|");
		sb.append(attachment);
		sb.append("|");
		sb.append(description);
		sb.append("|");
		sb.append(dateadded);
		sb.append("|");
		sb.append(addedby);
		sb.append("|");
		sb.append(isNew);
		return sb.toString();
	}

	
}