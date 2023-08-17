/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:54 )
 *
*/
package com.gotracrat.managelocation.resource;

import java.io.Serializable;
import java.util.Date;

/**
 * Hateoas resource associated with Attachment.
 *
 * @author prabhakar
 */
public class AttachmentResource implements Serializable {

/**
*
*/
private static final long serialVersionUID = 1L;
private Integer attachmentid;
private Integer entityid;
private Integer entitytypeid;
private String filename;
private String contenttype;
private String attachmentFile;
private String description;
private Date dateadded;
private String addedby;
private String moduleType;
private byte[] byteData;
private String dateaddedOn;
private Integer companyID;
private Date updatedDate;
private String itemTag;
private Integer locationId;
private Boolean isNew;

public String getDateaddedOn() {
return dateaddedOn;
}

public void setDateaddedOn(String dateaddedOn) {
this.dateaddedOn = dateaddedOn;
}


public String getModuleType() {
return moduleType;
}

public void setModuleType(String moduleType) {
this.moduleType = moduleType;
}

// Constructor
public AttachmentResource() {
// Needed empty constructor for serialization
}

public Integer getAttachmentid() {
return this.attachmentid;
}

public void setAttachmentid(Integer attachmentid) {
this.attachmentid = attachmentid;
}

public Integer getEntityid() {
return this.entityid;
}

public void setEntityid(Integer entityid) {
this.entityid = entityid;
}

public Integer getEntitytypeid() {
return this.entitytypeid;
}

public void setEntitytypeid(Integer entitytypeid) {
this.entitytypeid = entitytypeid;
}

public String getFilename() {
return this.filename;
}

public void setFilename(String filename) {
this.filename = filename;
}

public String getContenttype() {
return this.contenttype;
}

public void setContenttype(String contenttype) {
this.contenttype = contenttype;
}

public String getDescription() {
return this.description;
}

public void setDescription(String description) {
this.description = description;
}

public Date getDateadded() {
return this.dateadded;
}

public void setDateadded(Date dateadded) {
this.dateadded = dateadded;
}

public String getAddedby() {
return this.addedby;
}

public void setAddedby(String addedby) {
this.addedby = addedby;
}

public String getAttachmentFile() {
return attachmentFile;
}

public void setAttachmentFile(String attachmentFile) {
this.attachmentFile = attachmentFile;
}

public byte[] getByteData() {
return byteData;
}

public void setByteData(byte[] byteData) {
this.byteData = byteData;
}


public Date getUpdatedDate() {
return updatedDate;
}

public void setUpdatedDate(Date updatedDate) {
this.updatedDate = updatedDate;
}

public String getItemTag() {
return itemTag;
}

public void setItemTag(String itemTag) {
this.itemTag = itemTag;
}

public Integer getCompanyID() {
return companyID;
}

public void setCompanyID(Integer companyID) {
this.companyID = companyID;
}

public Integer getLocationId() {
return locationId;
}

public void setLocationId(Integer locationId) {
this.locationId = locationId;
}

public Boolean getIsNew() {
return isNew;
}

public void setIsNew(Boolean isNew) {
this.isNew = isNew;
}

public AttachmentResource(String filename, String contenttype, String attachmentFile, String description,
		Date dateadded, String addedby) {
	super();
	this.filename = filename;
	this.contenttype = contenttype;
	this.attachmentFile = attachmentFile;
	this.description = description;
	this.dateadded = dateadded;
	this.addedby = addedby;
}

}