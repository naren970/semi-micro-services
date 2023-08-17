package com.gotracrat.attributesandtypes.controller.resource;

import java.util.Date;

public class AllAttachmentDTO {

	private Integer attachmentid;
	private String filename;
	private String description;
	private Date dateadded;
	private String addedby;
	private Boolean isNew;
	private String contenttype;
	
	public AllAttachmentDTO(Integer attachmentid, String filename, String description, Date dateadded, String addedby,
			Boolean isNew, String contenttype) {
		super();
		this.attachmentid = attachmentid;
		this.filename = filename;
		this.description = description;
		this.dateadded = dateadded;
		this.addedby = addedby;
		this.isNew = isNew;
		this.contenttype = contenttype;
	}
	public Integer getAttachmentid() {
		return attachmentid;
	}
	public void setAttachmentid(Integer attachmentid) {
		this.attachmentid = attachmentid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateadded() {
		return dateadded;
	}
	public void setDateadded(Date dateadded) {
		this.dateadded = dateadded;
	}
	public String getAddedby() {
		return addedby;
	}
	public void setAddedby(String addedby) {
		this.addedby = addedby;
	}
	public Boolean getIsNew() {
		return isNew;
	}
	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}
	public String getContenttype() {
		return contenttype;
	}
	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	
	
	
}
